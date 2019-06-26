package com.example.chatbuddy.ui.chat.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.FragmentChatSearchBinding;

public class ChatSearchFragment extends Fragment {

    private OnChatSearchFragmentListener mListener;
    private ChatSearchFragmentPresenter presenter;
    private FragmentChatSearchBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChatSearchFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat_search, container, false);

        return binding.getRoot();
    }

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        if (context instanceof OnChatSearchFragmentListener) {
//            mListener = (OnChatSearchFragmentListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnChatSearchFragmentListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnChatSearchFragmentListener {

    }
}
