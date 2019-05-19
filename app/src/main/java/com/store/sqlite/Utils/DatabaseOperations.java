package com.store.sqlite.Utils;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOperations extends SQLiteOpenHelper {
    private static  final  int DATABASE_VERSION=1;
    private static  final String DATABASENAME="EMPLOY";
    private static  final String TABLENAME="EMPLOY_TABLE";
    private static  final  String Col_1= "ID";
    private static  final  String Col_2= "USERNAME";
    private static  final  String Col_3= "EMAIL";
    private static  final  String Col_4= "PASSWORD";


    public DatabaseOperations(Context context) {
        super(context, DATABASENAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(" create table "+ TABLENAME +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT)");
        db.execSQL("create table " + TABLENAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT , USERNAME TEXT, EMAIL TEXT, PASSWORD INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  boolean Createuser( String username, String email, String pass){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col_2, username);
        cv.put(Col_3, email);
        cv.put(Col_4, pass);
        long res= db.insert(TABLENAME,null,cv);
        if (res==-1){
            return  false;
        }
        else {
            return  true;
        }

    }

    public Cursor getAlldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLENAME,null);
        return  res;
    }

}
