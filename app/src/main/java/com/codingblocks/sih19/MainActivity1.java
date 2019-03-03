package com.codingblocks.sih19;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
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

import java.util.ArrayList;

public class MainActivity1 extends AppCompatActivity {

    TextView tvchangeLanguage;
    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,notification;
    CardView cardView;
    String age;
    SharedPreferences sharedPreferences;
    String languageCode;
    TextView logoutTextView;
    TextView videoDiag;
    TextView speechTherapy;
    TextView gamesSection;
    TextView immunityReminder;
    TextView nearbyHelp;
    TextView educationVideos;
    TextView govtSchemes;
    TextView sos;
    TextView diet;
    TextView hellobaby;
    TextView bmi;
    TextView t;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        imageView1 = findViewById(R.id.imageView5);
        imageView2 = findViewById(R.id.imageView6);
        hellobaby=findViewById(R.id.pgNameTextView);
        t=findViewById(R.id.a);
        bmi=findViewById(R.id.any);

        imageView3 = findViewById(R.id.imageView7);
        imageView4 = findViewById(R.id.imageView8);
        imageView5 = findViewById(R.id.imageView9);
        imageView6 = findViewById(R.id.imageView10);
        imageView7 = findViewById(R.id.imageView11);
        imageView8 = findViewById(R.id.imageView12);
        imageView9 = findViewById(R.id.imageView13);

        videoDiag=findViewById(R.id.videoTextView);
        speechTherapy=findViewById(R.id.speechTherapy1);
        gamesSection=findViewById(R.id.speechTherapy);
        immunityReminder=findViewById(R.id.immunity);
       // nearbyHelp=findViewById(R.id.nearby);
        diet=findViewById(R.id.diet);
        TextView nearby=findViewById(R.id.nearby);
        educationVideos=findViewById(R.id.edu);
        govtSchemes=findViewById(R.id.govt);
        sos=findViewById(R.id.sos);

        CallLanguage callLanguage;

        callLanguage = new CallLanguage(videoDiag.getText().toString(), MainActivity1.this);
        videoDiag.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(speechTherapy.getText().toString(), MainActivity1.this);
        speechTherapy.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(gamesSection.getText().toString(), MainActivity1.this);
        gamesSection.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(diet.getText().toString(), MainActivity1.this);
        diet.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(immunityReminder.getText().toString(), MainActivity1.this);
        immunityReminder.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(educationVideos.getText().toString(), MainActivity1.this);
        educationVideos.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(govtSchemes.getText().toString(), MainActivity1.this);
        govtSchemes.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(sos.getText().toString(), MainActivity1.this);
        sos.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(hellobaby.getText().toString(), MainActivity1.this);
        hellobaby.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(t.getText().toString(), MainActivity1.this);
        t.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(bmi.getText().toString(), MainActivity1.this);
        bmi.setText(callLanguage.getResult());

        callLanguage = new CallLanguage(nearby.getText().toString(), MainActivity1.this);
        nearby.setText(callLanguage.getResult());

        tvchangeLanguage=findViewById(R.id.changeLanguage);
        cardView= findViewById(R.id.profileCard);
        logoutTextView = findViewById(R.id.logoutTextView);
        sharedPreferences=getSharedPreferences("Language",Context.MODE_PRIVATE);
        languageCode=sharedPreferences.getString("lang","en");
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
        tvchangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SharedPreferences.Editor editor=sharedPreferences.edit();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity1.this);
                builder.setTitle("Choose language");
                builder.setItems(R.array.language, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                editor.putString("lang","en");
                                break;
                            case 1:
                                editor.putString("lang","hi");
                                break;
                            case 2:
                                editor.putString("lang","te");
                                break;
                            case 3:
                                editor.putString("lang","ta");
                                break;
                            case 4:
                                editor.putString("lang","gu");
                                break;
                            case 5:
                                editor.putString("lang","pa");
                                break;
                        }
                        editor.apply();

                    }
                });
                builder.create();
                builder.show();

            }
        });

    }
}
