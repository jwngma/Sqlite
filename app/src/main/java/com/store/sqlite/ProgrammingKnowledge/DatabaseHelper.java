package com.store.sqlite.ProgrammingKnowledge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student.db";
    private static final String TABLE_NAME = "student_table";
    private static final String COL1 = "ID";
    private static final String COL_2 = "NAME";
    private static final String COL_3 = "SURNAME";
    private static final String COL_4 = "MARKS";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, SURNAME TEXT, MARKS INTEGER)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

    }

    public boolean insertdata(String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2, name);
        cv.put(COL_3, surname);
        cv.put(COL_4, marks);
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAlldata() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select  * from " + TABLE_NAME, null);
        return res;
    }

    public boolean Updatedata(String id, String name, String surname, String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL1, id);
        cv.put(COL_2, name);
        cv.put(COL_3, surname);
        cv.put(COL_4, marks);
        db.update(TABLE_NAME, cv, "ID = ?", new String[]{id});
        return true;

    }

    public Integer deletedata(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deleted = db.delete(TABLE_NAME, "ID = ?", new String[]{id});
        return deleted;
    }
}
