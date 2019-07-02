package com.example.chatbuddy.ui.settings;

import com.example.chatbuddy.data.db.local.LocalDb;
import com.example.chatbuddy.data.db.local.model.UserSettings;
import com.example.chatbuddy.data.db.remote.FbDatabase;


class SettingsActivityPresenter {


    private final LocalDb db;
    private final String uid;
    private final FbDatabase fb;
    private SettingsActivity activity;

    SettingsActivityPresenter(SettingsActivity activity) {
        this.activity = activity;
        this.db = LocalDb.instance(activity);
        this.fb = FbDatabase.getInstance();
        this.uid = activity.getIntent().getStringExtra(UserSettings.class.toString());
    }

    void onThemeChanged(boolean isChecked) {
        db.setThemeMode(uid, isChecked);
    }

    void setValues() {
        UserSettings settings = db.getUserSettings(uid);
        if(settings != null){
            activity.binding.theme.setChecked(settings.getDarkMode());
            activity.binding.nickname.setText(settings.getNickname());
        }else{
            activity.binding.nickname.setText(FbDatabase.getInstance().getCurrentUser().getNickname());
        }

    }

    void saveSettings() {
        boolean isChecked = activity.binding.theme.isChecked();
        String nickname = activity.binding.nickname.getText().toString();

        UserSettings settings = db.getUserSettings(uid);
        if(settings == null){
            settings = new UserSettings(uid);
            settings.setDarkMode(isChecked);
            settings.setNickname(nickname);

            db.insertUserSettings(settings);
        }else{
            settings.setDarkMode(isChecked);
            settings.setNickname(nickname);

            db.updateUserSettings(settings);
        }

        fb.updateUserSettings(settings);

        activity.showSaveConfirmation();
        activity.onBackPressed();
    }
}
