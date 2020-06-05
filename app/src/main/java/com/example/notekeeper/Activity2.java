package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.example.notekeeper.model.NoteModel;
import com.example.notekeeper.utils.SharedPrefs;

public class Activity2 extends AppCompatActivity {

    //shared_prefs
    private EditText titleText;
    private EditText noteText;
    private Button saveButton;

    public static final String SHARED_PREFS = "sharedprefs";
    public static final String TITLE = "title";
    public static final String NOTE = "note";

    private String text1;
    private String text2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);


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

        //get extras
        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");
        String description = intent.getStringExtra("DESCRIPTION");

        if (title != null && description != null){
            titleText.setText(title);
            noteText.setText(description);
        }
        else {

        }






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
        note.setImg(R.drawable.note_icon);

        SharedPrefs prefs = new SharedPrefs(this);
        prefs.addNote(note);


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
