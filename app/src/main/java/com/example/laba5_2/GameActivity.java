package com.example.laba5_2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button rockButton = findViewById(R.id.rockButton);
        Button scissorsButton = findViewById(R.id.scissorsButton);
        Button paperButton = findViewById(R.id.paperButton);

        Intent intent = getIntent();
        final int player = intent.getIntExtra("player", 0);

        rockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnResult(player, "Камень");
            }
        });

        scissorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnResult(player, "Ножницы");
            }
        });

        paperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnResult(player, "Бумага");
            }
        });
    }

    private void returnResult(int player, String action) {
        Intent intent = new Intent();
        intent.putExtra("player", player);
        intent.putExtra("action", action);
        setResult(RESULT_OK, intent);
        finish();
    }
}
