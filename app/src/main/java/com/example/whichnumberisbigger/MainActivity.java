package com.example.whichnumberisbigger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //instance variables for the widgets we need
    //to access programmatically

    private Button leftButton;
    private Button rightButton;
    private TextView textViewScore;
    private int score;
    private int leftNum;
    private int rightNum;

    public static final int  MAX_NUM = 1000;
    public static final int MIN_NUM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        randomizeAndUpdateDisplay();
    }

    private void randomizeAndUpdateDisplay() {
        // set the score
        String scoreString = getResources().getString(R.string.main_score);
        textViewScore.setText(scoreString + score);
        // randomize numbers
        randomizeNumbers();
        // set the button values
        rightButton.setText(String.valueOf(rightNum));
        leftButton.setText(String.valueOf(leftNum));
    }



    private void randomizeNumbers() {
        //generate a random number for the left
        int range = MAX_NUM - MIN_NUM + 1;
        leftNum = genNumber();
        rightNum = genNumber();
        //generate a random number for the right bt make sure it doesn't
        //match the left
        while(
                leftNum == rightNum)
            rightNum = genNumber();

    }

    private int genNumber() {
        int range = MAX_NUM - MIN_NUM + 1;
        return  MIN_NUM + (int)(Math.random() * range);

    }


    private void wireWidgets() {
        leftButton = findViewById(R.id.button_main_left);
        rightButton = findViewById(R.id.button_main_right);
    }

    public void onLeftClick(View view) {
       checkAnswer(true);
    }

    public void onRightClick(View view) {
        checkAnswer(false);
    }

    public void checkAnswer(boolean leftPressed) {
        String message;
        if((leftNum > rightNum && leftPressed) || (rightNum > leftNum && !leftPressed)){
            score++;
            message = "Correct!";
        }
        else{
            score--;
            message = "Not Correct";
        }
        randomizeAndUpdateDisplay();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
