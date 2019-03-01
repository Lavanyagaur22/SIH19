package com.codingblocks.sih19;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    SharedPreferences sharedPreferences;

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference, databaseReference1;

    String id;

    TextView signUpToLogin;

    private EditText etUserEmail, etuserpassword,etuserConfirmPassword ;
    private FloatingActionButton btnSignUp;

    private String userEmail, userpassword ;

    @Override
    public void onBackPressed() {
       startActivity(new Intent(SignupActivity.this,LoginActivity.class));
       finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignUp = findViewById(R.id.btnSignUp);
        etUserEmail = findViewById(R.id.emailEditText);       //Test
        etuserpassword = findViewById(R.id.localityEditText);
        etuserConfirmPassword = findViewById(R.id.confirmET);//Test
        //Test
        signUpToLogin = findViewById(R.id.signupToLoginTextView);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userEmail = etUserEmail.getText().toString();
                userpassword = etuserpassword.getText().toString();

                if (etuserpassword.getText().toString().isEmpty()) {
                    etuserpassword.setError("required");
                }
                if (etuserConfirmPassword.getText().toString().isEmpty()) {
                    etuserConfirmPassword.setError("required");
                }
                if (!userEmail.contains("@")) {
                    etUserEmail.setError("Enter Valid Email ID.");
                }
                if (etuserConfirmPassword.getText().toString().length() != etuserpassword.getText().toString().length()) {
                    Toast.makeText(SignupActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                }
                if (checkPassword(etuserConfirmPassword.getText().toString(), etuserpassword.getText().toString()) == 1) {

                    Intent intent = new Intent(SignupActivity.this, InputActivity.class);
                    intent.putExtra("email", etUserEmail.getText().toString());
                    intent.putExtra("password", etuserpassword.getText().toString());
                    startActivity(intent);
                }

            }

        });

        signUpToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                finish();
            }
        });

    }

    public  int checkPassword(String password1,String confpassword)
    {
        for(int i=0;i<password1.length();i++)
        {
            Log.e("TagPass",password1.charAt(i)+" "+confpassword.charAt(i));
            if(password1.charAt(i)!=confpassword.charAt(i))
            {
                Log.e("TagPass","returns 0");
                Toast.makeText(SignupActivity.this,"Confirm Password does not match the Password",Toast.LENGTH_SHORT).show();
                return 0;
            }
        }
        return 1;
    }


}
