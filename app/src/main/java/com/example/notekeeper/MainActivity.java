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

    //shared_prefs
    private EditText titleText;
    private EditText noteText;
    private Button saveButton;

    public static final String SHARED_PREFS = "sharedprefs";
    public static final String TEXT = "text";

    private String text1;
    private String text2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        noteAdapter = new NoteAdapter(this,getMyList());
        mRecyclerview.setAdapter(noteAdapter);

        add_note_fab = findViewById(R.id.fab);
        add_note_fab.setOnClickListener(this);

        //sharedprefs
        titleText =(EditText) findViewById(R.id.title_Text);
        noteText =(EditText) findViewById(R.id.note_text);
        saveButton =(Button) findViewById(R.id.saveBtn);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        loadData();
        updateViews();


    }


    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, titleText.getText().toString());
        editor.putString(TEXT, noteText.getText().toString());
        editor.apply();

        Toast.makeText(this,"Note saved",Toast.LENGTH_SHORT).show();

    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        text1 = sharedPreferences.getString(TEXT, "");
        text2 = sharedPreferences.getString(TEXT, "");

    }

    public void updateViews(){
        titleText.setText(text1);
        noteText.setText(text2);
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
