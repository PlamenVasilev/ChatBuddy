package com.example.chatbuddy.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.ActivityMainBinding;
import com.example.chatbuddy.ui.chat.ChatFragment;
import com.example.chatbuddy.ui.login.LoginFragment;
import com.example.chatbuddy.ui.register.RegisterFragment;
import com.example.chatbuddy.ui.splash.SplashFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentListener, RegisterFragment.OnRegisterFragmentListener, ChatFragment.OnChatFragmentListener {

    private ActivityMainBinding binding;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        presenter.load();
    }

    private void openScreen(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fragment)
                .commit();
    }

    public void showSplashScreen() {
        openScreen(new SplashFragment());
    }

    public void showLoginScreen(){
        openScreen(new LoginFragment());
    }

    public void showRegisterScreen(){
        openScreen(new RegisterFragment());
    }

    public void showChatScreen(){
        openScreen(new ChatFragment());
    }

    public void showChatListFragment(){

    }

    public void showChatBuddiesFragment(){

    }

    public void showChatFragment(){

    }


}
