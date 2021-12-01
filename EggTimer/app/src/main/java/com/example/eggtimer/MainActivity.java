package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean countOn = false;
    SeekBar seekbar;
    Button button;
    TextView timeTextView;
    CountDownTimer countDownTimer;

    public void resetTimer() {
        timeTextView.setText("0:30");
        seekbar.setProgress(30000);
        countOn = false;
        Toast.makeText(this, "Stop countdown...", Toast.LENGTH_SHORT).show();
        button.setText("Start!");
        countDownTimer.cancel();
        seekbar.setEnabled(true);
    }



    public void onClick(View view) {
        if (countOn == true) {

            resetTimer();

        } else  {
            countOn = true;
            Toast.makeText(this, "Start countdown...", Toast.LENGTH_SHORT).show();
            button.setText("Stop!");
            seekbar.setEnabled(false);

            countDownTimer = new CountDownTimer(Long.valueOf(seekbar.getProgress()) + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int)l);
                    Log.i("Seconds Left!", String.valueOf(l /1000));

                }

                @Override
                public void onFinish() {

                }
            }.start();
        }

    }

    public void updateTimer(int secondsleft){
        int minutes = secondsleft / 60000;
        int seconds = secondsleft % 60000 / 1000;
        String secondString = "" + seconds;

        if(seconds < 10 ) {
            secondString = "0" + secondString;
        }
        timeTextView.setText(Integer.toString(minutes)+":"+secondString);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        button = (Button) findViewById(R.id.button);
        timeTextView = (TextView) findViewById(R.id.textView);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
                Log.i("p", "" + seekbar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        new CountDownTimer(10000, 1000) {
//            public void onTick (long milisecondsUntilDone) {
//                Log.i("Seconds Left!", String.valueOf(milisecondsUntilDone /1000));
//            }
//            public void onFinish() {
//                Log.i("We're done!", "No more countdown");
//            }
//        }.start();

        /*
        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Hey it's us", "A second has passed by!");
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);

         */
    }
}