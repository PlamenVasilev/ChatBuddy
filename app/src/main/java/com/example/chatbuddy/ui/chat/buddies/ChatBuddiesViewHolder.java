package com.example.chatbuddy.ui.chat.buddies;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.UserModel;

class ChatBuddiesViewHolder extends RecyclerView.ViewHolder {

    private ImageView avatar;
    private TextView nickname;
    private Button chat;
    private clickListener listener;

    ChatBuddiesViewHolder(@NonNull View itemView, final clickListener listener) {
        super(itemView);

        avatar = itemView.findViewById(R.id.buddy_avatar);
        nickname = itemView.findViewById(R.id.buddy_nickname);
        chat = itemView.findViewById(R.id.buddy_chat);

        this.listener = listener;
    }

    void setItem(final BuddyModel buddy) {
        nickname.setText(buddy.getNickname());
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onChatBuddy(buddy);
            }
        });
    }

    public interface clickListener{
        void onChatBuddy(BuddyModel buddy);
    }
}
