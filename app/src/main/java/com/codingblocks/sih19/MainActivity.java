package com.codingblocks.sih19;

import android.content.DialogInterface;
import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button govtSchemsBtn;

    LinearLayout immunizationLinearLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1, databaseReference2;

    String dob;

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


        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Personal Details/" + firebaseUser.getUid());
        databaseReference.keepSynced(true);
        databaseReference.child("Initial Detail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dob = dataSnapshot.child("dateOfBirth").getValue(String.class);


                databaseReference.child("MyImmunization").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        immuneList = new ArrayList<>();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            immuneList.add(snapshot.getKey());
                        }

                        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Immunization");
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                immunizationSnapshot = dataSnapshot;

                                Date currentDate = new Date();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                daysAge = getCountOfDays(dateFormat.format(currentDate),dob);

                                Log.e("DaysAge", daysAge + "");

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
                                    final CheckBox checkBox = new CheckBox(MainActivity.this);
                                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                            if (checkBox.isChecked()) {

                                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                builder.setMessage("Message");
                                                builder.setMessage("Are you sure?");
                                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        databaseReference2.child(checkBox.getText().toString()).setValue(null);

                                                    }
                                                });
                                                builder.setNegativeButton("No", null);
                                                builder.show();
                                            }
                                            else {

                                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                builder.setMessage("Message");
                                                builder.setMessage("Are you sure?");
                                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        databaseReference2.child(checkBox.getText().toString()).setValue(daysAge);

                                                    }
                                                });
                                                builder.setNegativeButton("No", null);
                                                builder.show();
                                            }

                                        }
                                    });
                                    checkBox.setId(i);
                                    checkBox.setText(immuneList.get(i));
                                    if (immuneList.contains(immuneList.get(i))) {
                                        checkBox.setChecked(true);
                                    }
                                    row.addView(checkBox);
                                    immunizationLinearLayout.addView(row);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public int getCountOfDays(String createdDateString, String expireDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Date createdConvertedDate = null, expireCovertedDate = null, todayWithZeroTime = null;
        try {
            createdConvertedDate = dateFormat.parse(createdDateString);
            expireCovertedDate = dateFormat.parse(expireDateString);

            Date today = new Date();

            todayWithZeroTime = dateFormat.parse(dateFormat.format(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int cYear = 0, cMonth = 0, cDay = 0;

        if (createdConvertedDate.after(todayWithZeroTime)) {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(createdConvertedDate);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);

        } else {
            Calendar cCal = Calendar.getInstance();
            cCal.setTime(todayWithZeroTime);
            cYear = cCal.get(Calendar.YEAR);
            cMonth = cCal.get(Calendar.MONTH);
            cDay = cCal.get(Calendar.DAY_OF_MONTH);
        }

        Calendar eCal = Calendar.getInstance();
        eCal.setTime(expireCovertedDate);

        int eYear = eCal.get(Calendar.YEAR);
        int eMonth = eCal.get(Calendar.MONTH);
        int eDay = eCal.get(Calendar.DAY_OF_MONTH);

        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        date1.clear();
        date1.set(cYear, cMonth, cDay);
        date2.clear();
        date2.set(eYear, eMonth, eDay);

        long diff = date2.getTimeInMillis() - date1.getTimeInMillis();

        float dayCount = (float) diff / (24 * 60 * 60 * 1000);

        return ((int) dayCount);
    }



}
