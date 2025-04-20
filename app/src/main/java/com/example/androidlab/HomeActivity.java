package com.example.androidlab;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 60000; // 60 seconds (1 minute)
    private int questionNum = 0;
    private int score = 0;
    private String quiz = "";
    private String expectedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button backBT = findViewById(R.id.backBT);

        backBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                startActivity(intent);
            }
        });

        TextView timeTV = findViewById(R.id.timeTV);
        Button AnswerBT = findViewById(R.id.AnswerBT);

        // Create the countdown timer
        new CountDownTimer(START_TIME_IN_MILLIS, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView every second
                timeTV.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                // Action when countdown ends
                timeTV.setText("0s");
                AnswerBT.setClickable(false);
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Time Out!")
                        .setMessage("Your Score: " + score)
                        .setPositiveButton("OK", (dialog, which) -> finish())
                        .show();
            }
        }.start();

        TextView quizTV = findViewById(R.id.quizTV);
        EditText answerET = findViewById(R.id.answerET);

        quizTV.setText(String.valueOf(quiz));

        expectedAnswer = generateQuestion();
        quizTV.setText(quiz);

        AnswerBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAnswer = answerET.getText().toString();
                checkAnswer(userAnswer);
            }
        });
    }

    private String generateQuestion() {
        Random random = new Random();
        int a = random.nextInt(10) + 1; // Random number between 1 and 10
        int b = random.nextInt(10) + 1;
        int operator = random.nextInt(4); // 0 for +, 1 for -, 2 for *, 3 for /

        switch (operator) {
            case 0:
                quiz = a + " + " + b;
                return String.valueOf(a + b);
            case 1:
                quiz = a + " - " + b;
                return String.valueOf(a - b);
            case 2:
                quiz = a + " * " + b;
                return String.valueOf(a * b);
            case 3:
                quiz = a + " / " + b;
                return String.format("%.2f", a / (float) b); // Two decimals for division
            default:
                return "0";
        }
    }

    private void checkAnswer(String userAnswer) {
        TextView quizTV = findViewById(R.id.quizTV);
        TextView questionNumTV = findViewById(R.id.questionNumTV);
        TextView scoreTV = findViewById(R.id.scoreTV);
        EditText answerET = findViewById(R.id.answerET);

        if (userAnswer.equals(expectedAnswer)) {
            score+=100;
            questionNum++;
            scoreTV.setText(String.valueOf(score));
            questionNumTV.setText(String.valueOf(questionNum));
            expectedAnswer = generateQuestion(); // Generate a new question
            quizTV.setText(quiz);
            answerET.setText(""); // Clear the input
        } else {
            answerET.setError("Incorrect! Try again.");
        }
    }

}