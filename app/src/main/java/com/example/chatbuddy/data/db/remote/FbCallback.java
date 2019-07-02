package com.example.chatbuddy.data.db.remote;

import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.MessageModel;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

public interface FbCallback {
    interface onUserCreated {
        void onComplete();
    }

    interface onUserUpdated {
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

    interface onMessagesList{
        void onComplete(ArrayList<MessageModel> messages);
    }
}
