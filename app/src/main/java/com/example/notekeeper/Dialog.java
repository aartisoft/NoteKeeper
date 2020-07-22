package com.example.notekeeper;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.notekeeper.model.NoteModel;
import com.example.notekeeper.utils.SharedPrefs;

public class Dialog extends AppCompatDialogFragment {
    private SharedPrefs prefs;
    private NoteModel notes;
    public Dialog(NoteModel note){
        this.notes = note;

    }


    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        prefs = new SharedPrefs(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete Note");
        builder.setMessage("Are you sure you want to delete this note?");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                prefs.removeNote(notes);
                Toast.makeText(getActivity(),"Note deleted",Toast.LENGTH_SHORT).show();
                dialog.cancel();


            }
        });



        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        return builder.create();

    }
}
