package com.example.chatbuddy.data.db.remote.model;

import java.io.Serializable;

public class BuddyModel implements Serializable {
    private String uid;
    private String nickname;

    public BuddyModel() {
    }

    public BuddyModel(String uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
