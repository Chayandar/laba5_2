package com.example.laba5_2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int PLAYER1_REQUEST_CODE = 1;
    private static final int PLAYER2_REQUEST_CODE = 2;

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Action = null; // Сохраняем выбор первого игрока

    private TextView resultTextView;
    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);

        Button player1Button = findViewById(R.id.player1Button);
        player1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(intent, PLAYER1_REQUEST_CODE);
            }
        });

        Button player2Button = findViewById(R.id.player2Button);
        player2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(intent, PLAYER2_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == PLAYER1_REQUEST_CODE || requestCode == PLAYER2_REQUEST_CODE) {
                if (data != null) {
                    String action = data.getStringExtra("action");
                    if (requestCode == PLAYER1_REQUEST_CODE) {
                        player1Action = action; // Сохраняем выбор первого игрока
                    } else {
                        processAction(player1Action, action); // Сравниваем выборы обоих игроков
                    }
                }
            }
        }
    }

    private void processAction(String player1Action, String player2Action) {
        if (player1Action.equals(player2Action)) {
            resultTextView.setText("Ничья!");
        } else if ((player1Action.equals("Камень") && player2Action.equals("Ножницы")) ||
                (player1Action.equals("Ножницы") && player2Action.equals("Бумага")) ||
                (player1Action.equals("Бумага") && player2Action.equals("Камень"))) {
            player1Score++;
            resultTextView.setText("Игрок 1 выиграл!");
        } else {
            player2Score++;
            resultTextView.setText("Игрок 2 выиграл!");
        }

        // Обновление счета
        scoreTextView.setText("Счет: Игрок 1 - " + player1Score + ", Игрок 2 - " + player2Score);
    }
}
