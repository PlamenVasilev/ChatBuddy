package com.example.chatbuddy.ui.chat;

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

    void onChatsListClick() {
        fragment.showChatsList(true);
    }

    void onChatBuddiesClick() {
        fragment.showChatBuddies(true);
    }

    void onSearchClick() {
        fragment.showSearch(true);
    }


}
