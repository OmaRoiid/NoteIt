package com.example.omar_salem.note.utilities;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.omar_salem.note.utilities.Note;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Omar_Salem on 3/1/2017.
 */
public class Helper {
  public static final String File_Extention=".bin";

    public static boolean SaveNote(Context context,Note note) {
        String FileName = String.valueOf(note.getmCurntTime()) + File_Extention;
//this lines of code to write a file to  external  Storage Area for App( internal storage )

            FileOutputStream fos;//This  To write the data to file

            ObjectOutputStream oos;
            try {
                fos = context.openFileOutput(FileName, Context.MODE_PRIVATE);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(note);
                fos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }


   // this lines of code to show the notes that was saved
    public static ArrayList<Note> GetAllSavedNote(Context context){
ArrayList<Note> notes=new ArrayList<>();
        File fileDir= context.getFilesDir();
        ArrayList<String>notefile=new ArrayList<>();
        for( String file: fileDir.list())
        {
            if(file.endsWith(File_Extention))
            {
                notefile.add(file);
            }
        }
        FileInputStream fis;
        ObjectInputStream ois;
        for(int i=0 ;i<notefile.size();i++)
        {
            try{
             fis= context.openFileInput(notefile.get(i));
                ois=new ObjectInputStream(fis);
                notes.add((Note)ois.readObject());
                fis.close();
                ois.close();
            } catch (FileNotFoundException e ) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
            e.printStackTrace();
                return null;
        } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

        }
        return notes;
    }
    @Nullable
    public static Note GetNoteByName (Context context, String FileName){
        Note note;
        File file=new File (context.getFilesDir(),FileName);
        if(file.exists()){
        FileInputStream fis;
        ObjectInputStream ois;
            try {
                fis= context.openFileInput(FileName);
                ois=new ObjectInputStream(fis);
                 note=(Note)ois.readObject();
                fis.close();
                ois.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }

return note;
        }

return null;

    }

    public static void deletenote(Context context, String filename) {
        File dir=context.getFilesDir();
        File file=new File(dir,filename);
        if(file.exists()){
            file.delete();
        }

    }
}
