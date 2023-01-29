package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpressionGenerator expressionGenerator;
    private Expression expression;

    private int currentHeart;
    private int currentLevel;
    private List<ImageView> heartList;

    private int userAnswer;
    private int highScore;

    // Declaring references to UI Views. Class fields since they're used in various private methods.
    private TextView textViewExpression;
    private TextView textViewLevel;
    private EditText editTextAnswer;
    private TextView textViewHighScore;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLevel = 1;
        resetHearts();

        textViewExpression = findViewById(R.id.tv_expression);
        expressionGenerator = new ExpressionGenerator();
        setNewExpression();
        textViewLevel = findViewById(R.id.tv_level);
        editTextAnswer = findViewById(R.id.et_answer);


        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();

        textViewHighScore = findViewById(R.id.tv_highScore);
        textViewHighScore.setText("HighScore: " + sharedPref.getInt(getString(R.string.high_score), 1));

        findViewById(R.id.btn_submit).setOnClickListener(this::submitAnswer);
    }

    private void submitAnswer(View view) {
        if (checkIfAnswerIsEmpty()) return;
        extractAnswer();
        if (userAnswer == expression.answer) {
            levelUp();
        } else {
            takeALife();
            displayMistake();
        }
        clearAnswer();
        setNewExpression();
    }

    private boolean checkIfAnswerIsEmpty() {
        return TextUtils.isEmpty(editTextAnswer.getText().toString());
    }

    private void extractAnswer() {
        userAnswer = Integer.parseInt(editTextAnswer.getText().toString());
    }

    private void levelUp() {
        textViewLevel.setText("Level: " + ++currentLevel);

        highScore = sharedPref.getInt(getString(R.string.high_score), 1);
        if (currentLevel >= highScore) {
            saveNewHighScoreLocally();
            textViewHighScore.setText("HighScore: " + highScore);
        }
    }

    private void takeALife() {
        if (currentHeart <= 1) {
            startActivity(new Intent(this, GameOver.class));
        }
        heartList.get(--currentHeart).setVisibility(View.INVISIBLE);
    }

    private void displayMistake() {
        Toast.makeText(this, "Wrong. Correct answer: " + expression.answer + ", your answer: " + userAnswer, Toast.LENGTH_SHORT).show();
    }

    private void clearAnswer() {
        editTextAnswer.setText("");
    }

    private void setNewExpression() {
        if (currentLevel < 10) {
            expression = expressionGenerator.getBeginnerExpression();
        } else if (currentLevel < 20) {
            expression = expressionGenerator.getIntermediateExpression();
        } else if (currentLevel < 30) {
            expression = expressionGenerator.getAdvancedExpression();
        }

        textViewExpression.setText(expression.expression);
    }

    private void resetHearts() {
        currentHeart = 3;
        heartList = new ArrayList<>(Arrays.asList(findViewById(R.id.iv_heart1), findViewById(R.id.iv_heart2), findViewById(R.id.iv_heart3)));
        for (ImageView heart : heartList) {
            heart.setVisibility(View.VISIBLE);
        }
    }

    private void saveNewHighScoreLocally() {
        editor.putInt(getString(R.string.high_score), currentLevel);
        editor.apply();
    }
}
