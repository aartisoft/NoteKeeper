package com.example.notekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notekeeper.adapter.NoteAdapter;
import com.example.notekeeper.fragments.FavoritesFragment;
import com.example.notekeeper.fragments.HomeFragment;
import com.example.notekeeper.fragments.RecycleBinFragment;
import com.example.notekeeper.fragments.RemindersFragment;
import com.example.notekeeper.model.NoteModel;
import com.example.notekeeper.utils.SharedPrefs;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    //BottomNavigation
    BottomNavigationView bottomNavigation;

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set toolbar as actionbar
         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("NoteKeeper");

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);













        //bottomNavigation instance

        bottomNavigation = findViewById(R.id.bottom_navigation);
        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {

                            case R.id.favorites:
                                openFragment(FavoritesFragment.newInstance());
                                toolbar.setTitle("Favourites");
                                return true;

                            case R.id.recycle_bin:
                                openFragment(RecycleBinFragment.newInstance());
                                toolbar.setTitle("Recycle Bin");
                                return true;

                            /*case R.id.reminders:
                                openFragment(RemindersFragment.newInstance());
                                setTitle("Reminders");
                                return true;*/

                            case R.id.home:
                                openFragment(HomeFragment.newInstance());
                                toolbar.setTitle("All Notes");
                                return true;

                            default:
                                return  false;
                        }

                    }
                };

        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

       openFragment(HomeFragment.newInstance());

    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();


        if(fragment instanceof  RecycleBinFragment){
            //show recycle toolbar
            getSupportActionBar().setCustomView(R.layout.bintoolbar_layout);

        } else{
            //set default toolbar
            getSupportActionBar().setCustomView(R.layout.default_toolbar_layout);
        }
    }


}
