package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class DragDropLevel2 extends AppCompatActivity implements View.OnTouchListener,View.OnDragListener{
    private TextView text1,text2,text3,text4;
    FirebaseDatabase firebaseDatabase;
    TextToSpeech textToSpeech;

    DatabaseReference databaseReference;
    long correct ,incorrect;
    String options,text;
    TextView dropped,dropTarget;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP)
        {
            dropped = (TextView)event.getLocalState();
            dropTarget = (TextView) v;

            dropped.setVisibility(View.INVISIBLE);
            text=dropTarget.getText().toString();//target
            options=dropped.getText().toString();
            speak("Apple");
            Log.e("TAG","Target"+text+" options"+options);
            firebaseDatabase = FirebaseDatabase.getInstance();

            databaseReference = firebaseDatabase.getReference("CerebralPalsy/Personal Details/Hr4btxFKRqM05RVElOjSYcqiuaq1").child("Intial Detail").child("DragColour");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Log.e("Snap", dataSnapshot + "");

                    correct = dataSnapshot.child("Correct").getValue(Long.class);
                    incorrect = dataSnapshot.child("Incorrect").getValue(Long.class);
                    Log.e("TAG","Incorrect"+incorrect);
                    if(options.equals("4")&&text.equals("1"))
                    {
                        correct = correct+1;
                        databaseReference.child("Correct").setValue(correct);
                        Toast.makeText(DragDropLevel2.this, "1 one", Toast.LENGTH_SHORT).show();
                        Log.e("TAG","INSIDE 1");

                        dropTarget.setText(dropped.getText());
                        dropTarget.setBackgroundColor(R.drawable.a_img);
                        startActivity(new Intent(DragDropLevel2.this,ColourDragDropActivity.class));
                    }
                    else if(options.equals("3")&&text.equals("2")){
                        correct = correct+1;
                        databaseReference.child("Correct").setValue(correct);
                        Toast.makeText(DragDropLevel2.this, "1 one", Toast.LENGTH_SHORT).show();
                        Log.e("TAG","INSIDE 1");

                        dropTarget.setText(dropped.getText());
                        dropTarget.setBackgroundColor(R.drawable.a_img);
                        startActivity(new Intent(DragDropLevel2.this,ColourDragDropActivity.class));

                    }

                    else{

                        incorrect =incorrect+1;
                        databaseReference.child("Incorrect").setValue(incorrect);
                        Toast.makeText(DragDropLevel2.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        Log.e("TAG","else");
                        dropTarget.setBackgroundColor(R.drawable.r);
                        startActivity(new Intent(DragDropLevel2.this,ColourDragDropActivity.class));

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            /*if(text.equals(text1.getText().toString())) text1.setVisibility(View.VISIBLE);
            else if(text.equals(text2.getText().toString())) text2.setVisibility(View.VISIBLE);
            else if(text.equals(text3.getText().toString())) text3.setVisibility(View.VISIBLE);
*/

        }
        return true;
    }

    private void speak(String apple) {
        textToSpeech.speak(apple,TextToSpeech.QUEUE_FLUSH,null);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        }
        else return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);

        textToSpeech=new TextToSpeech(DragDropLevel2.this, new TextToSpeech.OnInitListener() {
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
                    Toast.makeText(DragDropLevel2.this,"Feature not supported in your device",Toast.LENGTH_SHORT).show();
                }
            }
        });
        text1 = (TextView) findViewById(R.id.textView2);
        text2 = (TextView) findViewById(R.id.textView3);
        text3 = (TextView) findViewById(R.id.textView4);
        text4=findViewById(R.id.textView5);

        text1.setOnDragListener(this);
        text2.setOnDragListener(this);
        text3.setOnTouchListener(this);
        text4.setOnTouchListener(this);
    }
    }
