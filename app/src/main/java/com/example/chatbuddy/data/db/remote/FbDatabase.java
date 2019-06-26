package com.example.chatbuddy.data.db.remote;

import com.example.chatbuddy.data.db.remote.model.UserModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FbDatabase {

    public static Task<Void> addUser(UserModel userModel) {
        DatabaseReference users = FirebaseDatabase.getInstance().getReference("users");

        return users.push().setValue(userModel);
    }


}
