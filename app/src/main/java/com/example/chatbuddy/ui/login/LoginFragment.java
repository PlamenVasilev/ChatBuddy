package com.example.chatbuddy.ui.login;

import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.FragmentLoginBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private OnLoginFragmentListener mListener;
    private LoginFragmentPresenter presenter;
    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginFragmentPresenter(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);

        binding.submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                presenter.onSubmit(binding.loginEmail.getText().toString(), binding.loginPassword.getText().toString());
            }
        });

        binding.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mListener.showRegisterScreen();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginFragmentListener) {
            mListener = (OnLoginFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnLoginFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void loginSuccessfull(FirebaseUser user) {
        Snackbar.make(Objects.requireNonNull(this.getView()), "Login successfull!", Snackbar.LENGTH_SHORT)
                .show();

        mListener.showChatScreen();
    }

    void loginFailed(Exception exception) {
        Snackbar.make(Objects.requireNonNull(this.getView()), "Login failed: "+exception.getMessage(), Snackbar.LENGTH_LONG)
                .show();
    }

    void showLoader(){
        mListener.showLoader();
    }

    void hideLoader(){
        mListener.hideLoader();
    }

    public interface OnLoginFragmentListener {
        void showLoader();
        void hideLoader();
        void showRegisterScreen();
        void showChatScreen();
    }
}
