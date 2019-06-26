package com.example.chatbuddy.ui.chat.search;

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

import java.util.ArrayList;

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

        binding.searchRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        initSearchListener();

        return binding.getRoot();
    }

    private void initSearchListener() {
        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.searchNickname.getText().toString().isEmpty()){
                    binding.searchNickname.setError(getResources().getString(R.string.search_enter_nickname));
                }else if(binding.searchNickname.getText().toString().length()<3) {
                    binding.searchNickname.setError(getResources().getString(R.string.search_nickname_short));
                }else{
                    presenter.searchByNickname(binding.searchNickname.getText().toString());
                }
            }
        });
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

    void loadSearchData(ArrayList<UserModel> searchList) {
        ChatSearchAdapter adapter = new ChatSearchAdapter(searchList);

        binding.searchRecycler.setAdapter(adapter);
    }

    public interface OnChatSearchFragmentListener {

    }
}
