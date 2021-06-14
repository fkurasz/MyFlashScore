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

    public Cursor isUlubionaDruzyna(String klub1)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM WszystkieDruzyny WHERE ulubione=1 AND klub=\""+klub1+"\"",null);
        return cursor;
    }
    public Cursor getDataFromSQL(String sql_request)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql_request,null);
        return cursor;
    }

    //ustawia w tabeli mecz ulubione lub je usuwa
    public Boolean setUlubioneWszystkieDruzyny(String _klub){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int wynik = 0;

        String sql_request = "SELECT * FROM WszystkieDruzyny WHERE klub=\"" + _klub +"\"";

        Cursor cursor = db.rawQuery(sql_request,null);
        if(cursor.getCount()>0){
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append(cursor.getInt(7));
            }
            wynik = Integer.parseInt(buffer.toString());
                // na 0
            if(wynik == 1)
            {
                contentValues.put("ulubione",0);
                int result = db.update("WszystkieDruzyny",contentValues,"klub=\"" + _klub +"\"",null);
                return true;
            }
            else{
                // na 1
                contentValues.put("ulubione",1);
                int result = db.update("WszystkieDruzyny",contentValues,"klub=\"" + _klub +"\"",null);
                return true;
            }
        }
        return false;
    }

    //ustawia w tabeli mecz ulubione lub je usuwa
    public Boolean setUlubioneMecz(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        int wynik = 0;

        String sql_request = "SELECT * FROM Mecze WHERE id=\"" + _id +"\"";

        Cursor cursor = db.rawQuery(sql_request,null);
        if(cursor.getCount()>0){
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append(cursor.getInt(4));
            }
            wynik = Integer.parseInt(buffer.toString());
            if(wynik == 0)
            {
                // na 1
                contentValues.put("ulubione",1);
                int result = db.update("Mecze",contentValues,"id=\"" + _id +"\"",null);
                return true;
            }else {
                return true;
            }
        }
        return false;
    }

    public Boolean setUsunUlubioneMecz(int _id){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            int wynik = 0;

            String sql_request = "SELECT * FROM Mecze WHERE id=\"" + _id +"\"";

            Cursor cursor = db.rawQuery(sql_request,null);
            if(cursor.getCount()>0){
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append(cursor.getInt(4));
                }
                wynik = Integer.parseInt(buffer.toString());
                if(wynik == 1)
                {
                    // na 0
                    contentValues.put("ulubione",0);
                    int result = db.update("Mecze",contentValues,"id=\"" + _id +"\"",null);
                    return true;
                }else {
                    return true;
                }
            }
            return false;
    }
}
