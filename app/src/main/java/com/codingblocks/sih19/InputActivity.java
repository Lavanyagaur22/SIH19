package com.codingblocks.sih19;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        email=  intent.getStringExtra("Email");
        password = intent.getStringExtra("password");

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
                                        databaseReference.child("intial detail").setValue(intialClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });

                                        Date date = new Date();
                                        String key = databaseReference.getKey();
                                        UpdatedClass updatedClass = new UpdatedClass(key, heightEt.getText().toString(), weightEt.getText().toString(), date);

                                        databaseReference.child("updated detail").setValue(updatedClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

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
