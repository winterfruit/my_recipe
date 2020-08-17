package com.christina.pascal.meinerezepte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.zip.Inflater;


public class MainActivity extends AppCompatActivity {

        Toolbar toolbar;
        RecyclerView recyclerView;
        Adapter adapter;
        List<Note> notes;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
                toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));

                NoteDatabase db = new NoteDatabase(this);

                notes = db.getNotes();
                recyclerView = findViewById(R.id.listOfNotes);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter = new Adapter(this, notes);
                recyclerView.setAdapter(adapter);




                FloatingActionButton fab = findViewById(R.id.add);
                fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                startActivity(new Intent(MainActivity.this, AddNote.class));
                        }
                });

                }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.main_menu,menu);
                return true;
        }
}