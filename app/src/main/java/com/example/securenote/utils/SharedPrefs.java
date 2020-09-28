package com.example.securenote.utils;

import android.content.Context;

import com.example.securenote.model.NoteModel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public class SharedPrefs {
   private Context context;

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

        //sort ArrayList by date
        Collections.sort(allNotes, new Comparator<NoteModel>() {
            @Override
            public int compare(NoteModel o1, NoteModel o2) {

                SimpleDateFormat sdf = new SimpleDateFormat("EEE,d MMM yyyy HH:mm:ss", Locale.getDefault());


                try {
                    Date date1 = sdf.parse(o1.getTime());
                    Date date2 = sdf.parse(o2.getTime());



                    return date1.compareTo(date2);

                } catch (ParseException e) {
                    e.printStackTrace();

                    return 0;
                }


            }
        });


        return allNotes;
    }

    public void updateNote(NoteModel note){
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


    public void saveToPref( String str) {

        context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).edit().putString("code",str).commit();//saves serialised string to sharedprefs

    }

    public  String getCode(Context context) {

        return         context.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE).getString("code","str");//saves serialised string to sharedprefs

    }






}
