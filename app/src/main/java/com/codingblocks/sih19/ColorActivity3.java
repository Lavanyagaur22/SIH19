package com.codingblocks.sih19;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ColorActivity3 extends AppCompatActivity {
    DatabaseReference databaseReference;
    MediaPlayer SoundCorrect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_color3);
        final ImageView img1 = findViewById(R.id.img1);
        final ImageView img2 = findViewById(R.id.img2);
        final ImageView img3 = findViewById(R.id.img3);
        final ImageView img4=findViewById(R.id.img4);
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
                        SoundCorrect= MediaPlayer.create(ColorActivity3.this, R.raw.airhorn);
                        SoundCorrect.start();
                        databaseReference.child("Incorrect").setValue(Incorrect+1);
                        Intent intent = new Intent(ColorActivity3.this,GamesActivity.class);
                        startActivity(intent);
                    }
                });
                img2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SoundCorrect= MediaPlayer.create(ColorActivity3.this, R.raw.airhorn);
                        SoundCorrect.start();
                        databaseReference.child("Incorrect").setValue(Incorrect+1);
                        Intent intent = new Intent(ColorActivity3.this,GamesActivity.class);
                        startActivity(intent);
                    }
                });
                img3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SoundCorrect= MediaPlayer.create(ColorActivity3.this, R.raw.airhorn);
                        SoundCorrect.start();
                        databaseReference.child("Incorrect").setValue(Incorrect+1);
                        Intent intent = new Intent(ColorActivity3.this,GamesActivity.class);
                        startActivity(intent);
                    }
                });
                img4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SoundCorrect= MediaPlayer.create(ColorActivity3.this, R.raw.applause);
                        SoundCorrect.start();
                        databaseReference.child("Correct").setValue(Correct+1);
                        Intent intent = new Intent(ColorActivity3.this,GamesActivity.class);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SoundCorrect= MediaPlayer.create(ColorActivity3.this, R.raw.airhorn);
                SoundCorrect.start();
            }
        });

    }
}
