package com.example.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    boolean bartIsShowing = true;


    public void fadeAnimate(View view) {

        Log.i("info", "Image is pressed");
        ImageView bartImageView = (ImageView) findViewById(R.id.bartimageView);
        if (bartIsShowing) {
            bartImageView.animate().alpha(0).translationXBy(-1000).scaleX(0.1f).scaleY(0.1f).setDuration(1500);
            bartIsShowing = false;
        } else {
            bartImageView.animate().alpha(1).translationXBy(1000).scaleX(1).scaleY(1).setDuration(1500);
            bartIsShowing = true;
        }

        ImageView homerImageView = (ImageView) findViewById(R.id.homerImageView);




    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
