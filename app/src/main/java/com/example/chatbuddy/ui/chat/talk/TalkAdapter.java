package com.example.chatbuddy.ui.chat.talk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.FbCallback;
import com.example.chatbuddy.data.db.remote.FbDatabase;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.MessageModel;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

public class TalkAdapter extends RecyclerView.Adapter {
    private static final int TYPE_OUT = R.layout.message_out_holder;
    private static final int TYPE_IN = R.layout.message_in_holder;
    private ArrayList<MessageModel> list;
    private BuddyModel buddy;

    TalkAdapter(ArrayList<MessageModel> messages, BuddyModel buddy) {
        this.list = messages;
        this.buddy = buddy;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);

        return new TalkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel message = list.get(position);

        ((TalkViewHolder)holder).setItem(message, buddy);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        MessageModel message = list.get(position);

        if(message.isOutMessage()){
            return TYPE_OUT;
        }else{
            return TYPE_IN;
        }
    }
}
