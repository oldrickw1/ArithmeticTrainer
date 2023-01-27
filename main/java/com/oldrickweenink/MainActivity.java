package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
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
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    ExpressionGenerator expressionGenerator;
    Expression expression;
    TextView tv_beginnerExpression;
    TextView tv_level;
    EditText et_beginnerAnswer;
    Button btn_beginnerSubmit;
    ImageView iv_heart1;
    ImageView iv_heart2;
    ImageView iv_heart3;
    int currentHeart;
    int currentLevel;

    List<ImageView> heartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get references to buttons
        tv_beginnerExpression = findViewById(R.id.tv_beginnerExpression);
        tv_level = findViewById(R.id.tv_level);
        et_beginnerAnswer = findViewById(R.id.et_beginnerAnswer);
        btn_beginnerSubmit = findViewById(R.id.btn_beginnerSubmit);
        expressionGenerator = new ExpressionGenerator();
        setNewExpression();
        resetHearts();
        currentLevel = 0;

        btn_beginnerSubmit.setOnClickListener(view -> {
            if (checkIfAnswerIsEmpty()) {
                return;
            }
            if (checkIfAnswerIsCorrect()) {
                levelUp();
            } else {
                takeALife();
            }
            setNewExpression();
            clearAnswer();
        });
    }

    private void levelUp() {
        tv_level.setText("Level: " + ++currentLevel);
    }


    private void takeALife() {
        if (currentHeart == 1) {
            Intent intent = new Intent(this, gameOver.class);
            startActivity(intent);
        }
        heartList.get(currentHeart-- - 1).setVisibility(View.INVISIBLE);
    }


    private boolean checkIfAnswerIsEmpty() {
        if (TextUtils.isEmpty(et_beginnerAnswer.getText().toString())) {
            return true;
        }
        return false;
    }

    private boolean checkIfAnswerIsCorrect() {
        return Integer.parseInt(et_beginnerAnswer.getText().toString()) == expression.answer;
    }

    private void clearAnswer() {
        et_beginnerAnswer.setText("");
    }

    private void setNewExpression() {
        if (currentLevel < 10) {
            expression = expressionGenerator.getBeginnerExpression();
        } else if (currentLevel < 20) {
            expression = expressionGenerator.getIntermediateExpression();
        } else if (currentLevel < 30) {
            expression = expressionGenerator.getAdvancedExpression();
        }

        tv_beginnerExpression.setText(expression.expression);
    }

    private void resetHearts() {
        iv_heart1 = findViewById(R.id.iv_heart1);
        iv_heart2 = findViewById(R.id.iv_heart2);
        iv_heart3 = findViewById(R.id.iv_heart3);
        currentHeart = 3;
        heartList = new ArrayList<>(Arrays.asList(iv_heart1,iv_heart2,iv_heart3));
        for (ImageView heart : heartList) {
            heart.setVisibility(View.VISIBLE);
        }
    }
}