package com.example.chatbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.chatbuddy.databinding.ActivityMainBinding;
import com.example.chatbuddy.ui.login.LoginFragment;
import com.example.chatbuddy.ui.register.RegisterFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentListener, RegisterFragment.OnRegisterFragmentListener {

    private ActivityMainBinding binding;
    private Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        showLoginScreen();
    }

    private void openScreen(Fragment fragment) {
        lastFragment = fragment;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void showLoginScreen(){
        openScreen(new LoginFragment());
    }

    public void showRegisterScreen(){
        openScreen(new RegisterFragment());
    }

    public void showChatScreen(){

    }

    public void showChatListFragment(){

    }

    public void showChatBuddiesFragment(){

    }

    public void showChatFragment(){

    }


}
