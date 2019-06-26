package com.example.chatbuddy.ui.chat.search;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.UserModel;

class ChatSearchViewHolder extends RecyclerView.ViewHolder {

    TextView nickname;
    TextView email;
    Button add;

    ChatSearchViewHolder(@NonNull View itemView) {
        super(itemView);

        nickname = itemView.findViewById(R.id.holder_nickname);
        email = itemView.findViewById(R.id.holder_email);
        add = itemView.findViewById(R.id.holder_add);
    }

    void setItem(UserModel user) {
        nickname.setText(user.getNickname());
        email.setText(user.getEmail());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO !!!!
            }
        });
    }
}
