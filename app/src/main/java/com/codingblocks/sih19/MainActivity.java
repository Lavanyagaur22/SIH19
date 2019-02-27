package com.codingblocks.sih19;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Button;

import com.codingblocks.sih19.NearbyPlace.ActivityMapsCurrentPlace;
import com.codingblocks.sih19.NearbyPlace.GetNearbyPlacesData;

public class MainActivity extends AppCompatActivity {

    Button govtSchemsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        govtSchemsBtn=findViewById(R.id.govtSchmes);
        govtSchemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,HelpActivity.class);
              startActivity(intent);
            }
        });
        Button btn=findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ActivityMapsCurrentPlace.class);
                startActivity(intent);
            }
        });
    }

}
