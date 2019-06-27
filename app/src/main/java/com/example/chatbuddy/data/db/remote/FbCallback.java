package com.example.chatbuddy.data.db.remote;

import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

public interface FbCallback {
    interface onUserCreated {
        void onComplete();
    }

    interface onUserGet{
        void onSuccess(UserModel user);
        void onFailure();
    }

    interface onUserSearch{
        void onComplete(ArrayList<UserModel> searchList);
    }

    interface onBuddiesList{
        void onComplete(ArrayList<BuddyModel> buddyList);
    }
}
