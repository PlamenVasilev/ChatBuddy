package com.example.chatbuddy.ui.chat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.FragmentChatBinding;

import java.util.Objects;

public class ChatFragment extends Fragment {

    private OnChatFragmentListener mListener;
    private FragmentChatBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(binding.chatToolbar);

        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnChatFragmentListener) {
            mListener = (OnChatFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnChatFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnChatFragmentListener {

    }
}
