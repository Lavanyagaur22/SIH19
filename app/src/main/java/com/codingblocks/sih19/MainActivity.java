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

    Button btn, viewImmunizationButton , phoneVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        immunizationLinearLayout = findViewById(R.id.immunizationLinearLayout);
        govtSchemsBtn=findViewById(R.id.govtSchmes);
        viewImmunizationButton = findViewById(R.id.viewImmunizationButton);
        phoneVerify = findViewById(R.id.phoneVerifyButton);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        immuneList = new ArrayList<>();

        databaseReference1 = firebaseDatabase.getReference("CerebralPalsy/Personal Details/" + firebaseUser.getUid() + "/Initial Detail");

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dob = dataSnapshot.child("DOB").getValue(String.class);

                final Date currentDate = new Date();

                databaseReference = firebaseDatabase.getReference("CerebralPalsy/Immunization");

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        databaseReference2 = firebaseDatabase.getReference("CerebralPalsy/Personal Details/" + firebaseUser.getUid() + "/MyImmunization");

                        databaseReference2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                // ToDO: Get all immunization

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        immunizationSnapshot = dataSnapshot;

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
                            row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
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



        viewImmunizationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ImmunizationActivity.class));
            }
        });

        govtSchemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            /*  Intent intent = new Intent(MainActivity.this,GovtSchemsActivity.class);
              startActivity(intent);*/

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                final String dateString = dateFormat.format(date);

                String currDay = dateString.substring(0,2);
                String currMonth = dateString.substring(3,5);
                String currYear = dateString.substring(6,10);
                String age =  AgeCalculater.findAge(Integer.parseInt(currDay),Integer.parseInt(currMonth),Integer.parseInt(currYear),12,1,2018);

                Intent intent = new Intent(MainActivity.this,DietActivity.class);
                intent.putExtra("Age",age);
                startActivity(intent);
            }
        });

        
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ActivityMapsCurrentPlace.class);
                startActivity(intent);
            }
        });
        phoneVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NumberDragDropGameActivity.class);
                startActivity(intent);
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
