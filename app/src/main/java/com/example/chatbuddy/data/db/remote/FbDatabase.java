package com.example.chatbuddy.data.db.remote;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chatbuddy.data.db.remote.model.BuddyModel;
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
import java.util.Objects;

public class FbDatabase {
    private static final String USERS = "users";
    private static FbDatabase instance;
    private final FirebaseUser currentUser;
    private final FirebaseDatabase db;

    public static FbDatabase getInstance(){
        if(instance == null){
            instance = new FbDatabase();
        }
        return instance;
    }

    private FbDatabase() {
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance();
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

        users.orderByChild("nickname").startAt(nickname, "nickname"+ "\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<UserModel> searchList = new ArrayList<UserModel>();

                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    UserModel user = postSnapshot.getValue(UserModel.class);
                    if(!user.getUid().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){
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
        getUser(Objects.requireNonNull(currentUser).getUid(), new FbCallback.onUserGet(){
            @Override
            public void onSuccess(UserModel currentUser) {
                // A->B
                db.getReference(currentUser.getUid()+"/buddies/"+user.getUid()).setValue(user.getNickname());
                // B->A
                db.getReference(user.getUid()+"/buddies/"+currentUser.getUid()).setValue(currentUser.getNickname());
            }

            @Override
            public void onFailure() {

            }
        });
    }

    public void getBuddiesList(final FbCallback.onBuddiesList onBuddiesList){
        db.getReference(currentUser.getUid()+"/buddies/").addValueEventListener(new ValueEventListener() {
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
}
