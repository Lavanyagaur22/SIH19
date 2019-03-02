package com.codingblocks.sih19;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.codingblocks.sih19.NearbyPlace.ActivityMapsCurrentPlace;
import com.codingblocks.sih19.NearbyPlace.GetNearbyPlacesData;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button govtSchemsBtn;

    LinearLayout immunizationLinearLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,databaseReference1,databaseReference2;

    Date dob;

    DataSnapshot immunizationSnapshot;
    DataSnapshot immuneLevel;

    int daysAge, vaccineCount;

    ArrayList<String> immuneList;
    String age ,year,month,day,child,brekFast;

    Button btn, viewImmunizationButton , phoneVerify,brekfastBtn;
    int count=0;
    String []b;
    LinearLayout breakLL;
    TextView viewFullChart,viewMoreImmunization,morePlaces,viewMorePolices,schemeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        immunizationLinearLayout = findViewById(R.id.immunizationLinearLayout);
        phoneVerify = findViewById(R.id.goButton);
        viewFullChart = findViewById(R.id.viewFullChartButton);
        viewMoreImmunization = findViewById(R.id.viewImmunizationButton);
        morePlaces = findViewById(R.id.morePlacesTextView);
        viewMorePolices = findViewById(R.id.morePoliciesButton);
        schemeContent = findViewById(R.id.textView34);
        brekfastBtn = findViewById(R.id.textView23);
        breakLL = findViewById(R.id.breakLL);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        final String dateString = dateFormat.format(date);

        String currDay = dateString.substring(0,2);
        String currMonth = dateString.substring(3,5);
        String currYear = dateString.substring(6,10);
        age = AgeCalculater.findAge(Integer.parseInt(currDay),Integer.parseInt(currMonth),Integer.parseInt(currYear),12,1,2018);
        viewFullChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DietActivity.class);
                intent.putExtra("Age",age);
                startActivity(intent);
            }
        });


        viewMorePolices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GovtSchemsActivity.class);
                startActivity(intent);
            }
        });
        viewMoreImmunization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ImmunizationActivity.class);
                startActivity(intent);
            }
        });
        morePlaces.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityMapsCurrentPlace.class);
                startActivity(intent);
            }
        });

        brekfastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                                       }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                brekfastBtn.setOnClickListener(new View.OnClickListener() {
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
            }
        });
        databaseReference1= FirebaseDatabase.getInstance().getReference("CerebralPalsy/GovtSchems/1stScheme/");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                schemeContent.setText(dataSnapshot.child("Content").getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        immuneList = new ArrayList<>();

        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Immunization");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                immunizationSnapshot = dataSnapshot;

                daysAge = 25;
                
                // ToDo: Get the daysAge from DOB

                if (daysAge < 45) {
                    immuneLevel = immunizationSnapshot.child("0");
                }
                else if (daysAge > 45 && daysAge < 75) {
                    immuneLevel = immunizationSnapshot.child("45");
                }
                else if (daysAge > 75 && daysAge < 105) {
                    immuneLevel = immunizationSnapshot.child("75");
                }
                else if (daysAge > 105 && daysAge < 270) {
                    immuneLevel = immunizationSnapshot.child("105");
                }
                else if (daysAge > 270 && daysAge < 480) {
                    immuneLevel = immunizationSnapshot.child("270");
                }
                else if (daysAge > 480 && daysAge < 540) {
                    immuneLevel = immunizationSnapshot.child("480-540");
                }
                else if (daysAge > 1825) {
                    immuneLevel = immunizationSnapshot.child("1825");
                }


                for (DataSnapshot snapshot : immuneLevel.getChildren()) {
                    immuneList.add(snapshot.getKey());
                    vaccineCount++;
                }

                for (int i = 0; i < vaccineCount; i++) {


                    TableRow row =new TableRow(MainActivity.this);
                    row.setId(i);
                    row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    CheckBox checkBox = new CheckBox(MainActivity.this);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                            Toast.makeText(MainActivity.this, "Number of days is hardcoded", Toast.LENGTH_SHORT).show();

                        }
                    });
                    checkBox.setId(i);
                    checkBox.setText(immuneList.get(i));
                    row.addView(checkBox);
                    immunizationLinearLayout.addView(row);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        databaseReference.keepSynced(true);

        databaseReference.child("Initial Detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dob = dataSnapshot.child("DOB").getValue(Date.class);

                // ToDo: Calculate exact age in days

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
