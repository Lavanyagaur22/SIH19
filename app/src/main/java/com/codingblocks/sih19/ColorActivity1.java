package com.codingblocks.sih19;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ColorActivity1 extends AppCompatActivity {

    DatabaseReference databaseReference;
    MediaPlayer SoundCorrect;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_color1);
        final ImageView img1=findViewById(R.id.img1);
        final ImageView img2=findViewById(R.id.img2);
        textToSpeech=new TextToSpeech(ColorActivity1.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS)
                {
                    int result=textToSpeech.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                    Toast.makeText(ColorActivity1.this,"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }
            }
        });
      //  textToSpeech.speak("Show the blue",TextToSpeech.QUEUE_FLUSH,null);
        databaseReference=FirebaseDatabase.getInstance().getReference("CerebralPalsy").child("Personal Details").child("Hr4btxFKRqM05RVElOjSYcqiuaq1").child("Intial Detail").child("ColorGame");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                final long Correct=  dataSnapshot.child("Correct").getValue(Long.class);
                final long Incorrect= dataSnapshot.child("Incorrect").getValue(Long.class);
                Log.e("TAG","Correct"+Correct);
                img1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SoundCorrect= MediaPlayer.create(ColorActivity1.this, R.raw.airhorn);
                        SoundCorrect.start();
                        databaseReference.child("Incorrect").setValue(Incorrect+1);
                        Intent intent=new Intent(ColorActivity1.this,ColorActivity2.class);
                        startActivity(intent);
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SoundCorrect= MediaPlayer.create(ColorActivity1.this, R.raw.applause);
                        SoundCorrect.start();
                        databaseReference.child("Correct").setValue(Correct+1);
                        Intent intent=new Intent(ColorActivity1.this,ColorActivity2.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

      /*  Intent intent=new Intent(ColorActivity1.this,ColorActivity2.class);
        startActivity(intent);
*/
   /*     Intent intent=new Intent(ColorActivity1.this,ColorActivity2.class);
        startActivity(intent);*/

    }

    @Override
    protected void onStart() {
        Log.e("TAG","onResume");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textToSpeech.speak("Show the blue",TextToSpeech.QUEUE_ADD,null);
        super.onStart();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
