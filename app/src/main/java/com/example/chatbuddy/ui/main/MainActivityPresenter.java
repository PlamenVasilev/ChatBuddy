package com.example.chatbuddy.ui.main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Timer;
import java.util.TimerTask;

class MainActivityPresenter {
    private MainActivity activity;
    private FirebaseAuth mAuth;

    MainActivityPresenter(MainActivity activity) {
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
    }


    void load() {
        activity.showSplashScreen();

        final FirebaseUser currentUser = mAuth.getCurrentUser();

        // Splash timeout
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentUser != null && !currentUser.isAnonymous()) {
                    activity.showChatScreen();
                }else{
                    activity.showLoginScreen();
                }
            }
        }, 1000);
    }
}
