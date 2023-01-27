package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
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

    private TextView tv_expression;
    private TextView tv_level;
    private EditText et_answer;
    private CountDownTimer timer;
    private TextView tv_timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get references to buttons
        tv_expression = findViewById(R.id.tv_expression);
        tv_level = findViewById(R.id.tv_level);
        et_answer = findViewById(R.id.et_answer);
        Button btn_submit = findViewById(R.id.btn_submit);
        tv_timer = findViewById(R.id.tv_timer);

        expressionGenerator = new ExpressionGenerator();
        setNewExpression();
        resetHearts();
        currentLevel = 0;
        resetTimer();


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
            timer.cancel();
            resetTimer();
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

    private void resetTimer() {
        timer = new CountDownTimer(COUNTDOWN_TIME, 1000) {
            @Override
            public void onTick(long l) {
                tv_timer.setText("Time: " + l / 1000);
            }

            @Override
            public void onFinish() {
                takeALife();
                setNewExpression();
                clearAnswer();
                resetTimer();
            }
        }.start();
    }


}