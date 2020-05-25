package com.example.notekeeper.utils;

import android.content.Context;

import com.example.notekeeper.model.NoteModel;

import java.io.IOException;
import java.util.ArrayList;

public class SharedPrefs {
   private Context context;
    public SharedPrefs(Context context) {
        this.context = context;


    }
    /*adds new note to notemodel
    * @param note  this is the new note
    * */
    public void addNote(NoteModel note){
        ArrayList<NoteModel> allNotes = new ArrayList<NoteModel>();
        allNotes = getAllNotes();
        allNotes.add(note);

        String serialisedNote = null;
        try {
            serialisedNote = ObjectSerializer.serialize(allNotes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).edit().putString("allNotes",serialisedNote).commit();

    }

    public void removeNote(){

    }

    public ArrayList<NoteModel> getAllNotes(){
        String serialisedlist = context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).getString("allNotes","No Notes Composed");

        ArrayList<NoteModel> allNotes = new ArrayList<NoteModel>();
        //check if default
        if (serialisedlist.equals("No notes Composed")){
            return allNotes;
        }

        try {
            allNotes = (ArrayList<NoteModel>) ObjectSerializer.deserialize(serialisedlist);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return allNotes;
    }

}
