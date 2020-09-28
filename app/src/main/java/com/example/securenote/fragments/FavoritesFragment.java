package com.example.securenote.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.securenote.R;
import com.example.securenote.adapter.FavoritesAdapter;
import com.example.securenote.model.NoteModel;
import com.example.securenote.utils.SharedPrefs;

import java.util.ArrayList;


public class FavoritesFragment extends Fragment {

    RecyclerView mRecyclerview;
    FavoritesAdapter favoritesAdapter;
    SharedPrefs prefs;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView favTxt;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    public static FavoritesFragment newInstance() {
        FavoritesFragment fragment = new FavoritesFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View favView = inflater.inflate(R.layout.fragment_favorites, container, false);



        //Recyclerview instance
        mRecyclerview = favView.findViewById(R.id.favRecyclerView);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        //SharedPrefs instance
        prefs = new SharedPrefs(getContext());

        //SwipeRefreshLayout instance
        swipeRefreshLayout = favView.findViewById(R.id.favs_SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favoritesAdapter.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });





        favoritesAdapter = new FavoritesAdapter(getContext());
        mRecyclerview.setAdapter(favoritesAdapter);

        //Fav notes text
        favTxt = favView.findViewById(R.id.favNotes_text);



        return favView;
    }


    @Override
    public void onResume() {
        super.onResume();

        ArrayList<NoteModel> delNotes = new ArrayList<>();
        ArrayList<NoteModel> allNotes = prefs.getAllNotes();
        for (int i = 0; i < allNotes.size(); i++){
            NoteModel item = allNotes.get(i);

            if(item.getIsfavorited()){
                delNotes.add(item);
            }

        }

        if(favoritesAdapter != null){
            favoritesAdapter.refresh();
        }

// checks to display compose new note text
        if (delNotes.size() == 0){
            favTxt.setVisibility(View.VISIBLE);
        }
        else {
            favTxt.setVisibility(View.INVISIBLE);
        }
    }



}