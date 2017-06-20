package com.example.omar_salem.note.activity;


import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.example.omar_salem.note.utilities.Helper;
import com.example.omar_salem.note.utilities.Note;
import com.example.omar_salem.note.R;
import com.example.omar_salem.note.utilities.Notifcation;


public class NoteActivity extends AppCompatActivity {

    NotificationManager NFM;
    public EditText Et_titel;
    private EditText ET_contant;
    private String mNoteFileName;
    private Note mLoadedNote;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        initializeLayoutElements();

        mNoteFileName=getIntent().getStringExtra("Note_File");
        if(mNoteFileName!=null&&!mNoteFileName.isEmpty()){
            mLoadedNote= Helper.GetNoteByName(this,mNoteFileName);
            if(mLoadedNote!=null)
            {
                Et_titel.setText(mLoadedNote.getmTitel());
                ET_contant.setText(mLoadedNote.getmContant());
            }
        }
    }

    private void initializeLayoutElements() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        Et_titel =(EditText)findViewById(R.id.note_et_titel);
        ET_contant=(EditText)findViewById(R.id.note_et_content);
    }

    @Override
    protected void onPause() {

        SaveNote();
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//this to open socrse file
        getMenuInflater().inflate(R.menu.menu_note_new,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){//when i select item

        switch (item.getItemId()){

            case R.id.action_alarm:
                ShowNoti();
                break;
            case R.id.action_delete:
                DeleteNote();
                break;
        }
        return true;
    }

    private void SaveNote(){

        //this method to save note and send it to Helper class
        if (!TextUtils.isEmpty(Et_titel.getText().toString().trim())
                || !TextUtils.isEmpty(ET_contant.getText().toString().trim())){

            Edit_Or_New();
            if(Helper.SaveNote(this,note)  ){
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Can't be saved",Toast.LENGTH_SHORT).show();
            }
            finish();
        }

   }

   private void Edit_Or_New() {

        if(mLoadedNote==null){
            note=new Note(System.currentTimeMillis(),Et_titel.getText().toString(),ET_contant.getText().toString());
        }else
        {
            note=new Note(mLoadedNote.getmCurntTime(),Et_titel.getText().toString(),ET_contant.getText().toString());
        }
    }

    private void DeleteNote(){

        if(mLoadedNote==null){ Toast.makeText(this,"You don't save",Toast.LENGTH_SHORT).show();}
        else {
            AlertDialog.Builder dialog=new AlertDialog.Builder(this).setTitle("Deleting Notes")
                    .setMessage("Are you sure of deleting ")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Helper.deletenote(getApplicationContext(),mLoadedNote.getmCurntTime()+ Helper.File_Extention);
                            Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                            Et_titel.setText(null);
                            ET_contant.setText(null);
                            finish();
                        }
                    }).setNegativeButton("No",null)
                    .setCancelable(false);
            dialog.show();
        }
    }

    //on click buttont of set noti
    public  void ShowNoti (){
        Intent NotiIntent= new Intent(this,Notifcation.class);
        startActivity(NotiIntent);

}


}
