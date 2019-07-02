package com.example.chatbuddy.data.db.remote;

import androidx.annotation.NonNull;

import com.example.chatbuddy.data.db.local.model.UserSettings;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.MessageModel;
import com.example.chatbuddy.data.db.remote.model.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class FbDatabase {
    private static final String USERS = "users";
    private static final String BUDDIES = "buddies";
    private static final String MESSAGES = "messages";
    private static final String FIELD_NICKNAME = "nickname";
    private static FbDatabase instance;
    private final FirebaseUser authUser;
    private final FirebaseDatabase db;
    private UserModel currentUser;

    public static FbDatabase getInstance(){
        if(instance == null){
            instance = new FbDatabase();
        }
        return instance;
    }

    private FbDatabase() {
        authUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance();
        db.setPersistenceEnabled(true);
        db.getReference(USERS).keepSynced(true);
        db.getReference(Objects.requireNonNull(authUser).getUid()+"/"+MESSAGES).keepSynced(true);

        getUser(authUser.getUid(), new FbCallback.onUserGet() {
            @Override
            public void onSuccess(UserModel user) {
                currentUser = user;
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void addUser(final UserModel userModel, final FbCallback.onUserCreated onUserCreate) {
        DatabaseReference users = db.getReference(USERS);

        users.child(userModel.getUid()).setValue(userModel).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                onUserCreate.onComplete();
            }
        });
    }

    private void getUser(String uid, final FbCallback.onUserGet onUserGet) {
        DatabaseReference users = db.getReference().child(USERS);

        users.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);
                if (user != null){
                    onUserGet.onSuccess(user);
                }else{
                    onUserGet.onFailure();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void searchUsersByNickname(String nickname, final FbCallback.onUserSearch onUserSearch) {
        DatabaseReference users = db.getReference(USERS);

        users.orderByChild(FIELD_NICKNAME).equalTo(nickname, FIELD_NICKNAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<UserModel> searchList = new ArrayList<UserModel>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    UserModel user = postSnapshot.getValue(UserModel.class);
                    if(!user.getUid().equals(authUser.getUid())){
                        searchList.add(user);
                    }
                }

                onUserSearch.onComplete(searchList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });;
    }

    public void addBuddy(final UserModel user) {
        getUser(Objects.requireNonNull(authUser).getUid(), new FbCallback.onUserGet(){
            @Override
            public void onSuccess(UserModel currentUser) {
                // A->B
                db.getReference(currentUser.getUid()+"/"+BUDDIES+"/"+user.getUid()).setValue(user.getNickname());
                // B->A
                db.getReference(user.getUid()+"/"+BUDDIES+"/"+currentUser.getUid()).setValue(currentUser.getNickname());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void getBuddiesList(final FbCallback.onBuddiesList onBuddiesList){
        db.getReference(authUser.getUid()+"/"+BUDDIES+"/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<BuddyModel> buddyList = new ArrayList<BuddyModel>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    BuddyModel buddy = new BuddyModel(postSnapshot.getKey(), postSnapshot.getValue().toString());
                    buddyList.add(buddy);
                }

                onBuddiesList.onComplete(buddyList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void sendMessage(final BuddyModel buddy, final MessageModel message) {
        getUser(Objects.requireNonNull(authUser).getUid(), new FbCallback.onUserGet(){
            @Override
            public void onSuccess(UserModel currentUser) {
                // A->B
                db.getReference(currentUser.getUid()+"/"+MESSAGES+"/"+buddy.getUid()).push().setValue(message);
                // B->A
                message.setDirection(MessageModel.DIR_IN);
                db.getReference(buddy.getUid()+"/"+MESSAGES+"/"+currentUser.getUid()).push().setValue(message);
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void getMessages(BuddyModel buddy, final FbCallback.onMessagesList onMessagesList) {
        db.getReference(authUser.getUid()+"/"+MESSAGES+"/"+buddy.getUid()).limitToLast(500).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<MessageModel> messagesList = new ArrayList<MessageModel>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    MessageModel message = postSnapshot.getValue(MessageModel.class);
                    messagesList.add(message);
                }

                Collections.reverse(messagesList);

                onMessagesList.onComplete(messagesList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public void updateUserSettings(UserSettings settings) {
        currentUser.setNickname(settings.getNickname());
        currentUser.setAvatar(settings.getAvatar());
        updateUser(currentUser);
    }

    private void updateUser(final UserModel userModel) {
        db.getReference(USERS).child(userModel.getUid()).setValue(userModel);
    }
}
