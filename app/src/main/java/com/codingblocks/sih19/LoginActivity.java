package com.codingblocks.sih19;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
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



public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;

    private EditText etUserEmail,etUserPassword;
    private ImageView btnSignIn;
    private TextView loginToSignIn,forgotPassword;

    long range, i;

    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseDatabase = FirebaseDatabase.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.rgb(47,50,53));
        }

        final EditText input=new EditText(this);

        loginToSignIn = findViewById(R.id.tvLoginToSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        etUserEmail = findViewById(R.id.usernametextView);
        etUserPassword = findViewById(R.id.passwordtextView);
        forgotPassword = findViewById(R.id.forgotPasswordTextView);


        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        //If user kills the app without logging out, he/she should not need to login again.
        if(mFirebaseUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEmail = etUserEmail.getText().toString().trim();
                userPassword = etUserPassword.getText().toString().trim();

                if(!userEmail.isEmpty()&&!userPassword.isEmpty()) {
                    mFirebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "","Signing in..", true);

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                progressDialog.dismiss();

                                mFirebaseUser = mFirebaseAuth.getCurrentUser();

                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    if (userEmail.isEmpty()) {
                        etUserEmail.setError("Required");
                    }
                }
            }
        });


        loginToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getParent()!=null) {
                    ((ViewGroup) input.getParent()).removeView(input);
                }
                final Dialog dialog=new Dialog(LoginActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                Window window = dialog.getWindow();
                dialog.setContentView(R.layout.dialog_forgetpassword);
                dialog.setTitle(" Reset Password");
                Button ok = dialog.findViewById(R.id.okButton);
                Button cancel = dialog.findViewById(R.id.cancelButton);
                final EditText email = dialog.findViewById(R.id.email);
                input.setText(email.toString());

                window.setBackgroundDrawableResource(android.R.color.transparent);
                dialog.show();

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(email.getText().toString().contains("@")) {
                            Log.e("email",email.getText().toString());
                            mFirebaseAuth.sendPasswordResetEmail(input.getText().toString().trim())
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(LoginActivity.this, "Mail Sent!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                        else
                        {
                            Toast.makeText(LoginActivity.this, "Enter Valid Email ID", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }


}
