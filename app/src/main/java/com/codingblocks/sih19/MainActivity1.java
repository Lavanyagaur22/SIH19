package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codingblocks.sih19.NearbyPlace.ActivityMapsCurrentPlace;
import com.codingblocks.sih19.NearbyPlace.Notification;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity1 extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,notification;
    CardView cardView;
    String age;
    TextView logoutTextView;

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
        cardView= findViewById(R.id.profileCard);
        logoutTextView = findViewById(R.id.logoutTextView);

        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity1.this, LoginActivity.class));
            }
        });

        notification = findViewById(R.id.notificationImage);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this, Notification.class));
            }
        });

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.edvard.poseestimation");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,VoiceTherapy.class));
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,GameActivity.class));
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
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                final String dateString = dateFormat.format(date);

                String currDay = dateString.substring(0,2);
                String currMonth = dateString.substring(3,5);
                String currYear = dateString.substring(6,10);
                age = AgeCalculater.findAge(Integer.parseInt(currDay),Integer.parseInt(currMonth),Integer.parseInt(currYear),12,1,2018);
                     Intent intent = new Intent(MainActivity1.this,DietActivity.class);
                intent.putExtra("Age",age);
                startActivity(intent);
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity1.this,ProfileActivity.class));

            }
        });

    }
}
