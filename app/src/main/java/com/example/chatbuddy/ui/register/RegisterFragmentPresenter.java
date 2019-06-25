package com.example.chatbuddy.ui.register;

import android.widget.Toast;

class RegisterFragmentPresenter {

    private RegisterFragment fragment;

    RegisterFragmentPresenter(RegisterFragment fragment) {
        this.fragment = fragment;
    }

    void onSubmit() {
        Toast.makeText(fragment.getContext(),"Register ...", Toast.LENGTH_LONG).show();
    }
}
