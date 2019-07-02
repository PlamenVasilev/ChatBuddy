package com.example.chatbuddy.ui.chat.buddies;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.databinding.FragmentChatBuddiesBinding;
import com.example.chatbuddy.ui.chat.ChatFragment;

import java.util.ArrayList;

public class ChatBuddiesFragment extends Fragment {

    private ChatBuddiesFragmentPresenter presenter;
    private FragmentChatBuddiesBinding binding;
    ChatFragment.OnChatFragmentListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChatBuddiesFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_buddies, container, false);

        binding.buddiesRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.loadBuddiesList();

        return binding.getRoot();
    }

    void loadBuddiesData(ArrayList<BuddyModel> buddyList) {
        ChatBuddiesAdapter adapter = new ChatBuddiesAdapter(buddyList, clickListener());

        binding.buddiesRecycler.setAdapter(adapter);
    }

    private ChatBuddiesViewHolder.clickListener clickListener(){
        return new ChatBuddiesViewHolder.clickListener() {
            @Override
            public void onChatBuddy(BuddyModel buddy) {
                presenter.onBuddyClicked(buddy);
            }
        };
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ChatFragment.OnChatFragmentListener) {
            mListener = (ChatFragment.OnChatFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnChatFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
