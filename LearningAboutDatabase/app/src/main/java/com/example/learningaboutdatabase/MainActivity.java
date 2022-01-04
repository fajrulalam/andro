package com.example.learningaboutdatabase;

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

        SQLiteDatabase dB = this.openOrCreateDatabase("users", MODE_PRIVATE, null);
        dB.execSQL("DROP TABLE IF EXISTS user");
        dB.execSQL("CREATE TABLE IF NOT EXISTS user (" +
                "name VARCHAR," +
                "yearOfBirth INT(4)," +
                "nationality VARCHAR)");

//        dB.execSQL("INSERT INTO user (name, yearOfBirth, nationality) VALUES ('Fajpeg', 2001, 'Indonesia')");
//        dB.execSQL("INSERT INTO user (name, yearOfBirth, nationality) VALUES ('Ghnax', 2004, 'Indonesia')");
//        dB.execSQL("INSERT INTO user (name, yearOfBirth, nationality) VALUES ('Bigzee', 2009, 'Indonesia')");

        Cursor c = dB.rawQuery("SELECT * FROM user", null);

        int eventIndex = c.getColumnIndex("name");
        int yearIndex = c.getColumnIndex("yearOfBirth");
        int countryIndex = c.getColumnIndex("nationality");

        c.moveToFirst();

        while (!c.isAfterLast()) {
            Log.i("name", c.getString(eventIndex));
            Log.i("yearOfBirth", c.getString(yearIndex));
            Log.i("nationality", c.getString(countryIndex));
            c.moveToNext();
        }

    }
}