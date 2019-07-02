package com.example.chatbuddy.ui.register;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.FragmentRegisterBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private OnRegisterFragmentListener mListener;
    private FragmentRegisterBinding binding;
    private RegisterFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new RegisterFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);

        binding.login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.showLoginScreen();
            }
        });

        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onSubmit(
                        binding.registerEmail.getText().toString(),
                        binding.registerPassword.getText().toString(),
                        binding.registerNickname.getText().toString()
                );
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnRegisterFragmentListener) {
            mListener = (OnRegisterFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnRegisterFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void registerSuccessfull() {
        Snackbar.make(Objects.requireNonNull(this.getView()), "Register successfull!", Snackbar.LENGTH_SHORT)
                .show();

        mListener.showChatScreen();
    }

    void registerFailed(Exception exception) {
        Snackbar.make(Objects.requireNonNull(this.getView()), "Register failed: "+exception.getMessage(), Snackbar.LENGTH_LONG)
                .show();
    }

    void showLoader(){
        mListener.showLoader();
    }

    void hideLoader(){
        mListener.hideLoader();
    }

    public interface OnRegisterFragmentListener {
        void showLoader();
        void hideLoader();
        void showLoginScreen();
        void showChatScreen();
    }
}
