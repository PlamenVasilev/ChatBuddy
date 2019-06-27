package com.example.chatbuddy.data.db.remote;

import com.example.chatbuddy.data.db.remote.model.UserModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.Objects;

public class FbDatabase {

    public static Task<Void> addUser(UserModel userModel) {
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

        return users.push().setValue(userModel);
    }


    public static Query searchUsersByNickname(String nickname) {
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

        return users.orderByChild("nickname").startAt(nickname, "nickname"+ "\uf8ff");
    }

    public static void addBuddy(UserModel user) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String path = Objects.requireNonNull(mAuth.getCurrentUser()).getUid()+"/buddies/"+user.getUid();
        DatabaseReference myRef = database.getReference(path);
        myRef.setValue(user.getNickname());
    }


}
