package com.example.tp_5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


public class BDDManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mes_mots";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Mots";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MOT = "mot";
    private static final String sql = "CREATE TABLE "
            + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MOT + " varchar(200) NOT NULL )";

    BDDManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    long addMot(Mot mot) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_MOT, mot.mot);
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Mot> getTousMots() {
        ArrayList<Mot> mots = new ArrayList<Mot>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Mot m = new Mot();
                m.id = c.getInt((c.getColumnIndex(COLUMN_ID)));
                m.mot = c.getString(c.getColumnIndex(COLUMN_MOT));
                mots.add(m);
            } while (c.moveToNext());
        }
        return mots;
    }




    /*public void deleteMots(long m_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?",
                new String[]{String.valueOf(m_id)}) ;
    }*/

    /*public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }*/


}