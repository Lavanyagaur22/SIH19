package com.codingblocks.sih19;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InputActivity extends AppCompatActivity {

    EditText nameEt,DobET,heightEt,weightEt;
    Button confirmBtn;

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;
    String email,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        nameEt = findViewById(R.id.nameET);
        DobET = findViewById(R.id.dobET);
        heightEt = findViewById(R.id.heightEt);
        weightEt = findViewById(R.id.weightEt);
        confirmBtn = findViewById(R.id.confirmButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Personal Details/");

        firebaseAuth=FirebaseAuth.getInstance();
        final Intent intent = getIntent();
        email=  intent.getStringExtra("email");
        password = intent.getStringExtra("password");

        Log.e("Email Password", email + " " + password);

        final Calendar calendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);

                String myFormat = "dd/MM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
                DobET.setText(sdf.format(calendar.getTime()));

            }
        };

        DobET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(InputActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEt.getText().toString().isEmpty()) {
                    nameEt.setError("required");
                }
                if (DobET.getText().toString().isEmpty()) {
                    DobET.setError("required");
                }
                if (weightEt.getText().toString().isEmpty()) {
                    weightEt.setError("required");
                }
                if (heightEt.getText().toString().isEmpty()) {
                    heightEt.setError("required");
                } else {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(InputActivity.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Personal Details/" + firebaseUser.getUid());
                                        IntialClass intialClass = new IntialClass(DobET.getText().toString(), "male");
                                        databaseReference.child("Initial Detail").setValue(intialClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                startActivity(new Intent(InputActivity.this, MainActivity1.class));
                                            }
                                        });


                                    }
                                }
                            });


                }
            }
        });
    }
}
