package com.example.chatbuddy.ui.chat.search;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

class ChatSearchFragmentPresenter {

    private static final String TAG = "ChatSearch";
    private ChatSearchFragment fragment;

    ChatSearchFragmentPresenter(ChatSearchFragment fragment) {
        this.fragment = fragment;
    }

    void searchByNickname(String email) {
        FbDatabase.searchUsersByNickname(email).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG,"COUNT:"+dataSnapshot.getChildrenCount());

                ArrayList<UserModel> searchList = new ArrayList<>();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    UserModel user = postSnapshot.getValue(UserModel.class);
                    searchList.add(user);

                    Log.d(TAG, "Nickname: " + user.getNickname());
                }

                fragment.loadSearchData(searchList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    void addBuddy(UserModel user) {
        FbDatabase.addBuddy(user);
    }
}
