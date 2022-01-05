package com.example.newsreaderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Connect to API function (Background thread)
    public class DownloadTask extends AsyncTask<String, Void, String> {

        //Connect to website API
        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader =  new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                return result;

            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        //Process the JSON
        protected void onPostExecute(String s){
            super.onPostExecute(s);

            try {
                JSONArray jsonArray = new JSONArray(s);
                int maxNumberOfStories = 20;
                int i = 0;
                while (i < maxNumberOfStories) {
                    Log.i("ID", "" + jsonArray.getJSONArray(i));
                    i++;
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting the list view
        ListView listView = findViewById(R.id.listView);
        ArrayList<String> news = new ArrayList<>();
        news.add("Test news");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, news );
        listView.setAdapter(arrayAdapter);


        //Execute connection to API
        DownloadTask task = new DownloadTask();

        try {
            task.execute("https://hacker-news.firebaseio.com/v0/topstories.json?print=pretty");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}