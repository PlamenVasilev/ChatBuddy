package com.example.chatbuddy.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.databinding.ActivityMainBinding;
import com.example.chatbuddy.ui.chat.ChatFragment;
import com.example.chatbuddy.ui.chat.talk.TalkFragment;
import com.example.chatbuddy.ui.login.LoginFragment;
import com.example.chatbuddy.ui.register.RegisterFragment;
import com.example.chatbuddy.ui.splash.SplashFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginFragmentListener, RegisterFragment.OnRegisterFragmentListener, ChatFragment.OnChatFragmentListener, TalkFragment.OnTalkFragmentListener {

    private ActivityMainBinding binding;
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        presenter = new MainActivityPresenter(this);
        presenter.load();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void openScreen(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_frame, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    public void showLoader(){
        binding.mainProgress.setVisibility(View.VISIBLE);
    }

    public void hideLoader(){
        binding.mainProgress.setVisibility(View.GONE);
    }

    public void showSplashScreen() {
        openScreen(new SplashFragment(), false);
    }

    public void showLoginScreen(){
        openScreen(new LoginFragment(), false);
    }

    public void showRegisterScreen(){
        openScreen(new RegisterFragment(), false);
    }

    public void showChatScreen(){
        openScreen(new ChatFragment(), false);
    }

    public void showTalkScreen(BuddyModel buddy) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("buddy", buddy);

        Fragment fragment = new TalkFragment();
        fragment.setArguments(bundle);

        openScreen(fragment, true);
    }

}
