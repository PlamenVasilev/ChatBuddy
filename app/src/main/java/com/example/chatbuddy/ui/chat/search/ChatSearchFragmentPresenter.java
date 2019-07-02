package com.example.chatbuddy.ui.chat.search;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

class ChatSearchFragmentPresenter {

    private ChatSearchFragment fragment;

    ChatSearchFragmentPresenter(ChatSearchFragment fragment) {
        this.fragment = fragment;
    }

    private void searchByNickname(String email) {
        FbDatabase.getInstance().searchUsersByNickname(email, new FbCallback.onUserSearch() {
            @Override
            public void onComplete(ArrayList<UserModel> searchList) {
                fragment.mListener.hideLoader();
                fragment.loadSearchData(searchList);
            }
        });
    }

    void addBuddy(UserModel user) {
        FbDatabase.getInstance().addBuddy(user);
    }

    void searchBuddy() {
        String searchName = fragment.binding.searchNickname.getText().toString();
        if(searchName.isEmpty()){
            fragment.binding.searchNickname.setError(fragment.getResources().getString(R.string.search_enter_nickname));
        }else if(searchName.length()<=3) {
            fragment.binding.searchNickname.setError(fragment.getResources().getString(R.string.search_nickname_short));
        }else{
            fragment.mListener.showLoader();
            searchByNickname(searchName);
        }
    }
}
