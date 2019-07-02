package com.example.chatbuddy.data.db.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UserSettings {
    @PrimaryKey
    @NonNull
    private String id;
    private String nickname;
    private Boolean darkMode;
    private String avatar;

    public UserSettings(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Boolean getDarkMode() {
        return darkMode;
    }

    public void setDarkMode(Boolean darkMode) {
        this.darkMode = darkMode;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
