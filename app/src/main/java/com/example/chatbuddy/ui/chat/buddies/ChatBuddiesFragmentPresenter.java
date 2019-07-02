package com.example.chatbuddy.ui.chat.buddies;

import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;

import java.util.ArrayList;

class ChatBuddiesFragmentPresenter {

    private ChatBuddiesFragment fragment;

    ChatBuddiesFragmentPresenter(ChatBuddiesFragment fragment) {
        this.fragment = fragment;
    }

    void loadBuddiesList() {
        FbDatabase.getInstance().getBuddiesList(new FbCallback.onBuddiesList() {
            @Override
            public void onComplete(ArrayList<BuddyModel> buddyList) {
                fragment.loadBuddiesData(buddyList);
            }
        });
    }

    void onBuddyClicked(BuddyModel buddy) {
        fragment.mListener.showLoader();
        fragment.mListener.showTalkScreen(buddy);
    }
}
