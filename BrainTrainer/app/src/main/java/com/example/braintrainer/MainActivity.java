package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {

    //variables for randomizing the question
    int operator;
    int int1;
    int int2;
    int answer;
    String questionOnScreen;
    String operatorString;
    ArrayList<Integer> options = new ArrayList<Integer>();
    ArrayList<Integer> tagValues = new ArrayList<Integer>();
    ArrayList<Integer> usedValues = new ArrayList<Integer>();

    //necessary views
    TextView question;
    TextView a;
    TextView b;
    TextView c;
    TextView d;
    TextView checker;
    Button correctCounter;
    GridLayout gridLayout;
    TextView selected;



    public void createQuestion() {
        int1= ThreadLocalRandom.current().nextInt(0,21);
        int2= ThreadLocalRandom.current().nextInt(0,21);
        operator = ThreadLocalRandom.current().nextInt(0,3);

        if (operator==0){
            operatorString = "+";
            answer = int1 + int2;
        } else if (operator==1) {
            operatorString = "-";
            answer = int1 - int2;
        } else {
            operatorString = "x";
            answer = int1 * int2;
        }

        //displaying the random question on screen
        question.setText(int1 + " " +operatorString + " " + int2);
        options.add(answer);
        while (options.size() <=4){
            int i = 0;
            int random = ThreadLocalRandom.current().nextInt(answer -8 ,answer +8 );
            if (random != answer && !options.contains(random)){
                options.add(random);
                i++;
            }

        }

//        int aTag = Integer.parseInt((String) a.getTag());
//        int bTag = Integer.parseInt((String) b.getTag());
//        int cTag = Integer.parseInt((String) c.getTag());
//        int dTag = Integer.parseInt((String) d.getTag());


        tagValues.add((Integer) a.getTag());
        tagValues.add((Integer) b.getTag());
        tagValues.add((Integer) c.getTag());
        tagValues.add((Integer) d.getTag());


        int i = 0;
        while (i <=3) {
            int optionValue = options.get(ThreadLocalRandom.current().nextInt(0, 4));
            if (!usedValues.contains(optionValue)){
                if (i == 0) {
                    a.setTag(optionValue);
                    a.setText("" +optionValue);
                }
                else if (i == 1) {
                    b.setTag(optionValue);
                    b.setText("" +optionValue);
                }
                else if (i==2) {
                    c.setTag(optionValue);
                    c.setText("" +optionValue);
                }
                else {
                    d.setTag(optionValue);
                    d.setText("" +optionValue);
                }
                usedValues.add(optionValue);
                i++;
            }
        }


        Log.i("The Answer:", "" + answer);
        Log.i("A:", "" + a.getTag());
        Log.i("B:", "" + b.getTag());
        Log.i("C:", "" + c.getTag());
        Log.i("D:", "" + d.getTag());

        a.setTag(options.get(ThreadLocalRandom.current().nextInt(0, 4)));

        options.clear();
        tagValues.clear();
        usedValues.clear();

    }


    public void ChildOne(View view) {
        selectAnswer(0);
    }

    public void ChildTwo(View view) {
        selectAnswer(1);
    }

    public void ChildThree(View view) {
        selectAnswer(2);
    }

    public void ChildFour(View view) {
        selectAnswer(3);
    }

    public void selectAnswer(int l) {
        selected = (TextView) gridLayout.getChildAt(l);
        int selectedAnswer = Integer.parseInt(selected.getTag().toString());

        if ((Integer)selectedAnswer == answer) {
            correct();
        }
        else {
            incorrect();
        }
        Log.i("Answer Selected", "" + (Integer)selected.getTag());
        createQuestion();
    }


    int numerator = 0;
    int denominator = 0;

    public void correct() {
        numerator += 1;
        denominator += 1;
        checker.setText("Correct!");
        checker.postDelayed(new Runnable() {
            @Override
            public void run() {
                checker.setVisibility(View.VISIBLE);
            }
        }, 2000);
        correctCounter.setText(numerator + "/" + denominator);

    }

    public void incorrect() {
        denominator +=1;
        checker.setText("Incorrect!");
        checker.postDelayed(new Runnable() {
            @Override
            public void run() {
                checker.setVisibility(View.VISIBLE);
            }
        },2000);
        correctCounter.setText(numerator + "/" + denominator);
    }




//    public void onClick(View view) {
//        if (isVisible == false) {
//            textView.setVisibility(View.VISIBLE);
//            textView2.setVisibility(View.INVISIBLE);
//            isVisible = true;
//            button.setText("Hide me!");
//        }  else {
//            textView.setVisibility(View.INVISIBLE);
//            textView2.setVisibility(View.VISIBLE);
//            isVisible = false;
//            button.setText("Show me!");
//        }
    //}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        createQuestion();

        //assigning the views
        question = (TextView) findViewById(R.id.textView3);
        a = (TextView) findViewById(R.id.A_option);
        b = (TextView) findViewById(R.id.B_option);
        c = (TextView) findViewById(R.id.C_option);
        d = (TextView) findViewById(R.id.D_option);
        checker = (TextView) findViewById(R.id.correctOrNot);
        correctCounter = (Button) findViewById(R.id.correctCounter);

//        gridLayout = findViewById(R.id.gridLayout);
//        for (int i = 0; i<gridLayout.getChildCount(); i++) {
//            selected = (TextView) gridLayout.getChildAt(i);
//        }

        a.setTag(0);
        b.setTag(1);
        c.setTag(2);
        d.setTag(3);
        checker.setVisibility(View.INVISIBLE);


//        textView = (TextView) findViewById(R.id.textView);
//        button = (Button) findViewById(R.id.button);
//        button.setText("Hide me!");
//        textView2 = (TextView) findViewById(R.id.textView2);
//
//        textView2.setVisibility(View.INVISIBLE);
    }
}