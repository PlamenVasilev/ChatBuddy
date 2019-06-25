package com.example.chatbuddy.ui.login;

import android.widget.Toast;

import androidx.annotation.NonNull;

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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(fragment.getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            fragment.loginSuccessfull(user);
                        } else {

                            fragment.loginFailed(task.getException());
                        }
                    }
                });
    }
}
