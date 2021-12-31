package com.example.storingdatawithsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.storingdatawithsharedpreferences", Context.MODE_PRIVATE);

        ArrayList<String> family = new ArrayList<String>();

        family.add("Fajpeg");
        family.add("Dad");
        family.add("Mum");
        family.add("Biggy");
        family.add("Nax");

        try {
            String familySerialized = ObjectSerializer.serialize(family);
            sharedPreferences.edit().putString("family", familySerialized).apply();
            Log.i("friends Serialized", familySerialized);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<String> familyDeserialized = new ArrayList<String>();
        try {
            familyDeserialized = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("family", ObjectSerializer.serialize(new ArrayList<String>())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("Deseralized", familyDeserialized.toString());


    }
}