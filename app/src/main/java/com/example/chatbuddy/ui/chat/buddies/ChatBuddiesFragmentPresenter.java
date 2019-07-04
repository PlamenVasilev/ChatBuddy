package com.example.chatbuddy.ui.chat.buddies;

import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

class ChatBuddiesFragmentPresenter {

    private ChatBuddiesFragment fragment;

    ChatBuddiesFragmentPresenter(ChatBuddiesFragment fragment) {
        this.fragment = fragment;
    }

    void loadBuddiesList() {
        fragment.mListener.showLoader();
        FbDatabase.getInstance().getBuddiesList(new FbCallback.onBuddiesList() {
            @Override
            public void onComplete(ArrayList<BuddyModel> buddyList) {
                if(fragment.mListener != null){
                    fragment.mListener.hideLoader();
                }
                fragment.loadBuddiesData(buddyList);
            }
        });
    }

    void onBuddyClicked(final BuddyModel buddy) {
        fragment.mListener.showLoader();
        FbDatabase.getInstance().getUser(buddy.getUid(), new FbCallback.onUserGet() {
            @Override
            public void onSuccess(UserModel user) {
                buddy.setAvatar(user.getAvatar());
                fragment.mListener.hideLoader();
                fragment.mListener.showTalkScreen(buddy);
            }

            @Override
            public void onFailure() {

            }
        });
    }
}
