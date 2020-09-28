package com.example.securenote.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.securenote.R;
import com.example.securenote.adapter.RecycleBinAdapter;
import com.example.securenote.model.NoteModel;
import com.example.securenote.utils.SharedPrefs;

import java.util.ArrayList;


public class RecycleBinFragment extends Fragment {

    RecyclerView mRecyclerView;
    RecycleBinAdapter recycleBinAdapter;
    SharedPrefs prefs;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView binTxt;
    TextView binTitle;



    public RecycleBinFragment() {
        // Required empty public constructor
    }


    public static RecycleBinFragment newInstance() {
        RecycleBinFragment fragment = new RecycleBinFragment();


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
        View recycleBin_View = inflater.inflate(R.layout.fragment_recycle_bin, container, false);

        binTitle = recycleBin_View.findViewById(R.id.emptyText);



        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(true);

        View view = ((AppCompatActivity) getActivity()).getSupportActionBar().getCustomView();
        ImageView emptybinIcon = view.findViewById(R.id.emptyBin);
        emptybinIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // NoteModel note = new NoteModel();


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Empty Recycle Bin");
                builder.setMessage("Are you sure you want to empty Recycle Bin?" +
                        "All notes in the Recycle Bin will be permanently deleted!");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ArrayList<NoteModel> allNotes = prefs.getAllNotes();
                        for(int i = 0; i < allNotes.size(); i++){
                            NoteModel item = allNotes.get(i);

                            if(item.getDeleted()){

                                prefs.removeNote(item);

                            }

                        }

                       recycleBinAdapter.refresh();

                    }

                });



                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();



            }
        });









        //Recyclerview instance
        mRecyclerView = recycleBin_View.findViewById(R.id.RBin_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Sharedprefs instance
        prefs = new SharedPrefs(getContext());

        //SwipeRefreshLayout
        swipeRefreshLayout = recycleBin_View.findViewById(R.id.RBin_SwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recycleBinAdapter.refresh();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        recycleBinAdapter = new RecycleBinAdapter(getContext());
        mRecyclerView.setAdapter(recycleBinAdapter);

        //Bin text
        binTxt = recycleBin_View.findViewById(R.id.bin_text);




        return recycleBin_View;
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<NoteModel> delNotes = new ArrayList<>();
        ArrayList<NoteModel> allNotes = prefs.getAllNotes();
        for (int i = 0; i < allNotes.size(); i++){
            NoteModel item = allNotes.get(i);

            if(item.getDeleted()){
                delNotes.add(item);
            }

        }

        if(recycleBinAdapter != null){
            recycleBinAdapter.refresh();
        }

// checks to display compose new note text
        if (delNotes.size() == 0){
            binTxt.setVisibility(View.VISIBLE);
        }
        else {
            binTxt.setVisibility(View.INVISIBLE);
        }
    }
}