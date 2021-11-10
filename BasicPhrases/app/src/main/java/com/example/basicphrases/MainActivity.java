package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public void thisPhrase (View view) {
        Button button = (Button) view;



        int buttonPressed = Integer.parseInt(button.getTag().toString());
        Log.i("This button is clicked", "" + buttonPressed);
        MediaPlayer mediaPlayer;

        if (buttonPressed == 0) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a0doyouspeakenglish);
            mediaPlayer.start();
        } else if (buttonPressed == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a1goodevening);
            mediaPlayer.start();
        } else if (buttonPressed == 2) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a2hello);
            mediaPlayer.start();
        } else if (buttonPressed == 3) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a3howareyou);
            mediaPlayer.start();
        } else if (buttonPressed == 4) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a4ilivein);
            mediaPlayer.start();
        } else if (buttonPressed == 5) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a5mynameis);
            mediaPlayer.start();
        } else if (buttonPressed == 6) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a6please);
            mediaPlayer.start();
        }  else if (buttonPressed == 7) {
            mediaPlayer = MediaPlayer.create(this, R.raw.a7welcome);
            mediaPlayer.start();
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}