package com.example.newactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> holidayTrip=  new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    public void nextActivity(View view){
        Intent intent = new Intent(getApplicationContext(), MainActivity2.class);

        intent.putExtra("year", 2022);

        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);




        holidayTrip.add("Pantai Pangasan");
        holidayTrip.add("Telaga Sarangan");
        holidayTrip.add("Tawangmangu");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, holidayTrip);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                intent.putExtra("destinationID", holidayTrip.get(i));
                startActivity(intent);
            }
        });
    }
}