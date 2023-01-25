package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_beginner;
    Button btn_intermediate;
    Button btn_advanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to buttons
        btn_beginner = findViewById(R.id.btn_beginner);
        btn_intermediate = findViewById(R.id.btn_intermediate);
        btn_advanced = findViewById(R.id.btn_advanced);


    }
}