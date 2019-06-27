package com.example.chatbuddy.ui.chat.search;

import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

class ChatSearchFragmentPresenter {

    private ChatSearchFragment fragment;

    ChatSearchFragmentPresenter(ChatSearchFragment fragment) {
        this.fragment = fragment;
    }

    void searchByNickname(String email) {
        FbDatabase.getInstance().searchUsersByNickname(email, new FbCallback.onUserSearch() {
            @Override
            public void onComplete(ArrayList<UserModel> searchList) {
                fragment.loadSearchData(searchList);
            }
        });
    }

    void addBuddy(UserModel user) {
        FbDatabase.getInstance().addBuddy(user);
    }
}
