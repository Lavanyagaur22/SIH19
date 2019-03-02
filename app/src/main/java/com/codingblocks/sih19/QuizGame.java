package com.codingblocks.sih19;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class QuizGame extends AppCompatActivity {

    CountDownTimer countDownTimer;
    Button goButton;
    TextView timerText;
    TextView questionText;
    TextView totalSolvedText;
    TextView correctText;
    GridLayout answerGrid;
    Button playAgain;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    RelativeLayout gameRelativeLayout;

    int answer;
    int totalQuestions = 0;
    int totalRight = 0;

    public void startTimer(View view) {

        goButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);

        playAgain(view);

    }

    public void chooseAnswer(View view) {
        int selectedAnswer = Integer.parseInt(((Button) view).getText().toString());

        if(selectedAnswer == answer) {
            totalRight += 1;
            correctText.setVisibility(View.VISIBLE);
            correctText.setText("Correct!");
        } else {
            correctText.setText("Incorrect");
        }

        totalQuestions += 1;
        updateQuestionCount();
        generateQuestion();
    }

    public void playAgain(View view) {
        totalQuestions = 0;
        totalRight = 0;
        updateQuestionCount();
        generateQuestion();

        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        button4.setEnabled(true);
        playAgain.setVisibility(View.INVISIBLE);
        correctText.setVisibility(View.INVISIBLE);

        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                int secondsLeft = (int) l / 1000;

                timerText.setText(secondsLeft + "s");
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();

                timerText.setText("0s");
                correctText.setText("Your score: " + totalRight + "/" + totalQuestions);

                playAgain.setVisibility(View.VISIBLE);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                button4.setEnabled(false);
            }
        }.start();
    }

    private void updateQuestionCount() {
        totalSolvedText.setText(totalRight + "/" + totalQuestions);
    }

    private void generateQuestion() {
        Random random = new Random();
        int firstNumber = random.nextInt(20) + 1;
        int secondNumber = random.nextInt(20) + 1;

        questionText.setText(firstNumber + " + " + secondNumber);

        answer = firstNumber + secondNumber;

        int locationOfAnswer = random.nextInt(4);
        int[] answers = new int[4];

        for (int i = 0; i < 4; i++) {
            if(i == locationOfAnswer) {
                answers[i] = answer;
            } else {
                answers[i] = random.nextInt(40) + 1;
            }
        }

        button1.setText(Integer.toString(answers[0]));
        button2.setText(Integer.toString(answers[1]));
        button3.setText(Integer.toString(answers[2]));
        button4.setText(Integer.toString(answers[3]));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        gameRelativeLayout = (RelativeLayout) findViewById(R.id.gameRelativeLayout);

        goButton = (Button) findViewById(R.id.goButton);
        timerText = (TextView) findViewById(R.id.timerText);
        questionText = (TextView) findViewById(R.id.questionText);
        totalSolvedText = (TextView) findViewById(R.id.totalSolvedText);
        correctText = (TextView) findViewById(R.id.correctText);
        answerGrid = (GridLayout) findViewById(R.id.answerGrid);
        playAgain = (Button) findViewById(R.id.playAgainButton);

        button1 = ((Button) findViewById(R.id.answer1));
        button2 = ((Button) findViewById(R.id.answer2));
        button3 = ((Button) findViewById(R.id.answer3));
        button4 = ((Button) findViewById(R.id.answer4));
    }
}
