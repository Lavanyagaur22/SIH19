package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GovtDetailActivity  extends AppCompatActivity {

    LinearLayout linearLayout;
    Button applyBtn;
    String name,detailContent;
    String[] detail_content;
    TextView nameTextView;
    ImageView backbutton;
    int count =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scheme);
        linearLayout=findViewById(R.id.linerLayout);
        nameTextView = findViewById(R.id.nameTextView);
        applyBtn = findViewById(R.id.applyBtn);
        backbutton = findViewById(R.id.backButton);

        Intent intent1 = getIntent();
        name = intent1.getStringExtra("Name");
        detailContent = intent1.getStringExtra(R.string.detail_content+"");
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
          startActivity(new Intent(GovtDetailActivity.this,WebViewActivity.class));
          finish();
      }
  });
   backbutton.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           finish();
       }
   });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
