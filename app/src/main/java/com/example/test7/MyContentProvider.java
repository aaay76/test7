package com.example.test7;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private SQLiteDatabase cDb;
    private Context mContext;
    public static final int CONTACTS_DIR = 0;
    public static final int CONTACTS_ITEM = 1;
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI("com.example.test7.MyContentProvider","Contacts",CONTACTS_DIR);
        uriMatcher.addURI("com.example.test7.MyContentProvider","Contacts/#",CONTACTS_ITEM);
    }
    @Override
    public boolean onCreate() {
        cDb = new ContactsDatabase(getContext(),"Contacts.db",null,1).getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                Log.d("MyContentProvider","查询CONTACTS表中的所有数据");
                return cDb.query("Contacts",projection,selection,selectionArgs,null,null,sortOrder,null);
            case CONTACTS_ITEM:
                String id=uri.getPath();
                id=id.substring(id.indexOf("/"));
                Log.d("MyContentProvider","查询CONTACTS表中ID为"+id+"的数据");
                return cDb.query("Contacts",projection,"id=?",new String[]{id},null,null,sortOrder,null);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case CONTACTS_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.test7.MyContentProvider.Contacts";
            case CONTACTS_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.test7.MyContentProvider.Contacts";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        cDb.insert("Contacts",null,values);
        mContext.getContentResolver().notifyChange(uri,null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count=cDb.delete("Contacts",selection,selectionArgs);
        if(count>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int row=cDb.update("Contacts",values,selection,selectionArgs);
        if(row>0){
            getContext().getContentResolver().notifyChange(uri,null);
        }
        return row;
    }
}
