package com.example.omar_salem.note.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.omar_salem.note.utilities.Note;
import com.example.omar_salem.note.R;

import java.util.ArrayList;

/**
 * Created by Omar_Salem on 3/2/2017.
 */
public class NoteAdapter extends ArrayAdapter<Note> {
    TextView titel;
    TextView content;
    TextView date;
    public NoteAdapter(Context context, int resource, ArrayList<Note> notes ) {
        super(context, resource,notes);
    }

    @Override//this method can create a new view and display list item
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {

            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_note,null);
        }
        Note note=getItem(position);
                if(note != null)
                {
                    titel=(TextView)convertView.findViewById(R.id.note_title);
                    content =(TextView)convertView.findViewById(R.id.list_note_Contant);
                    date =(TextView)convertView.findViewById(R.id.list_note_Date);
                    titel.setText(note.getmTitel());
                  if(  note.getmContant().length() > 50)
                  {
                    content.setText(note.getmContant().substring(0,50));
                  }
                    else
                  {
                      content.setText(note.getmContant());
                  }
                   date.setText(note.GetTimeForrmated(getContext()));



                }
        return convertView;
    }
}
