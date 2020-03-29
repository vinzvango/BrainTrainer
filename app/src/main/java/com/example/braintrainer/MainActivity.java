package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //----FIELDS DECLARATION------
    Button btnStart;
    TextView lblResult;
    TextView lblPoints;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView lblSum;
    TextView timer;
    Button btnPlayAgain;
    RelativeLayout gameRelativeLayout;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationofCorrectAnswer;
    int score=0;
    int numberofQuestions =0;




    public void start(View view){
        /*home button start will be invisible then
        game relative layout will be visible*/

        btnStart.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.btnPlayAgain));
    }


    public void playAgain(View view) {
        score = 0;
        numberofQuestions = 0;
        timer.setText("30s");
        lblPoints.setText("0/0");
        lblResult.setText("");
        btnPlayAgain.setVisibility(View.INVISIBLE);
        generateQuestion();

        // timer starts on 30 seconds
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {
                //upon finishing, btnPlayAgain will be visible then timer will be 0s
                btnPlayAgain.setVisibility(View.VISIBLE);
                timer.setText("0s");
                lblResult.setText("YOUR SCORE" + Integer.toString(score)+ "/" + Integer.toString(numberofQuestions));
                disableChoices();
            }
        }.start();
    }



    public void generateQuestion(){
        //random variable
        Random rand = new Random();
        //a and b bounded to randomize upto 21 only
        int a = rand.nextInt(21);
        int b= rand.nextInt(21);
        //label will display what is randomized above
        lblSum.setText(Integer.toString(a)+" + "+Integer.toString(b));
        //location will be randomized up to 4
        locationofCorrectAnswer = rand.nextInt(4);

        answers.clear();

        int incorrectAnswer;
        //looping [0 -1] if i is the same as the randomized number, store sum of a+b to the arraylist answers
        for(int i=0; i<4; i++){
            if(i ==locationofCorrectAnswer){
                answers.add(a+b);
            }
            else{
                // store random number to 41 into incorrectAnswer
                incorrectAnswer = (rand.nextInt(41));
                //then while incorrectanswer == to the correct sum, randomize the correct answer
                while (incorrectAnswer==a+b){
                    incorrectAnswer =rand.nextInt(41);
                }
                //add to answers
                answers.add(incorrectAnswer);
            }
        }


        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));



    }

    public void chooseAnswer(View view){

        //C
        if(view.getTag().toString().equals(Integer.toString(locationofCorrectAnswer))) {
            score++;
            lblResult.setText("CORRECT");
        }
        else{
            lblResult.setText("BOBO AMPOPTA");
        }

        numberofQuestions++;
        lblPoints.setText(Integer.toString(score)+ "/" + Integer.toString(numberofQuestions));
        generateQuestion();
    }


    public void disableChoices(){
        button0.setEnabled(false);
        button1.setEnabled(false);
        button2.setEnabled(false);
        button3.setEnabled(false);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //declaration of fields
        btnStart = findViewById(R.id.btnStart);
        lblSum = findViewById(R.id.lblQuestion);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        lblResult = findViewById(R.id.lblResult);
        lblPoints = findViewById(R.id.lblPoints);
        timer = findViewById(R.id.lblTimer);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        playAgain(findViewById(R.id.btnPlayAgain));
        gameRelativeLayout = findViewById(R.id.gameRelativeLayout);

    }


}
