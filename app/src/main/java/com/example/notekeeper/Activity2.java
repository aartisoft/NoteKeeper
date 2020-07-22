package com.example.notekeeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.notekeeper.adapter.NoteAdapter;
import com.example.notekeeper.model.NoteModel;
import com.example.notekeeper.utils.SharedPrefs;

import java.util.ArrayList;
import java.util.Random;

public class Activity2 extends AppCompatActivity {

    //shared_prefs
    private EditText titleText;
    private EditText noteText;
    private Button saveButton;
    private int randomID;
    private String title;
    private String description;
    private SharedPrefs prefs;
    NoteAdapter noteAdapter;

    public static final String SHARED_PREFS = "sharedprefs";
    public static final String TITLE = "title";
    public static final String NOTE = "note";

    private Boolean isEditing;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);




        Toolbar toolbar = findViewById(R.id.topAppBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //sharedprefs
        titleText =(EditText) findViewById(R.id.title_Text);
        noteText =(EditText) findViewById(R.id.note_text);
        //saveButton =(Button) findViewById(R.id.saveBtn);
        prefs = new SharedPrefs(this);






        /*saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                
            }
        });
        */



        //get extras
        Intent intent = getIntent();
         title = intent.getStringExtra("TITLE");
         description = intent.getStringExtra("DESCRIPTION");
         randomID = intent.getIntExtra("ID",0);

       //isEditing
        if (title != null && description != null){
            isEditing = true;
            titleText.setText(title);
            noteText.setText(description);
        }
        else {
            //Add new note
            isEditing = false;



        }






    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.save){
            saveData();


        }
        if (id == R.id.delete){
            NoteModel note = new NoteModel();
            note.setRandomID(randomID);



            Dialog alertdialog = new Dialog(note);
            alertdialog.show(getSupportFragmentManager(), "alertdialog");



        }
        return super.onOptionsItemSelected(item);
    }

    public void saveData(){
       /** SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("title", titleText.getText().toString());
        editor.putString(NOTE, noteText.getText().toString());
        editor.apply(); **/

       /** getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE).edit().putString("title", titleText.getText().toString()).commit();
        getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).edit().putString("note", noteText.getText().toString()).commit();**/
       //note model that will contain saved data
        NoteModel note = new NoteModel();
        note.setTitle(titleText.getText().toString());
        note.setDescription(noteText.getText().toString());



        //note.setImg(R.drawable.note_icon);

        SharedPrefs prefs = new SharedPrefs(this);
        if(isEditing==false){
            note.setRandomID(new Random().nextInt());//generate ID for new note

            prefs.addNote(note);

        }
        else{

            note.setRandomID(randomID);
            prefs.editNote(note);
        }


        Toast.makeText(this,"Note saved",Toast.LENGTH_SHORT).show();





    }




   /* public void loadData(){
       SharedPreferences sharedPreferences = getSharedPreferences("sharedprefs",MODE_PRIVATE);
        text1 = sharedPreferences.getString(TITLE, "");
        text2 = sharedPreferences.getString(NOTE, "");
        text1 = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).getString("title","");
        text2 = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).getString("note","");

    }

    public void updateViews(){
        titleText.setText(text1);
        noteText.setText(text2);

    }
*/

}
