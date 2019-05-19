package com.store.sqlite.Prabesh;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ContactDbHelper extends SQLiteOpenHelper {
    private static final String TAG = "ContactDbHelper";


    public  static  final  String DATABASE_NAME="contact_db";
    public  static final  int DATABASE_VERSION=1;

    public  static  final  String  CREATE_TABLE= "create table "+ContactContract.ContactEntry.TABLE_NAME+
            "("+ ContactContract.ContactEntry.CONTACT_ID+" number,"+ ContactContract.ContactEntry.NAME+" text,"+
            ContactContract.ContactEntry.EMAIL+" text);";
    public  static  final  String DROP_TABLE=" drop table if exists "+ ContactContract.ContactEntry.TABLE_NAME;

    public ContactDbHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "ContactDbHelper: Database Created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Log.d(TAG, "onCreate: Table created");
        

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);

    }

    public  void AddContact(int id, String name, String email,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues= new ContentValues();
        contentValues.put(ContactContract.ContactEntry.CONTACT_ID,id);
        contentValues.put(ContactContract.ContactEntry.NAME,name);
        contentValues.put(ContactContract.ContactEntry.EMAIL,email);
        sqLiteDatabase.insert(ContactContract.ContactEntry.TABLE_NAME,null,contentValues);
        Log.d(TAG, "AddContact: one Row has been inserted");


    }

    public Cursor readContacts(SQLiteDatabase sqLiteDatabase){
        String[] projections={ContactContract.ContactEntry.CONTACT_ID, ContactContract.ContactEntry.NAME, ContactContract.ContactEntry.EMAIL};

        Cursor cursor=sqLiteDatabase.query(ContactContract.ContactEntry.TABLE_NAME,projections,
                null,
                null,
                null,
                null,
                null);

        return  cursor;
    }
}
