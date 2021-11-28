package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public void generateTimesTable(int timesTableNumber) {
        ListView friendList = (ListView) findViewById(R.id.myList);
        ArrayList<String> timesTable = new ArrayList<String>();
        for (int za = 1 ; za<=10; za++) {
            int product = za* timesTableNumber;
            timesTable.add(za + " x " + timesTableNumber + " = " + product);
        }
        ArrayAdapter<String> arrayAdapterMultiplier = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, timesTable);
        friendList.setAdapter(arrayAdapterMultiplier);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textViewLabel = (TextView) findViewById(R.id.textView2);
        ArrayList<String> myFriends = new ArrayList<String>();

        myFriends.add("Fajpeg");
        myFriends.add("Dimas");
        myFriends.add("Alul");
        myFriends.add("Rekyan");
        myFriends.add("Andico");


        SeekBar multiplier = (SeekBar) findViewById(R.id.seekBar2);





        multiplier.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                System.out.println(i);
                generateTimesTable(i);
                textViewLabel.setText("Multiples of " + i);



            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}