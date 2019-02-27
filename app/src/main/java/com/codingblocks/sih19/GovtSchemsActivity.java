package com.codingblocks.sih19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class GovtSchemsActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;
    ImageView backButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_schems);

        tabLayout = findViewById(R.id.tabLayoutID);
        pager = findViewById(R.id.viewPagerID);

        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GovtSchemsActivity.this, MainActivity.class));
                finish();
            }
        });

        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new SchemsFragment(), "Govt Scheme");
        adapter.addFragment(new NGOFragment(), "NGOs");


        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GovtSchemsActivity.this, MainActivity.class));
        finish();
    }

    }
