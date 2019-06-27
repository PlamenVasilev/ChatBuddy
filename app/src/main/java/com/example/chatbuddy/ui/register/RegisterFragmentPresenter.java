package com.example.chatbuddy.ui.register;

import androidx.annotation.NonNull;

import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.UserModel;
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

    void onSubmit(String email, String password, final String nickname) {
        fragment.showLoader();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Objects.requireNonNull(fragment.getActivity()), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        fragment.hideLoader();

                        if (task.isSuccessful()) {
                            final FirebaseUser authUser = mAuth.getCurrentUser();
                            UserModel userModel = new UserModel(Objects.requireNonNull(authUser).getUid(), authUser.getEmail(), nickname);

                            FbDatabase.getInstance().addUser(userModel, new FbCallback.onUserCreated() {
                                @Override
                                public void onComplete() {
                                    fragment.registerSuccessfull();
                                }
                            });
                        } else {
                            fragment.registerFailed(task.getException());
                        }
                    }
                });
    }
}
