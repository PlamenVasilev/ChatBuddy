package com.example.chatbuddy.ui.chat.search;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.UserModel;

class ChatSearchViewHolder extends RecyclerView.ViewHolder {

    private TextView nickname;
    private TextView email;
    private Button add;
    private clickListener listener;

    ChatSearchViewHolder(@NonNull View itemView, final clickListener listener) {
        super(itemView);

        nickname = itemView.findViewById(R.id.holder_nickname);
        email = itemView.findViewById(R.id.holder_email);
        add = itemView.findViewById(R.id.holder_add);
        this.listener = listener;
    }

    void setItem(final UserModel user) {
        nickname.setText(user.getNickname());
        email.setText(user.getEmail());
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddBuddy(user);
            }
        });
    }

    public interface clickListener{
        void onAddBuddy(UserModel user);
    }
}
