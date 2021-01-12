package com.myaplikasi.postest88;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper  {
    private static final String DATABASE_NAME = "data.db";
    private static int DATABASE_VERSION = 1;
    public DataHelper (Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        String sql = "create table biodata(no integer primary key, foto text null, alamat text null, tlp text null, sms text null, fasilitas text null, harga text null);";
        Log.d("Data", "onCreate: "+sql);
        db.execSQL(sql);
//        sql = "INSERT INTO biodata (no, nama, tgl, alamat, berat, harga ) VALUES ('1', 'fathur','sorosutan', '1994-02-03', '4 kg', '20k');";
//        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }
}

