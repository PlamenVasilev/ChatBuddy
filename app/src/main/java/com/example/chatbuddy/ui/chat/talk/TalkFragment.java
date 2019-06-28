package com.example.chatbuddy.ui.chat.talk;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.data.db.remote.model.MessageModel;
import com.example.chatbuddy.databinding.FragmentTalkBinding;

import java.util.ArrayList;
import java.util.Objects;

public class TalkFragment extends Fragment {

    private OnTalkFragmentListener mListener;
    private TalkFragmentPresenter presenter;
    private FragmentTalkBinding binding;
    private BuddyModel buddy;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new TalkFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        buddy = (BuddyModel) Objects.requireNonNull(getArguments()).getSerializable("buddy");

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_talk, container, false);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(binding.talkToolbar);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //linearLayoutManager.setReverseLayout(true);
        binding.talkRecycler.setLayoutManager(linearLayoutManager);

        setToolbar();
        initListeners();
        presenter.loadMessages(buddy);

        return binding.getRoot();
    }

    private void initListeners() {
        binding.talkSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = binding.talkText.getText().toString();
                if(!msg.isEmpty() && msg.length() > 3){
                    presenter.sendMessage(buddy, new MessageModel(msg));
                    binding.talkText.setText(null);
                }
            }
        });
    }

    private void setToolbar() {
        binding.talkToolbar.setTitle(buddy.getNickname());
        binding.talkToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        binding.talkToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Objects.requireNonNull(getActivity()).onBackPressed();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnTalkFragmentListener) {
            mListener = (OnTalkFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnTalkFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    void showMessages(ArrayList<MessageModel> messages) {

        TalkAdapter adapter = new TalkAdapter(messages);

        binding.talkRecycler.setAdapter(adapter);
    }


    public interface OnTalkFragmentListener {

    }
}
