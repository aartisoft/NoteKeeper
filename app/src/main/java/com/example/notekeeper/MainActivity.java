package com.example.notekeeper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notekeeper.adapter.NoteAdapter;
import com.example.notekeeper.model.NoteModel;
import com.example.notekeeper.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NoteAdapter.OnNoteListener {


    private static final String TAG = "MainActivity";
    RecyclerView mRecyclerview;
    NoteAdapter noteAdapter;

    FloatingActionButton add_note_fab;
    // Menu drawer variable
    private DrawerLayout drawer;

    //refreshLayout
    SwipeRefreshLayout swipeRefreshLayout;

    SharedPrefs prefs;
    ArrayList<NoteModel>myList;

    TextView ctextView;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar as actionbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("My Notes");





        //Recyclerview instances
        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        //sharedprefs instance
         final SharedPrefs prefs = new SharedPrefs(this);
         myList = prefs.getAllNotes();


        ctextView = findViewById(R.id.composeNote_text);
        if (myList.size() == 0){
            ctextView.setVisibility(View.VISIBLE);
        }
        else{
            ctextView.setVisibility(View.INVISIBLE);
        }

        noteAdapter = new NoteAdapter(this,prefs.getAllNotes(),this);
        mRecyclerview.setAdapter(noteAdapter);



        //fab instances
        add_note_fab = findViewById(R.id.fab);
        add_note_fab.setOnClickListener(this);

        //menu drawer instances
        //drawer = findViewById(R.id.drawer_layout);
        //creates toggle
       /* ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer,
                toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();//Rotates humbager icon to get over drawer*/

        //swiperefreshlayout instance
        swipeRefreshLayout = findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noteAdapter.refresh(prefs.getAllNotes());
                swipeRefreshLayout.setRefreshing(false);
            }
        });








    }

    @Override
    protected void onResume() {
        super.onResume();
        //refresh recyclerview
        SharedPrefs prefs = new SharedPrefs(this);
        if(noteAdapter != null)
        noteAdapter.refresh(prefs.getAllNotes());

// checks to display compose new note text
        if (prefs.getAllNotes().size() == 0){
            ctextView.setVisibility(View.VISIBLE);
        }
        else{
            ctextView.setVisibility(View.INVISIBLE);
        }
    }

    /* private ArrayList<NoteModel> getMyList(){

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

        }*/
    //On fab button click
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this,Activity2.class);
        startActivity(intent);
        Log.v("onclicklog","Opened a new note");

    }
    //On note click
    @Override
    public void OnNoteClick(NoteModel noteModel) {
        Log.d(TAG, "OnNoteClick: clicked");
        Intent intent = new Intent(this,Activity2.class);
        intent.putExtra("TITLE",noteModel.getTitle() );
        intent.putExtra("DESCRIPTION",noteModel.getDescription());
        intent.putExtra("ID",noteModel.getRandomID());
        startActivity(intent);

    }
}
