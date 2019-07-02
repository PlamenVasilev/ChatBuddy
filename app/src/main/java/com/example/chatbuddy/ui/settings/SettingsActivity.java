package com.example.chatbuddy.ui.settings;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.ActivitySettingsBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {

    ActivitySettingsBinding binding;
    private SettingsActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        presenter = new SettingsActivityPresenter(this);

        presenter.setValues();
        setToolbar();
        initListeners();
    }

    private void initListeners() {
        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.saveSettings();
            }
        });
    }

    private void setToolbar() {
        this.setSupportActionBar(binding.settingsToolbar);
        ActionBar toolbar = this.getSupportActionBar();

        if(toolbar != null){
            toolbar.setTitle(getString(R.string.settings));
            toolbar.setDisplayHomeAsUpEnabled(true);
            toolbar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void showSaveConfirmation() {
        Snackbar.make(findViewById(android.R.id.content), "Settings saved!", Snackbar.LENGTH_LONG)
                .show();
    }
}
