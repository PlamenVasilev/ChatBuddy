package com.example.chatbuddy.ui.settings;

import android.graphics.Bitmap;

import com.example.chatbuddy.data.db.local.LocalDb;
import com.example.chatbuddy.data.db.local.model.UserSettings;
import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.FbStorage;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;


class SettingsActivityPresenter {


    private final LocalDb db;
    private final String uid;
    private final FbDatabase fb;
    private final FbStorage storage;
    private SettingsActivity activity;
    private Bitmap selectedImage;

    SettingsActivityPresenter(SettingsActivity activity) {
        this.activity = activity;
        this.db = LocalDb.instance(activity);
        this.fb = FbDatabase.getInstance();
        this.storage = FbStorage.getInstance();
        this.uid = activity.getIntent().getStringExtra(UserSettings.class.toString());
    }

    void setValues() {
        UserSettings settings = db.getUserSettings(uid);
        if (settings != null) {
            activity.binding.theme.setChecked(settings.getDarkMode());
            activity.binding.nickname.setText(settings.getNickname());
            if (settings.getAvatar() != null && !settings.getAvatar().isEmpty()){
                Picasso.get().load(settings.getAvatar()).into(activity.binding.avatar);
            }
        } else {
            activity.binding.nickname.setText(FbDatabase.getInstance().getCurrentUser().getNickname());
        }

    }

    void saveSettings() {
        final boolean darkMode = activity.binding.theme.isChecked();
        final String nickname = activity.binding.nickname.getText().toString();

        final UserSettings settings = getUserSettings();
        settings.setDarkMode(darkMode);
        settings.setNickname(nickname);

        if(selectedImage != null){
            ByteArrayOutputStream byteStreem = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteStreem);
            byte[] avatarBytes = byteStreem.toByteArray();

            activity.showLoader();
            storage.uploadAvatar(avatarBytes, new FbCallback.onUploadCompleted() {
                @Override
                public void onComplete(String url) {
                    settings.setAvatar(url);
                    saveUserSettings(settings);

                    activity.showSaveConfirmation();
                    activity.onBackPressed();
                    activity.hideLoader();
                }

                @Override
                public void onError(Exception exception) {
                    activity.showError(exception.getMessage());
                    activity.hideLoader();
                }
            });
        }else{
            saveUserSettings(settings);

            activity.showSaveConfirmation();
            activity.onBackPressed();
            activity.hideLoader();
        }
    }

    private void saveUserSettings(UserSettings settings) {
        db.insertUserSettings(settings);
        fb.updateUserSettings(settings);
    }

    private UserSettings getUserSettings() {
        UserSettings settings = db.getUserSettings(uid);
        if (settings == null) {
            settings = new UserSettings(uid);
        }

        return settings;
    }

    void setImage(Bitmap photo) {
        selectedImage = photo;
        activity.binding.avatar.setImageBitmap(photo);
    }
}
