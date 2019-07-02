package com.example.chatbuddy.ui.chat.search;

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
import com.example.chatbuddy.data.db.remote.model.UserModel;
import com.example.chatbuddy.databinding.FragmentChatSearchBinding;
import com.example.chatbuddy.ui.chat.ChatFragment;

import java.util.ArrayList;

public class ChatSearchFragment extends Fragment {

    private ChatSearchFragmentPresenter presenter;
    FragmentChatSearchBinding binding;
    ChatFragment.OnChatFragmentListener mListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChatSearchFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_search, container, false);

        binding.searchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        initSearchListener();

        return binding.getRoot();
    }

    private void initSearchListener() {
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.searchBuddy();
            }
        });
    }


    void loadSearchData(ArrayList<UserModel> searchList) {
        ChatSearchAdapter adapter = new ChatSearchAdapter(searchList, clickListener());

        binding.searchRecycler.setAdapter(adapter);
    }

    private ChatSearchViewHolder.clickListener clickListener(){
        return new ChatSearchViewHolder.clickListener() {
            @Override
            public void onAddBuddy(UserModel user) {
                presenter.addBuddy(user);
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
