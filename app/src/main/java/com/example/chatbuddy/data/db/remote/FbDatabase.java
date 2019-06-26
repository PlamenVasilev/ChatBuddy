package com.example.chatbuddy.data.db.remote;

import com.example.chatbuddy.data.db.remote.model.UserModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FbDatabase {

    public static Task<Void> addUser(UserModel userModel) {
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

        return users.push().setValue(userModel);
    }


    public static Query searchUsersByNickname(String nickname) {
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

        return users.orderByChild("nickname").startAt(nickname, "nickname"+ "\uf8ff");
    }
}
