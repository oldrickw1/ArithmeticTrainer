
package com.oldrickweenink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class gameOver extends AppCompatActivity {
    Button btn_tryAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        btn_tryAgain = findViewById(R.id.btn_tryAgain);
        btn_tryAgain.setOnClickListener(view -> {
            startActivity(new Intent(this, MainActivity.class));
        });
    }
}