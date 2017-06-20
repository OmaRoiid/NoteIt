package com.example.omar_salem.note.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omar_salem.note.utilities.Helper;
import com.example.omar_salem.note.utilities.Note;
import com.example.omar_salem.note.adapter.NoteAdapter;
import com.example.omar_salem.note.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLayoutElements();
    }

    private void initializeLayoutElements() {

        notesListView =(ListView)findViewById(R.id.list_view_notes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openNoteActivity();
            }
        });
    }


    public void openNoteActivity() {

        startActivity(new Intent(MainActivity.this, NoteActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        notesListView.setAdapter(null);
        ArrayList<Note> MA_notes= Helper.GetAllSavedNote(this);
        if(MA_notes == null || MA_notes.size()==0)
        {
            Toast.makeText(this,"You don't Saved Any Notes",Toast.LENGTH_SHORT).show();
        }else
        {
            NoteAdapter na= new NoteAdapter(this,R.layout.item_note,MA_notes);
            notesListView.setAdapter(na);
            notesListView.setOnItemClickListener(this);

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        String FileName=((Note) notesListView.getItemAtPosition(position)).getmCurntTime()+ Helper.File_Extention;
        Intent NoteViewIntent= new Intent(getApplicationContext(),NoteActivity.class);
        NoteViewIntent.putExtra("Note_File",FileName);//this to make my information about note
        startActivity(NoteViewIntent);
    }
}