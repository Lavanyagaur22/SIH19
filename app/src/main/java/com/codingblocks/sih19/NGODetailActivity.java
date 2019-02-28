package com.codingblocks.sih19;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NGODetailActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Button applyBtn ,callBtn;
    String name,detailContent ,website,phone;
    String[] detail_content;
    TextView nameTextView;
    ImageView backbutton;



    int count =0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ngo);
        linearLayout=findViewById(R.id.linerLayout);
        nameTextView = findViewById(R.id.nameTextView);
        applyBtn = findViewById(R.id.applyBtn);
        backbutton = findViewById(R.id.backButton);
        callBtn = findViewById(R.id.CallButton);

        Intent intent1 = getIntent();
        name = intent1.getStringExtra("Name");
        detailContent = intent1.getStringExtra("Ngo Detail");
        website = intent1.getStringExtra("website");
        phone = intent1.getStringExtra("phone");

        detail_content = detailContent.split("\\$");
        final TextView[] myTextViews = new TextView[detail_content.length];


        for(String s : detail_content){
            final TextView rowTextView = new TextView(this);
            rowTextView.setText("*  " +s);
            linearLayout.addView(rowTextView);
            myTextViews[count] = rowTextView;
            count++;
        }

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(NGODetailActivity.this,WebViewActivity.class);
               intent.putExtra("web",website);
               startActivity(intent);
               finish();
            }
        });
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+phone));
                    startActivity(callIntent);
                }
                catch (ActivityNotFoundException activityException) {
                    Toast.makeText(NGODetailActivity.this, "Call failed", Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e) {
                    Toast.makeText(NGODetailActivity.this, "Call failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
