package com.example.chatbuddy.data.db.remote.model;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageModel {
    public static final String DIR_OUT = "out";
    public static final String DIR_IN = "in";
    private String message;
    private Date created;
    private Boolean read;
    private String direction = DIR_OUT;

    public MessageModel() {
    }

    public MessageModel(String message) {
        this.message = message;
        this.created = new Date();
        this.read = false;
        this.direction = DIR_OUT;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public boolean isOutMessage() {
        return direction.equals(DIR_OUT);
    }
}
