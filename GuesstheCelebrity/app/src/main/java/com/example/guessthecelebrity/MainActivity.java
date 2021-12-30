package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;
    ImageView imageView;

    String[] answers = new String[4];
    int locationOfCorrectAnswer = 0;

    ArrayList<String> celebURLs = new ArrayList<String>();
    ArrayList<String> celebNames = new ArrayList<String>();
    int chosenCeleb = 0;



    public class imageDowloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(in);
                return myBitmap;
            }catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

    }
    public class DownloadTask extends AsyncTask<String, Void, String>{

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

                int data = reader.read();

                while (data != -1) {
                    char current = (char) data;
                    result += current;

                    data = reader.read();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }

    public void clicked(View view) {
        String optionPressed = (String) view.getTag();
        Log.i("Option Pressed: ", optionPressed);
        if (optionPressed.equals(Integer.toString((locationOfCorrectAnswer)))){
            Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong, it was " + celebNames.get(chosenCeleb), Toast.LENGTH_SHORT).show();
        }
    }

    public void newQuestion() {
        Random rand = new Random();
        chosenCeleb = rand.nextInt(celebURLs.size());

        imageDowloader imageTask = new imageDowloader();
        Bitmap celebImage = null;
        try {
            celebImage = imageTask.execute(celebURLs.get(chosenCeleb)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        imageView.setImageBitmap(celebImage);

        locationOfCorrectAnswer = rand.nextInt(4);

        int incorrectAnswerLocation;

        for (int i=0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers[i] = celebNames.get(chosenCeleb);
            } else {
                incorrectAnswerLocation = rand.nextInt(celebURLs.size());
                while (incorrectAnswerLocation == chosenCeleb) {
                    answers[i] = celebNames.get(incorrectAnswerLocation);
                }
            }
        }

        buttonA.setText(answers[0]);
        buttonA.setText(answers[1]);
        buttonA.setText(answers[2]);
        buttonA.setText(answers[3]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonA = (Button) findViewById(R.id.button);
        buttonB = (Button) findViewById(R.id.button2);
        buttonC = (Button) findViewById(R.id.button3);
        buttonD = (Button) findViewById(R.id.button4);
        imageView = (ImageView) findViewById(R.id.imageView);

        DownloadTask task = new DownloadTask();
        String result = null;

        try {
            result = task.execute("https://www.thetoptens.com/greatest-celebrities/").get();

            String[] splitResult = result.split("<div id=\"loadmore\" class=\"bluebutton\" onclick=\"getMoreItems(2)\">");
            Pattern pattern = Pattern.compile("class=\"hublink\">(.*?)<");

            Matcher matcher = pattern.matcher(splitResult[0]);

            while (matcher.find()) {
                System.out.println(matcher.group(1));
            }


            pattern = Pattern.compile("img src=\"(.*?)\"");
            matcher = pattern.matcher((splitResult[0]));

            while (matcher.find()) {
                System.out.println(matcher.group(1));
            }

            newQuestion();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}