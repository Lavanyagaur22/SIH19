package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DietActivity extends AppCompatActivity {

    Button breakFastTV,lunchTV,snacksTV,dinnerTV;
    LinearLayout breakLL,lunchLL,snacksLL,dinnerLL;
    DatabaseReference databaseReference;
    ImageView backbutton;
    String age,child;
    String year ,month,day,brekFast,lunch,snacks,dinner;
    String[] b ,l,s,d;
    int count;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        breakFastTV = findViewById(R.id.breakFast);
        lunchTV = findViewById(R.id.lunch);
        snacksTV = findViewById(R.id.snacks);
        dinnerTV= findViewById(R.id.dinner);

        breakLL = findViewById(R.id.linerbreakfast);
        lunchLL = findViewById(R.id.linearlunch);
        snacksLL = findViewById(R.id.linearsnacks);
        dinnerLL = findViewById(R.id.lineardinner);
        backbutton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        age = intent.getStringExtra("Age");
        String [] agearray = age.split("\\$");
        year = agearray[0];
        month= agearray[1];
        day=agearray[2];
        Log.e("year" ,year+month+day);
        if(Integer.parseInt(year)>=4)
        {
            child="4-6 Years";
        }
       else if(Integer.parseInt(year)==3)
        {
            child = "3 Years";
        }
       else if(Integer.parseInt(year)==2)
        {
            child="24 Months";
        }
      else if(Integer.parseInt(year)==1&&Integer.parseInt(month)>=9)
        {
            child="21 months";
        }
      else if(Integer.parseInt(year)==1&&Integer.parseInt(month)>=6)
        {
            child="18 Months";
        }
        else if(Integer.parseInt(year)==1&&Integer.parseInt(month)>=3)
        {
            child="15 Months";
        }
        else if(Integer.parseInt(year)==1)
        {
            child="12 Months";
        }
        else if(Integer.parseInt(year)==0&&Integer.parseInt(month)>=6)
        {
            child="6 Months";
        }
        else if(Integer.parseInt(year)==0&&Integer.parseInt(month)>=9)
        {
            child="9 Months";
        }


Log.e("child",child);
        databaseReference = FirebaseDatabase.getInstance().getReference("CerebralPalsy/Diet/");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(dataSnapshot.hasChild(child)) {
                        brekFast = snapshot.child("Breakfast").getValue(String.class);
                        lunch = snapshot.child("Lunch").getValue(String.class);
                        snacks = snapshot.child("Snacks").getValue(String.class);
                        dinner = snapshot.child("Dinner").getValue(String.class);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        breakFastTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   count=0;
                b = brekFast.split("\\$");
                final TextView[] myTextViews = new TextView[b.length];


                for(String s : b) {
                    final TextView rowTextView = new TextView(getBaseContext());
                    rowTextView.setText("*  " + s);
                    breakLL.addView(rowTextView);
                    myTextViews[count] = rowTextView;
                    count++;
                }
            }
        });
        lunchTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 count=0;
                l = lunch.split("\\$");
                final TextView[] myTextViews = new TextView[l.length];


                for(String s : l) {
                    final TextView rowTextView = new TextView(getBaseContext());
                    rowTextView.setText("*  " + s);
                    lunchLL.addView(rowTextView);
                    myTextViews[count] = rowTextView;
                    count++;
                }
            }
        });
        snacksTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        count=0;
                s = snacks.split("\\$");
                final TextView[] myTextViews = new TextView[s.length];


                for(String s : s) {
                    final TextView rowTextView = new TextView(getBaseContext());
                    rowTextView.setText("*  " + s);
                    snacksLL.addView(rowTextView);
                    myTextViews[count] = rowTextView;
                    count++;
                }
            }
        });
       dinnerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        count=0;
                d = dinner.split("\\$");
                final TextView[] myTextViews = new TextView[d.length];


                for(String s : d) {
                    final TextView rowTextView = new TextView(getBaseContext());
                    rowTextView.setText("*  " + s);
                    dinnerLL.addView(rowTextView);
                    myTextViews[count] = rowTextView;
                    count++;
                }
            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }


}

