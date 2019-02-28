package com.codingblocks.sih19;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static android.util.Config.LOGD;

public class MainActivity extends AppCompatActivity {

    Button govtSchemsBtn;

    LinearLayout immunizationLinearLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Date dob;

    DataSnapshot immunizationSnapshot;
    DataSnapshot immuneLevel;

    int daysAge, vaccineCount;

    ArrayList<String> immuneList;

    Button btn, viewImmunizationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        immunizationLinearLayout = findViewById(R.id.immunizationLinearLayout);
        govtSchemsBtn=findViewById(R.id.govtSchmes);
        viewImmunizationButton = findViewById(R.id.viewImmunizationButton);

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
                    row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
                    CheckBox checkBox = new CheckBox(MainActivity.this);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            
                            // ToDo: Do something

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



        /*databaseReference = firebaseDatabase.getReference("Personal Details/" + firebaseUser.getUid());
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
        });*/

        viewImmunizationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, ImmunizationActivity.class));
            }
        });

        govtSchemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,GovtSchemsActivity.class);
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


    }

}
