package com.example.notekeeper.utils;

import android.content.Context;

import com.example.notekeeper.Activity2;
import com.example.notekeeper.MainActivity;
import com.example.notekeeper.adapter.NoteAdapter;
import com.example.notekeeper.model.NoteModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SharedPrefs {
   private Context context;
   NoteAdapter noteAdapter;
    public SharedPrefs(Context context)
    {
        this.context = context;
    }



    /*adds new note to notemodel
    * @param note  this is the new note
    * */
    public void addNote(NoteModel note){

        ArrayList<NoteModel> allNotes = new ArrayList<NoteModel>();
        allNotes = getAllNotes();
        allNotes.add(note);




        String serialisedNotes = null;
        try {
            serialisedNotes = ObjectSerializer.serialize(allNotes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).edit().putString("allNotes",serialisedNotes).commit();//saves serialised string to sharedprefs

    }

    public void removeNote(NoteModel note){

        ArrayList<NoteModel> allnotes = getAllNotes();
        for (Iterator<NoteModel> del = allnotes.iterator(); del.hasNext();){
            if (del.next().getRandomID() == (note.getRandomID())){
                del.remove();

            }

        }


        String serialisedNotes = null;
        try {
            serialisedNotes = ObjectSerializer.serialize(allnotes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).edit().putString("allNotes",serialisedNotes).commit();


    }

    public ArrayList<NoteModel> getAllNotes(){
        String serialisedNotes = context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).getString("allNotes","No Notes Composed");//gets serialised string

        ArrayList<NoteModel> allNotes = new ArrayList<NoteModel>();
        //check if default
        if (serialisedNotes.equals("No notes Composed")){
            return allNotes;
        }

        try {
            allNotes = (ArrayList<NoteModel>) ObjectSerializer.deserialize(serialisedNotes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return allNotes;
    }

    public void editNote(NoteModel note){
        ArrayList<NoteModel> allNotes = getAllNotes();
        for(int i = 0; i < allNotes.size(); i++){
            NoteModel item = allNotes.get(i);//gets specific note at each position
            if(item.getRandomID() == (note.getRandomID())){           //checks if clicked note is being edited
                allNotes.remove(item);//removes old note
                allNotes.add(note);//adds new note
            }



        }

        String serialisedNotes = null;
        try {
            serialisedNotes = ObjectSerializer.serialize(allNotes);
        } catch (IOException e) {
            e.printStackTrace();
        }


        context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).edit().putString("allNotes",serialisedNotes).commit();//saves serialised string to sharedprefs






    }


}
