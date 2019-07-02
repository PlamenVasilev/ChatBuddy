package com.example.chatbuddy.ui.chat.talk;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.MessageModel;

import java.text.SimpleDateFormat;

class TalkViewHolder extends RecyclerView.ViewHolder {

    private ImageView avatar;
    private TextView message;
    private TextView date;
    private TextView seen;

    TalkViewHolder(@NonNull View itemView) {
        super(itemView);

        avatar = itemView.findViewById(R.id.message_avatar);
        message = itemView.findViewById(R.id.message_text);
        date = itemView.findViewById(R.id.message_date);
        seen = itemView.findViewById(R.id.message_seen);
    }

    void setItem(final MessageModel msg) {
        message.setText(msg.getMessage());

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("H:m dd.MM.yyyy");
        date.setText(formatter.format(msg.getCreated()));

        if(msg.getRead()){
            seen.setText("Seen");
        }
    }
}
