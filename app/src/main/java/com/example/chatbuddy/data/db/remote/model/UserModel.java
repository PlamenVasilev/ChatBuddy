package com.example.chatbuddy.data.db.remote.model;

public class UserModel {
    private String uid;
    private String email;
    private String nickname;
    private String avatar;

    public UserModel() {

    }

    public UserModel(String uid, String email, String nickname) {
        this.uid = uid;
        this.email = email;
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        if (email == null){
            return email;
        }

        return email.toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        if (nickname == null){
            return nickname;
        }

        return nickname.toLowerCase();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
