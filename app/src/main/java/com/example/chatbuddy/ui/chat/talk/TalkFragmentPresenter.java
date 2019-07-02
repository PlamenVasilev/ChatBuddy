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

    private void send(BuddyModel buddy, String msg) {
        fragment.mListener.showLoader();
        MessageModel message = new MessageModel(msg);
        FbDatabase.getInstance().sendMessage(buddy, message);
        fragment.mListener.hideLoader();
    }

    void loadMessages(BuddyModel buddy) {
        fragment.mListener.showLoader();
        FbDatabase.getInstance().getMessages(buddy, new FbCallback.onMessagesList() {
            @Override
            public void onComplete(ArrayList<MessageModel> messages) {
                fragment.mListener.hideLoader();
                fragment.showMessages(messages);
            }
        });
    }

    void sendMessage(BuddyModel buddy) {
        String msg = fragment.binding.talkText.getText().toString();
        if(!msg.isEmpty() && msg.length() >= 2){
            send(buddy, msg);
            fragment.binding.talkText.setText(null);
        }
    }
}
