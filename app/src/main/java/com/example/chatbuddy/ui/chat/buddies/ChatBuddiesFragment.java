package com.example.chatbuddy.ui.chat.buddies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.FragmentChatBuddiesBinding;

public class ChatBuddiesFragment extends Fragment {

    private OnChatBuddiesFragmentListener mListener;
    private ChatBuddiesFragmentPresenter presenter;
    private FragmentChatBuddiesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChatBuddiesFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_buddies, container, false);

        return binding.getRoot();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof OnChatBuddiesFragmentListener) {
//            mListener = (OnChatBuddiesFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnChatBuddiesFragmentListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnChatBuddiesFragmentListener {

    }
}
