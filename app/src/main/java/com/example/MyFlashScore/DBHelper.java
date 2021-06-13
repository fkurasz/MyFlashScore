package com.example.MyFlashScore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "druzyny.db", null, 10);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM WszystkieDruzyny",null);
        return cursor;
    }
    public Cursor getDataFromSQL(String sql_request)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql_request,null);
        return cursor;
    }

    //ustawia w tabeli mecz ulubione lub je usuwa
    public Boolean setUlubione(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT * FROM Mecze",null);
        if(cursor.getCount()>0){
            if(cursor.getInt(4)==1)
            {
                // na 0
                contentValues.put("ulubione",0);
                long result = db.update("Mecze",contentValues,null,null);
                return true;
            }
            else{
                // na 1
                contentValues.put("ulubione",1);
                long result = db.update("Mecze",contentValues,null,null);
                return true;
            }
        }
        return false;
    }
}
