package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 1=yellow, 0=red
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winState = { {0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    int turn = 1;
    boolean finish = false;
    public void dropIn (View view) {

        ImageView counter = (ImageView) view;

        Log.i("Kotak", "" + counter.getTag());
        int kotakTerrpilih = Integer.parseInt(counter.getTag().toString());

        if (gameState[kotakTerrpilih] == 2 && finish == false) {
            counter.setTranslationY(-1500);
            gameState[kotakTerrpilih] = turn;
            if (turn == 1) {
                counter.setImageResource(R.drawable.yellow);
                turn = 0;
            } else {
                counter.setImageResource(R.drawable.red);
                turn = 1;
            }
            counter.animate().translationYBy(1500).rotation(1800).setDuration(300);
            String winner = "";

            for (int[] winState : winState) {
                if (gameState[winState[0]] == gameState[winState[1]] && gameState[winState[1]] == gameState[winState[2]] && gameState[winState[1]] != 2) {
                    if (gameState[winState[0]] == 1) {
                        winner = "Yellow";

                    } else {
                        winner = "Red";
                    }

                    finish = true;

                    Button restartButton = (Button) findViewById(R.id.button);
                    TextView restartTextView = (TextView)  findViewById(R.id.textView);
                    restartTextView.setText(winner + " has won");
                    restartButton.setVisibility(View.VISIBLE);
                    restartTextView.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain (View view) {
        Button restartButton = (Button) findViewById(R.id.button);
        TextView restartTextView = (TextView)  findViewById(R.id.textView);
        restartButton.setVisibility(View.INVISIBLE);
        restartTextView.setVisibility(View.INVISIBLE);

        //access all counter

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i = 0; i< gameState.length; i++) {
            gameState[i] = 2;
        }

        turn = 1;
        finish = false;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}