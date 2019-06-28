package com.example.chatbuddy.ui.chat.talk;

import android.util.Log;

import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.MessageModel;

import java.util.ArrayList;

class TalkFragmentPresenter {

    private TalkFragment fragment;

    TalkFragmentPresenter(TalkFragment fragment) {
        this.fragment = fragment;
    }

    void sendMessage(BuddyModel buddy, MessageModel message) {
        FbDatabase.getInstance().sendMessage(buddy, message);
    }

    void loadMessages(BuddyModel buddy) {
        Log.e(getClass().toString(), "load messages !!!!");
        FbDatabase.getInstance().getMessages(buddy, new FbCallback.onMessagesList() {
            @Override
            public void onComplete(ArrayList<MessageModel> messages) {
                fragment.showMessages(messages);
            }
        });
    }
}
