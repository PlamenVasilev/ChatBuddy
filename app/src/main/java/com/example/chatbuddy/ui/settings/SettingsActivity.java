package com.example.chatbuddy.ui.settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.example.chatbuddy.R;
import com.example.chatbuddy.databinding.ActivitySettingsBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {

    private static final int MY_CAMERA_PERMISSION_CODE = 1000;
    private static final int CAMERA_REQUEST = 10000;
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

        binding.selectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
                    boolean hasCameraPermission = (ContextCompat.checkSelfPermission(SettingsActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);

                    if (!hasCameraPermission) {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    } else {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            }
        });
    }

    private void setToolbar() {
        this.setSupportActionBar(binding.settingsToolbar);
        ActionBar toolbar = this.getSupportActionBar();

        if (toolbar != null) {
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
        Snackbar.make(findViewById(android.R.id.content), getString(R.string.settings_saved), Snackbar.LENGTH_LONG)
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.permissions_granted), Snackbar.LENGTH_LONG).show();

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.permissions_denied), Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            presenter.setImage(photo);
        }
    }

    public void showLoader(){
        binding.progress.setVisibility(View.VISIBLE);
    }

    public void hideLoader(){
        binding.progress.setVisibility(View.GONE);
    }

    public void showError(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG).show();
    }
}
