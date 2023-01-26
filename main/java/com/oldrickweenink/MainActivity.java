package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv_beginnerExpression;
    EditText et_beginnerAnswer;
    Button btn_beginnerSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myApplication.setUpExpressionGenerator();
        // get references to buttons
        tv_beginnerExpression = findViewById(R.id.tv_beginnerExpression);
        et_beginnerAnswer = findViewById(R.id.et_beginnerAnswer);
        btn_beginnerSubmit = findViewById(R.id.btn_beginnerSubmit);

        Expression expression = myApplication.expressionGenerator.getBeginnerExpression();
        tv_beginnerExpression.setText(expression.expression);

        btn_beginnerSubmit.setOnClickListener(view -> {
            if (TextUtils.isEmpty(et_beginnerAnswer.getText().toString())) {
                return;
            }
            int userAnswer = Integer.parseInt(et_beginnerAnswer.getText().toString());
            if (userAnswer == expression.answer) {
                Toast.makeText(this, "CORRECT!", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(this, "INCORRECT!", Toast.LENGTH_SHORT).show();


        });
    }
}