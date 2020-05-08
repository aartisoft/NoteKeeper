package com.example.notekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView noteList;

    public Button next;

    public void init (){
        next= (Button)findViewById(R.id.but1);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toy = new Intent(MainActivity.this,Activity2.class);
                startActivity(toy);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        noteList=(ListView)findViewById(R.id.list);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Note 1");
        arrayList.add("Note 2");
        arrayList.add("Note 3");
        arrayList.add("Note 4");
        arrayList.add("Note 5");
        arrayList.add("Note 6");
        arrayList.add("Note 7");
        arrayList.add("Note 8");
        arrayList.add("Note 9");
        arrayList.add("Note 10");
        arrayList.add("Note 11");
        arrayList.add("Note 12");
        arrayList.add("Note 13"); 

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
        noteList.setAdapter(arrayAdapter);
    }
}
