package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    EditText cityName;
    String main;
    String desc;
    String city;


    public void clicked (View view) {
        DownloadTask task = new DownloadTask();
        city = cityName.getText().toString().trim();

        try {
            task.execute("https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=1edb0f5016ef741b9692cd794e09de65");
        }catch (Exception e) {
            e.printStackTrace();
            textView.setText("The city you search for doesn't exist");
            textView.setVisibility(View.VISIBLE);
        }



    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data =  reader.read();

                while (data != -1) {
                    char current = (char) data;

                    result += current;
                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);

                String weatherInfo = jsonObject.getString("weather");
                Log.i("Weather", "" + weatherInfo);

                JSONArray array = new JSONArray(weatherInfo);

                for (int i = 0; i <array.length(); i++) {
                    JSONObject jsonPart = array.getJSONObject(i);

                    main = jsonPart.getString("main");
                    desc = jsonPart.getString("description");

                    textView.setText("The weather in "+city+" is "+ main+ ". It is " + desc );
                    textView.setVisibility(View.VISIBLE);


                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        task = new DownloadTask();
//        Runnable https;
//        task.execute("https://api.openweathermap.org/data/2.5/weather?q=Shanghai&appid=1edb0f5016ef741b9692cd794e09de65");

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        cityName = findViewById(R.id.cityName);

        textView.setVisibility(View.INVISIBLE);


    }
}