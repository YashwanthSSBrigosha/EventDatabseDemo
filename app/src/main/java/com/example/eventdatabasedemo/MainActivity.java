package com.example.eventdatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {

            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (name VARCHAR,year INT(4))");
            //myDatabase.execSQL("INSERT INTO events (name,year) VALUES ('event1',2019)");
            //myDatabase.execSQL("INSERT INTO events (name,year) VALUES ('event2',2021)");
            Cursor c = myDatabase.rawQuery("SELECT * FROM events",null);
            int eventIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");
            c.moveToFirst();

            while (!c.isAfterLast()){
                Log.i("Results-Event",c.getString(eventIndex));
                Log.i("Results-Year",Integer.toString(c.getInt(yearIndex)));
                c.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}