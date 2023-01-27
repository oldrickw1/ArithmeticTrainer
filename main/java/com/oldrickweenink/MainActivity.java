package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyApp_MainActivity";
    private static final long COUNTDOWN_TIME = 10000;
    private ExpressionGenerator expressionGenerator;
    private Expression expression;

    private int currentHeart;
    private int currentLevel;
    private List<ImageView> heartList;

    private int answer;
    private int highScore;

    private TextView tv_expression;
    private TextView tv_level;
    private EditText et_answer;
    private TextView tv_timer;
    private TextView tv_highScore;

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get references to buttons
        tv_expression = findViewById(R.id.tv_expression);
        tv_level = findViewById(R.id.tv_level);
        et_answer = findViewById(R.id.et_answer);
        Button btn_submit = findViewById(R.id.btn_submit);

        // TODO: Implement highscore mechanism
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor  = sharedPref.edit();
        tv_highScore = findViewById(R.id.tv_highScore);
        highScore = sharedPref.getInt(getString(R.string.high_score), 1);
        tv_highScore.setText("HighScore: " +  highScore);
        expressionGenerator = new ExpressionGenerator();
        setNewExpression();
        resetHearts();
        currentLevel = 0;

        btn_submit.setOnClickListener(view -> {
            if (checkIfAnswerIsEmpty()) return;
            extractAnswer();
            if (answer == expression.answer) {
                levelUp();
            } else {
                displayMistake();
                takeALife();
            }
            setNewExpression();
            clearAnswer();
        });
    }

    private boolean checkIfAnswerIsEmpty() {
        return TextUtils.isEmpty(et_answer.getText().toString());
    }

    private void extractAnswer() {
        answer = Integer.parseInt(et_answer.getText().toString());
    }

    private void displayMistake() {
        Toast.makeText(this, "Wrong. Correct answer: " + expression.answer + ", your answer: " + answer, Toast.LENGTH_SHORT).show();
    }

    private void levelUp() {
        tv_level.setText("Level: " + ++currentLevel);

        highScore =  sharedPref.getInt(getString(R.string.high_score), 1);
        Log.i(TAG, "High score: " + highScore);
        if (currentLevel >= highScore) {
            editor.putInt(getString(R.string.high_score), currentLevel);
            editor.apply();
            tv_highScore.setText("HighScore: " + highScore);
        }
    }

    private void takeALife() {
        if (currentHeart <= 1) {
            startActivity(new Intent(this, gameOver.class));
        }
        heartList.get(--currentHeart).setVisibility(View.INVISIBLE);
    }

    private void setNewExpression() {
        if (currentLevel < 10) {
            expression = expressionGenerator.getBeginnerExpression();
        } else if (currentLevel < 20) {
            expression = expressionGenerator.getIntermediateExpression();
        } else if (currentLevel < 30) {
            expression = expressionGenerator.getAdvancedExpression();
        }

        tv_expression.setText(expression.expression);
    }

    private void clearAnswer() {
        et_answer.setText("");
    }

    private void resetHearts() {
        ImageView iv_heart1 = findViewById(R.id.iv_heart1);
        ImageView iv_heart2 = findViewById(R.id.iv_heart2);
        ImageView iv_heart3 = findViewById(R.id.iv_heart3);
        currentHeart = 3;
        heartList = new ArrayList<>(Arrays.asList(iv_heart1, iv_heart2, iv_heart3));
        for (ImageView heart : heartList) {
            heart.setVisibility(View.VISIBLE);
        }
    }

}