package com.example.chatbuddy.ui.chat;

import android.content.Intent;

import com.example.chatbuddy.data.db.local.model.UserSettings;
import com.example.chatbuddy.ui.settings.SettingsActivity;
import com.google.firebase.auth.FirebaseAuth;

class ChatFragmentPresenter {

    private ChatFragment fragment;
    private FirebaseAuth mAuth;

    ChatFragmentPresenter(ChatFragment fragment) {
        this.fragment = fragment;
        this.mAuth = FirebaseAuth.getInstance();
    }

    void onLogoutClicked() {
        fragment.showLogoutDialog();
    }

    void logout() {
        mAuth.signOut();
    }

    void onChatBuddiesClick() {
        fragment.showChatBuddies(true);
    }

    void onSearchClick() {
        fragment.showSearch(true);
    }


    void showSettings() {
        Intent settings = new Intent(fragment.getContext(), SettingsActivity.class);
        settings.putExtra(UserSettings.class.toString(), FirebaseAuth.getInstance().getCurrentUser().getUid());
        fragment.startActivity(settings);
    }
}
