package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.codingblocks.sih19.NearbyPlace.ActivityMapsCurrentPlace;

public class MainActivity1 extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        imageView1 = findViewById(R.id.imageView5);
        imageView2 = findViewById(R.id.imageView6);
        imageView3 = findViewById(R.id.imageView7);
        imageView4 = findViewById(R.id.imageView8);
        imageView5 = findViewById(R.id.imageView9);
        imageView6 = findViewById(R.id.imageView10);
        imageView7 = findViewById(R.id.imageView11);
        imageView8 = findViewById(R.id.imageView12);
        imageView9 = findViewById(R.id.imageView13);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity1.this,HelpActivity.class));
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,HelpActivity.class));
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,GamesActivity.class));
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,ImmunizationActivity.class));
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this, ActivityMapsCurrentPlace.class));
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,HelpActivity.class));
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,GovtSchemsActivity.class));
            }
        });
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,DataBank.class));
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,DietActivity.class));
            }
        });

    }
}
