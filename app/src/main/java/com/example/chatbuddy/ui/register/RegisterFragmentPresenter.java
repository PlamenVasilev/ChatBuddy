package com.example.chatbuddy.ui.register;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

class RegisterFragmentPresenter {

    private RegisterFragment fragment;
    private FirebaseAuth mAuth;

    RegisterFragmentPresenter(RegisterFragment fragment) {
        this.fragment = fragment;
        mAuth = FirebaseAuth.getInstance();
    }

    void onSubmit(String email, String password) {
        fragment.showLoader();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(fragment.getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        fragment.hideLoader();

                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            fragment.registerSuccessfull(user);
                        } else {
                            fragment.registerFailed(task.getException());
                        }
                    }
                });
    }
}
