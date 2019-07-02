package com.example.chatbuddy.data.db.local;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.example.chatbuddy.data.db.local.model.UserSettings;
import com.example.chatbuddy.data.db.remote.FbDatabase;

public class LocalDb {

    private static LocalDb instance;
    private final AppDatabase db;

    public static LocalDb instance(Context context){
        if(instance == null){
            instance = new LocalDb(context);
        }

        return instance;
    }

    private LocalDb(Context context){
        db = Room.databaseBuilder(context, AppDatabase.class, "settings.sqlite")
                .allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDb(){
        return db;
    }

    public void setThemeMode(String uid, boolean isChecked) {
        UserSettings settings = db.userSettingsDao().getSettingsById(uid);
        if(settings == null){
            settings = new UserSettings(uid);
            settings.setDarkMode(isChecked);
            settings.setNickname(FbDatabase.getInstance().getCurrentUser().getNickname());
            db.userSettingsDao().insertUserSettings(settings);
        }else{
            settings.setDarkMode(isChecked);
            db.userSettingsDao().updateUserSettings(settings);
        }

    }

    public UserSettings getUserSettings(String uid) {
        return db.userSettingsDao().getSettingsById(uid);
    }

    public void insertUserSettings(UserSettings settings) {
        db.userSettingsDao().insertUserSettings(settings);
    }

    public void updateUserSettings(UserSettings settings) {
        db.userSettingsDao().updateUserSettings(settings);
    }
}
