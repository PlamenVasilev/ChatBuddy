package com.example.chatbuddy.ui.login;

import androidx.annotation.NonNull;

import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

class LoginFragmentPresenter {

    private LoginFragment fragment;
    private FirebaseAuth mAuth;

    LoginFragmentPresenter(LoginFragment fragment) {
        this.fragment = fragment;
        mAuth = FirebaseAuth.getInstance();
    }

    void onSubmit(String email, String password) {
        fragment.showLoader();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(fragment.getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        fragment.hideLoader();

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            fragment.loginSuccessfull(user);
                        } else {
                            fragment.loginFailed(task.getException());
                        }
                    }
                });
    }
}
