package com.codingblocks.sih19;

import android.os.Bundle;
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


public class DragLevel1 extends AppCompatActivity implements View.OnTouchListener,View.OnDragListener {

    private TextView text1,text2,text3;
    FirebaseDatabase firebaseDatabase;

    DatabaseReference databaseReference;
    Long correct ,incorrect;
    String options,text;
    TextView dropped,dropTarget;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP)
        {
           dropped = (TextView)event.getLocalState();
             dropTarget = (TextView) v;

            dropped.setVisibility(View.INVISIBLE);
             options=dropped.getText().toString();
             text=dropTarget.getText().toString();//target
            Log.e("TAG","Target"+text+" options"+options);
            firebaseDatabase = FirebaseDatabase.getInstance();

            databaseReference = firebaseDatabase.getReference("CerebralPalsy/Personal Details/Hr4btxFKRqM05RVElOjSYcqiuaq1").child("Intial Detail").child("DragColour");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Log.e("Snap", dataSnapshot + "");

                    correct = dataSnapshot.child("Correct").getValue(Long.class);
                    incorrect = dataSnapshot.child("Incorrect").getValue(Long.class);
                    if(options.equals("3")&&text.equals("1"))
                    {
                        correct = correct+1;
                        databaseReference.child("Correct").setValue(correct);
                        Toast.makeText(DragLevel1.this, "1 one", Toast.LENGTH_SHORT).show();
                        Log.e("TAG","INSIDE 1");

                        dropTarget.setText(dropped.getText());
                        dropTarget.setBackgroundColor(R.drawable.a_img);
                    }
                    else{

                        incorrect =incorrect+1;
                        databaseReference.child("Incorrect").setValue(incorrect);
                        Toast.makeText(DragLevel1.this, "Incorrect", Toast.LENGTH_SHORT).show();
                        Log.e("TAG","else");
                        dropTarget.setBackgroundColor(R.drawable.r);
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
        setContentView(R.layout.activity_level1);

        text1 = (TextView)findViewById(R.id.textView1);
        text2 = (TextView)findViewById(R.id.textView2);
        text3 = (TextView)findViewById(R.id.textView3);

        text1.setOnDragListener(this);
        text2.setOnDragListener(this);
        text3.setOnTouchListener(this);

    }

}
