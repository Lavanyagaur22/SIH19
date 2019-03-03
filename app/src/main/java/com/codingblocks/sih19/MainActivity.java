package com.codingblocks.sih19;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.codingblocks.sih19.NearbyPlace.ActivityMapsCurrentPlace;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
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

    ArrayList<String> immuneList, myImmuneList;
    String age ,year,month,day,child,brekFast;

    Button btn, viewImmunizationButton , phoneVerify,brekfastBtn;
    int count=0;
    String []b;
    LinearLayout breakLL;
    ImageView profileImageView;
    TextView viewFullChart,viewMoreImmunization,morePlaces,viewMorePolices,schemeContent;
    private StorageReference storageReference;

    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static final int WRITE_EXTERNAL_STORAGE = 101;

    int flag = 0;
    File selectedImage=null;
    File compressedImage = null;
    Uri imageUri;

    LayoutInflater inflater;
    TextView custom_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        myImmuneList = new ArrayList<>();

        CardView cardView=findViewById(R.id.profileInfoCardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,BMIGraphActivity.class);
                startActivity(intent);
            }
        });
        immunizationLinearLayout = findViewById(R.id.immunizationLinearLayout);
        phoneVerify = findViewById(R.id.goButton);
        viewFullChart = findViewById(R.id.viewFullChartButton);
        viewMoreImmunization = findViewById(R.id.viewImmunizationButton);
        morePlaces = findViewById(R.id.morePlacesTextView);
        viewMorePolices = findViewById(R.id.morePoliciesButton);
        schemeContent = findViewById(R.id.textView34);
        brekfastBtn = findViewById(R.id.textView23);
        breakLL = findViewById(R.id.breakLL);
        profileImageView =findViewById(R.id.profileImageView);

        storageReference = FirebaseStorage.getInstance().getReference().child("Documents");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        WRITE_EXTERNAL_STORAGE);
            }
        }

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

        LinearLayout linearLayout2=findViewById(R.id.gameButton);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout=findViewById(R.id.profileButton);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.edvard.poseestimation");
                if (launchIntent != null) {
                    startActivity(launchIntent);//null pointer check in case package name was not found
                }
            }
        });

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View viewDialog = inflater.inflate(R.layout.dilog_document, null);

                radioGroup  = viewDialog.findViewById(R.id.radiogroup);
                 cameraRB = viewDialog.findViewById(R.id.radioCamera);
                galleryRB = findViewById(R.id.radioGallery);

                /*final View titleView = inflater.inflate(R.layout.custom_titleroom, null);
                custom_title = titleView.findViewById(R.id.roomCustomTitle);
*/
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setIcon(getResources().getDrawable(R.drawable.add_circle));
                builder.setIcon(R.drawable.add_circle);
                builder.setView(viewDialog);

                final int index = 0;
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        View radioButton = radioGroup.findViewById(i);
                        int index = radioGroup.indexOfChild(radioButton);
                        Log.e("infsa",index+"");
                        switch (index) {
                            case 0:

                                index =0;
                                break;
                            case 1:
                                index =1;
                                break;
                        }
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                            if (index == 0) {
                                ContentValues values = new ContentValues();
                                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                                imageUri = getContentResolver().insert(
                                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                                            != PackageManager.PERMISSION_GRANTED) {
                                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                                MY_CAMERA_PERMISSION_CODE);
                                    } else {
                                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                                    }

                                    flag = 1;
                                } else {
                                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                    flag = 1;
                                }

                            } else {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_CODE);


                                flag = 1;
                            }

                    }


                });

                builder.setNegativeButton("Cancel", null);
                builder.show();
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
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        immuneList = new ArrayList<>();

                        myImmuneList.clear();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                            myImmuneList.add(snapshot.getKey());
                        }

                        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Immunization");
                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        databaseReference = firebaseDatabase.getReference("CerebralPalsy/Immunization");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                immunizationSnapshot = dataSnapshot;

                                Date currentDate = new Date();

                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                daysAge = getCountOfDays(dob, dateFormat.format(currentDate));

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
                                    final CheckBox checkBox = new CheckBox(MainActivity.this);
                                    if (myImmuneList.contains(immuneList.get(i))) {
                                        checkBox.setChecked(true);
                                    }
                                    databaseReference2 = firebaseDatabase.getReference("CerebralPalsy/Personal Details/" + firebaseUser.getUid() + "/MyImmunization");
                                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    TableRow row =new TableRow(MainActivity.this);
                    row.setId(i);
                    row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    CheckBox checkBox = new CheckBox(MainActivity.this);
                    checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                            if (!checkBox.isChecked()) {

                                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                                builder.setMessage("Message");
                                                builder.setMessage("Are you sure you wanna uncheck?");
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
                                                builder.setMessage("Are you sure you wanna check?");
                                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {

                                                        databaseReference2.child(checkBox.getText().toString()).setValue(daysAge);

                                                    }
                                                });
                                                builder.setNegativeButton("No", null);
                                                builder.show();
                                            }
                            Toast.makeText(MainActivity.this, "Number of days is hardcoded", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                    checkBox.setId(i);
                                    checkBox.setText(immuneList.get(i));
                                    row.addView(checkBox);
                                    immunizationLinearLayout.addView(row);
                                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            Bitmap bitmap = null;

            try {
                selectedImage = FileUtil.from(MainActivity.this, imageUri);

                try {
                    compressedImage = new Compressor(MainActivity.this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(80)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .compressToFile(selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeFile(compressedImage.getPath());


            if ("doc"!=null) {


                databaseReference.child("Personal Detail").child("Documents").setValue("child");

                final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("doc" + " " + "Uploading")
                        .setContentText("Uploading in progress")
                        .setSmallIcon(R.drawable.add_circle).setOngoing(true)
                        .setPriority(NotificationCompat.PRIORITY_LOW);

                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);
                notificationManager.notify(101, builder.build());


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UploadTask uploadTask = storageReference.child("child").putFile(Uri.fromFile(compressedImage));
                        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.e("progress", taskSnapshot.getBytesTransferred() + "");
                                builder.setContentText("Uploading")
                                        .setProgress(498110, ((int) taskSnapshot.getBytesTransferred()), false);
                                Log.e("progress", "");
                                notificationManager.notify(101, builder.build());

                            }
                        });
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                                // progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                builder.setContentText("Uploaded").setOngoing(false)
                                        .setProgress(0, 0, false);
                                Log.e("progress", "");
                                notificationManager.notify(101, builder.build());


                            }
                        });
                    }
                }).start();
            }

        }
        else
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            File selectedImage;
            Bitmap bitmap = null;
            Uri uri=data.getData();

            try {
                selectedImage = FileUtil.from(MainActivity.this, data.getData());
                Log.e("actualimage",selectedImage+"");
                try {
                    compressedImage = new Compressor(MainActivity.this)
                            .setMaxWidth(640)
                            .setMaxHeight(480)
                            .setQuality(75)
                            .setCompressFormat(Bitmap.CompressFormat.JPEG)
                            .compressToFile(selectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("length",compressedImage.length()+"");

            } catch (IOException e) {
                e.printStackTrace();
            }
            bitmap = BitmapFactory.decodeFile(compressedImage.getPath());


            if ("child" != null) {

                databaseReference = firebaseDatabase.getReference();
                databaseReference.child("Personal Detail").child("Documents").child("child").setValue("Pending");

                final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                final NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("child"+"Uploading")
                        .setContentText("Uploading in progress")
                        .setSmallIcon(R.drawable.add_circle).setOngoing(true)
                        .setPriority(NotificationCompat.PRIORITY_LOW);

                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pi);
                notificationManager.notify(101, builder.build());

                final int size= (int) compressedImage.length();


                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        UploadTask uploadTask = storageReference.child("child").putFile(Uri.fromFile(compressedImage));
                        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                Log.e("progress",taskSnapshot.getBytesTransferred()+"");
                                builder.setContentText("Uploading")
                                        .setProgress(size+1, ((int) taskSnapshot.getBytesTransferred()),false);
                                Log.e("progress","");
                                notificationManager.notify(101, builder.build());

                            }
                        });
                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {

                                Toast.makeText(MainActivity.this, "Image Upload Failed", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                builder.setContentText("Uploaded").setOngoing(false)
                                        .setProgress(0,0,false);
                                Log.e("progress","");
                                notificationManager.notify(101, builder.build());


                            }
                        });
                    }
                }).start();

            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }
        else
        if (requestCode==WRITE_EXTERNAL_STORAGE)
        {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show();
            }
        }

    }



    @Override
    public void onBackPressed() {

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

}

