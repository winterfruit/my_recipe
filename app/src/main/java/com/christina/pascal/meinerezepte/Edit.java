package com.christina.pascal.meinerezepte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class Edit extends AppCompatActivity {
    Toolbar toolbar;
    EditText noteTitle,noteDetails, noteZubereitungszeit;
    Calendar c;
    String todaysDate;
    String currentTime;
    NoteDatabase db;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent i = getIntent();
        Long id = i.getLongExtra("ID",0);
        db = new NoteDatabase(this);
        note = db.getNote(id);



        noteTitle = findViewById(R.id.noteTitle);
        noteDetails = findViewById(R.id.noteDetails);
        noteZubereitungszeit = findViewById(R.id.noteZubereitungszeit);

        noteTitle.setText(note.getTitle());
        noteDetails.setText(note.getContent());
        noteZubereitungszeit.setText(note.getZubereitungszeit());

        getSupportActionBar().setTitle(note.getTitle());


        FloatingActionButton fab = findViewById(R.id.save_edit);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note.setTitle(noteTitle.getText().toString());
                note.setContent(noteDetails.getText().toString());
                note.setZubereitungszeit(noteZubereitungszeit.getText().toString());
                int id = db.editNote(note);
                if (id==note.getID()) {
                    Toast.makeText(Edit.this, "Note Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Edit.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
                startActivity(new Intent(getApplicationContext(),Details.class).putExtra("ID",note.getID()));
            }
        });



        noteTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Aktuelle Zeit und Datum
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.DAY_OF_MONTH) +"/"+(c.get(Calendar.MONTH)+1) +"/"+c.get(Calendar.YEAR);
        currentTime = pad(c.get(Calendar.HOUR_OF_DAY))+":"+pad(c.get(Calendar.MINUTE));
        Log.d("calendar", "date and time:  "+ todaysDate +" and " + currentTime);

    }

    private String pad(int i) {
        if(i<10)
            return "0"+i;
        return String.valueOf(i);
    }

}
