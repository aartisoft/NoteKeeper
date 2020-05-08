package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        myAdapter = new MyAdapter(this,getMyList());
        mRecyclerview.setAdapter(myAdapter);
    }

    private ArrayList<Model> getMyList(){

        ArrayList<Model>models = new ArrayList<>();

        Model m = new Model();
        m.setTitle("Note Title");
        m.getDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        models.add(m);

        m = new Model();
        m.setTitle("Note Title");
        m.getDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        models.add(m);

        m = new Model();
        m.setTitle("Note Title");
        m.getDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        models.add(m);

        m = new Model();
        m.setTitle("Note Title");
        m.getDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        models.add(m);

        m = new Model();
        m.setTitle("Note Title");
        m.getDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        models.add(m);

        m = new Model();
        m.setTitle("Note Title");
        m.getDescription("This is a description of a note...");
        m.setImg(R.drawable.note_icon);
        models.add(m);

        return models;

    }
}
