package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Integer i;
    int myIntValue;
    SharedPreferences sharedPreferences;

    public void testSaved(View view) {
        Log.i("What's saved is: ", ""+ myIntValue);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return  super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.English:
                i = 1;
                setLanguage(i);
                saveLanguage(i);
                Log.i("via setting", "English is chosen");
                sharedPreferences.edit().putInt("myIntValue", i).apply();
                Log.i("i is", ""+i);
                return true;
            case R.id.Espanol:
                i =2;
                setLanguage(i);
                saveLanguage(i);
                Log.i("via setting", "Spanish is chosen");
                sharedPreferences.edit().putInt("myIntValue", i).apply();
                Log.i("i is", ""+i);
                return true;
            default:
                return false;
        }
    }

    public void setLanguage(int language) {
        if (language==1) {
            textView.setText("Hello world!");
        } else {
            textView.setText("Hola Mundial!");
        }
    }

    public Integer saveLanguage(int sl) {
        sharedPreferences.edit().putInt("myIntValue", sl).apply();
        Log.i("Saved", "" + myIntValue);






//        return myIntValue;
        return 0;

    }

    public void chooselanguage() {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Choose Language")
                    .setMessage("What language do you want?")
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            i = 1;
                            Toast.makeText(MainActivity.this, "Language: English", Toast.LENGTH_SHORT).show();
                            setLanguage(i);
                            saveLanguage(i);
                        }
                    })
                    .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int z) {
                            i = 2;
                            Toast.makeText(MainActivity.this, "Idioma: Espanol", Toast.LENGTH_SHORT).show();
                            setLanguage(i);
                            saveLanguage(i);
                        }
                    })
                    .show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        sharedPreferences = this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);

        myIntValue = sharedPreferences.getInt("myIntValue", -1);

        if (myIntValue == -1) {
            chooselanguage();
        }

        setLanguage(myIntValue);
        Log.i("LanguageKey ", "" + myIntValue);


        }
    }



//        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
//        ArrayList<String> languageSwitch = new ArrayList<String>();
//
//        languageSwitch.add(Integer.toString(i));
//
//        try {
//            String languageKey = ObjectSerializer.serialize(languageSwitch);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ArrayList<String> languageKeyDeserialized = new ArrayList<>();
//        try {
//            languageKeyDeserialized = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("languageKey", ObjectSerializer.serialize((new ArrayList<String>()))));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Log.i("Check Language Key", languageKeyDeserialized.get(0));



