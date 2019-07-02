package com.example.chatbuddy.data.db.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.chatbuddy.data.db.local.model.UserSettings;

@Dao
public interface UserSettingsDao {
    @Query("SELECT * FROM UserSettings WHERE id=:id")
    UserSettings getSettingsById(String id);

    @Insert
    void insertUserSettings(UserSettings feed);

    @Delete
    void deleteUserSettings(UserSettings feed);

    @Update
    void updateUserSettings(UserSettings feed);
}
