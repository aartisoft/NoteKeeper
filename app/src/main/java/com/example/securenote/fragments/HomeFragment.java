package com.example.securenote.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.securenote.Activity2;
import com.example.securenote.R;
import com.example.securenote.adapter.NoteAdapter;
import com.example.securenote.model.NoteModel;
import com.example.securenote.utils.SharedPrefs;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    RecyclerView mRecyclerview;
    NoteAdapter noteAdapter;
    SharedPrefs prefs;
    FloatingActionButton add_note_fab;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<NoteModel>myList;
    TextView cText;




    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
      HomeFragment fragment = new HomeFragment();

      return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_home, container, false);


       /* Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("All Notes");*/

              //RecyclerView instance
        mRecyclerview = rootView.findViewById(R.id.recyclerView);
        mRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        //SharedPrefs instance
        prefs = new SharedPrefs(getContext());


        noteAdapter = new NoteAdapter(getContext(), new NoteAdapter.OnNoteListener() {
            @Override
            public void OnNoteClick(NoteModel noteModel) {
                Log.d(TAG, "OnNoteClick: clicked");
                Intent intent = new Intent(getContext(), Activity2.class);
                intent.putExtra("TITLE",noteModel.getTitle() );
                intent.putExtra("DESCRIPTION",noteModel.getDescription());
                intent.putExtra("ID",noteModel.getRandomID());
                startActivity(intent);

            }
        });

        mRecyclerview.setAdapter(noteAdapter);

        //Fab instance
        add_note_fab = rootView.findViewById(R.id.fab);
        add_note_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Activity2.class);
                startActivity(intent);
                Log.v("onclicklog","Opened a new note");
            }
        });


        //SwipeRefresh layout instance
        swipeRefreshLayout = rootView.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                noteAdapter.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        //Compose New Note Text instance
        myList = prefs.getAllNotes();
        cText = rootView.findViewById(R.id.composeNote_text);







        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        //refresh recyclerview
        SharedPrefs prefs = new SharedPrefs(getContext());
        if(noteAdapter != null){
            noteAdapter.refresh();
        }

// checks to display compose new note text
        if (myList.size() == 0){
            cText.setVisibility(View.VISIBLE);
        }
        else {
            cText.setVisibility(View.INVISIBLE);
        }
    }


}