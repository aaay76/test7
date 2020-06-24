package com.example.test7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ContactsDatabase extends SQLiteOpenHelper {
    public final String CREATE_CONTACTS = "create table Contacts(" +
            "id integer primary key autoincrement,"+
            "Name String,"+
            "Number String,"+
            "Sex String)";
    private Context mContext;
    public ContactsDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
        mContext=context;
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_CONTACTS);
        Toast.makeText(mContext,"Create succeeded",Toast.LENGTH_SHORT).show();
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
