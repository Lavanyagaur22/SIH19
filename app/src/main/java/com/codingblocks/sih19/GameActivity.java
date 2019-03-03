package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity {

    ImageView dragDrop,colourchange,quiz,object,pitchure,match,backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        dragDrop = findViewById(R.id.imageView9);

        colourchange = findViewById(R.id.imageView2);
        object = findViewById(R.id.imageView8);
        quiz = findViewById(R.id.imageView12);
        match = findViewById(R.id.imageView10);
        pitchure = findViewById(R.id.imageView);
        backButton = findViewById(R.id.backButton);
        dragDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,DragLevel1.class);
                startActivity(intent);
            }
        });
        colourchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,ColorActivity1.class);
                startActivity(intent);
            }
        });
        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,QuizGame.class);
                startActivity(intent);
            }
        });
        object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,ColourDailyObject.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,DragDropLevel2.class);
                startActivity(intent);
            }
        });
        pitchure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameActivity.this,FruitDragDropActivity.class);
                startActivity(intent);

            }
        });



    }
}
