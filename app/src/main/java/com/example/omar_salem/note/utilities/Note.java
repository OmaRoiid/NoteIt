package com.example.omar_salem.note.utilities;

import android.content.Context;

import java.text.SimpleDateFormat;
//import android.icu.util.TimeZone;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Omar_Salem on 3/1/2017.
 */
public class Note implements Serializable {
    private long mCurntTime;
    private String mTitel;
    private String mContant;

    public Note(long curntTime, String titel, String contant) {
        mCurntTime = curntTime;
        mTitel = titel;
        mContant = contant;
    }
//----------Seters Functions ----------//
    public void setmCurntTime(long mCurntTime) {
        this.mCurntTime = mCurntTime;
    }

    public void setmTitel(String mTitel) {
        this.mTitel = mTitel;
    }

    public void setmContant(String mContant) {
        this.mContant = mContant;
    }
//-----------end Of------//
    public long getmCurntTime() {

            return mCurntTime;

    }
    public String getmContant() {
        return mContant;
    }
    public String getmTitel() {
        return mTitel;
    }
public String GetTimeForrmated(Context context){
   SimpleDateFormat sdf =new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",context.getResources().getConfiguration().locale);
    //sdf.setTimeZone(TimeZone.getDefault());
    return sdf.format(new Date(mCurntTime));

}

}
