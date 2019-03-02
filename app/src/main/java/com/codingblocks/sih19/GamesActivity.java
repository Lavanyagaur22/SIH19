package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GamesActivity extends AppCompatActivity {
    Button dragDrop,colourchange,quiz,object;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        dragDrop = findViewById(R.id.button1);
        colourchange = findViewById(R.id.button2);
        object = findViewById(R.id.button3);
        quiz = findViewById(R.id.button4);
        dragDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(GamesActivity.this,DragLevel1.class);
             startActivity(intent);
            }
        });
        colourchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesActivity.this,ColorActivity1.class);
                startActivity(intent);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesActivity.this,QuizGame.class);
                startActivity(intent);
            }
        });
        object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GamesActivity.this,ColourDailyObject.class);
                startActivity(intent);
            }
        });
    }

}
