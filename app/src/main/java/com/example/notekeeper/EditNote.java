package com.example.notekeeper;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditNote extends AppCompatActivity {

    private static final String TAG = "EditNote";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note);
        Log.d(TAG, "onCreate: called ");
    }
}
