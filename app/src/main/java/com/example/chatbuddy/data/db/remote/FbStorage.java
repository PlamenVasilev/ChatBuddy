package com.example.chatbuddy.data.db.remote;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class FbStorage {

    private static final String AVATARS = "avatars";
    private static FbStorage instance;
    private final FirebaseUser authUser;
    private StorageReference storageRef;

    public static FbStorage getInstance() {
        if (instance == null) {
            instance = new FbStorage();
        }
        return instance;
    }

    private FbStorage() {
        authUser = FirebaseAuth.getInstance().getCurrentUser();
        storageRef = FirebaseStorage.getInstance().getReference();
    }

    public void uploadAvatar(byte[] avatarBytes, final FbCallback.onUploadCompleted onUploadCompleted) {
        final StorageReference imageRef = storageRef.child(AVATARS+"/"+authUser.getUid()+".png");
        imageRef.putBytes(avatarBytes)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                String url = uri.toString();
                                onUploadCompleted.onComplete(url);
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        onUploadCompleted.onError(exception);
                    }
                });
    }
}
