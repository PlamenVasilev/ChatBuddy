package com.example.chatbuddy.ui.chat;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatbuddy.R;
import com.example.chatbuddy.data.db.remote.model.BuddyModel;
import com.example.chatbuddy.databinding.FragmentChatBinding;
import com.example.chatbuddy.ui.chat.buddies.ChatBuddiesFragment;
import com.example.chatbuddy.ui.chat.search.ChatSearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class ChatFragment extends Fragment {

    private OnChatFragmentListener mListener;
    private FragmentChatBinding binding;
    private ChatFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ChatFragmentPresenter(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_chat, container, false);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(binding.chatToolbar);

        initToolbarListener();
        initBottomNavigationListener();
        showChatBuddies(false);

        return binding.getRoot();
    }

    private void initToolbarListener() {
        binding.chatToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.logout:
                        presenter.onLogoutClicked();
                        break;
                    case R.id.settings:
                        // todo .. what ?
                        break;
                }
                return true;
            }
        });
    }

    private void initBottomNavigationListener() {
        binding.chatNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.chat_buddies:
                        presenter.onChatBuddiesClick();
                        break;
                    case R.id.chat_search:
                        presenter.onSearchClick();
                        break;
                }
                return true;
            }
        });
    }

    void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setMessage(R.string.confirm_logout)
                .setTitle(R.string.confirm_logout_title)
                .setPositiveButton(R.string.confirm_logout_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.logout();
                        mListener.showLoginScreen();
                    }
                })
                .setNegativeButton(R.string.confirm_logout_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void openScreen(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction()
                .replace(R.id.chat_frame, fragment);
        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }


    void showChatBuddies(boolean addToBackStack) {
        openScreen(new ChatBuddiesFragment(), addToBackStack);
    }

    void showSearch(boolean addToBackStack) {
        openScreen(new ChatSearchFragment(), addToBackStack);
    }

    @Override
    public void onAttach(@NonNull Context context) {
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
        void showLoginScreen();
        void showTalkScreen(BuddyModel buddy);
    }
}
