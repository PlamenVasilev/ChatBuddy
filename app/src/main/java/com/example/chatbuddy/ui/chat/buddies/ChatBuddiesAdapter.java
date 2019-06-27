package com.example.chatbuddy.ui.chat.buddies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;

import java.util.ArrayList;

public class ChatBuddiesAdapter extends RecyclerView.Adapter {
    private ArrayList<BuddyModel> list;
    private ChatBuddiesViewHolder.clickListener clickListener;

    ChatBuddiesAdapter(ArrayList<BuddyModel> searchList, ChatBuddiesViewHolder.clickListener clickListener) {
        this.list = searchList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.buddies_holder, viewGroup, false);
        return new ChatBuddiesViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BuddyModel buddy = list.get(position);

        ((ChatBuddiesViewHolder)holder).setItem(buddy);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
