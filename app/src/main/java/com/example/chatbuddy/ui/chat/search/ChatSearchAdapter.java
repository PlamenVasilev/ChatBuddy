package com.example.chatbuddy.ui.chat.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.UserModel;

import java.util.ArrayList;

public class ChatSearchAdapter extends RecyclerView.Adapter {
    private ArrayList<UserModel> list;

    ChatSearchAdapter(ArrayList<UserModel> searchList) {
        this.list = searchList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.search_holder, viewGroup, false);
        return new ChatSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserModel user = list.get(position);

        ((ChatSearchViewHolder)holder).setItem(user);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
