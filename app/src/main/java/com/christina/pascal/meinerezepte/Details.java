package com.christina.pascal.meinerezepte;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {
    Long id;
    TextView mDetails,mZubereitungszeit;
    NoteDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));


        mDetails = findViewById(R.id.noteDesc);
        mZubereitungszeit = findViewById(R.id.mZubereitungszeit);

        Intent i = getIntent();
        id = i.getLongExtra("ID",0);

        db = new NoteDatabase(this);
        note = db.getNote(id);

        mDetails.setText(note.getContent());
        mZubereitungszeit.setText(note.getZubereitungszeit());

        getSupportActionBar().setTitle(note.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Toast.makeText(this,"Title -->" + note.getTitle(), Toast.LENGTH_SHORT).show();
        //Delete Button bei 23:15
        //FloatingActionButton fab = findViewById(R.id.edit_fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        startActivity(new Intent(Details.this, Edit.class).putExtra("ID",note.getID()));
        //
        //        Toast.makeText(Details.this, "Delete Note", Toast.LENGTH_SHORT).show();
        //   }
        //});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.edit_menu_button) {
            startActivity(new Intent(Details.this, Edit.class).putExtra("ID",note.getID()));
        }
        if (item.getItemId() == R.id.delete_menu_button) {
            db.deleteNote(note.getID());
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}