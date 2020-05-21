package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notekeeper.adapter.NoteAdapter;
import com.example.notekeeper.model.NoteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    RecyclerView mRecyclerview;
    NoteAdapter noteAdapter;

    FloatingActionButton add_note_fab;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter = new NoteAdapter(this,getMyList());
        mRecyclerview.setAdapter(noteAdapter);

        add_note_fab = findViewById(R.id.fab);
        add_note_fab.setOnClickListener(this);




    }








    private ArrayList<NoteModel> getMyList(){

        ArrayList<NoteModel>NoteModels = new ArrayList<>();

        NoteModel m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        NoteModels.add(m);

        m = new NoteModel();
        m.setTitle("Note Title");
        m.setDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        m.name = "jina";
        NoteModels.add(m);

        return NoteModels;

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
        Log.v("onclicklog","Opened a new note");

    }
}
