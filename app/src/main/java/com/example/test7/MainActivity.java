package com.example.test7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List ContactsList = new ArrayList<>();
    private ContactsAdapter contactsAdapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.READ_CONTACTS},1);
    }

    protected void onResume(){
        super.onResume();
        if( ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)==PackageManager.PERMISSION_GRANTED){
            ContactsList.clear();
            ContactsList = readContacts();
            contactsAdapter = new ContactsAdapter(MainActivity.this, R.layout.listview_contacts, ContactsList);
            lv = findViewById(R.id.contacts);
            lv.setAdapter(contactsAdapter);
        }
    }

    private List readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            while (cursor.moveToNext()){
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                Contacts contacts = new Contacts(displayName,number,null);
                ContactsList.add(contacts);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor!=null){
                cursor.close();
            }
        }
        return ContactsList;
    }
}
