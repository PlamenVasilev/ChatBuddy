package com.example.chatbuddy.ui.login;

import android.widget.Toast;

class LoginFragmentPresenter {

    private LoginFragment fragment;

    LoginFragmentPresenter(LoginFragment fragment) {
        this.fragment = fragment;
    }

    void onSubmit(){
        Toast.makeText(fragment.getContext(),"Login ...", Toast.LENGTH_LONG).show();
    }
}
