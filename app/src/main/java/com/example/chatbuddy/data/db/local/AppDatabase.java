package com.example.chatbuddy.data.db.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.chatbuddy.data.db.local.dao.UserSettingsDao;
import com.example.chatbuddy.data.db.local.model.UserSettings;

@Database(entities = {UserSettings.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserSettingsDao userSettingsDao();
}
