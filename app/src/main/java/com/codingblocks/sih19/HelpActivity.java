package com.codingblocks.sih19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HelpActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RelativeLayout whatsAppButton, gmailButton;
    RecyclerView youTubeRecyclerView;

    DatabaseReference databaseReference;

    ImageView backButton;
    SearchView searchView;
    List<String> youTubeIDList;
    List<String> descriptionList;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;

    DataSnapshot immunizationSnapshot, immuneLevel;

    int daysAge;

    ArrayList<String> immuneList;

    Button level1Button, level2Button, level3Button, level4Button, level5Button, level6Button, level7Button;
    RecyclerView immunizationListView;

    ImmunizationDetailList immunizationDetailList;
    YouTubeDetailList youTubeDetailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        youTubeIDList = new ArrayList<>();
        descriptionList = new ArrayList<>();

        backButton = findViewById(R.id.backButton);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firebaseDatabase = FirebaseDatabase.getInstance();

        immuneList = new ArrayList<>();

        level1Button = findViewById(R.id.level1Button);
        level2Button = findViewById(R.id.level2Button);
        level3Button = findViewById(R.id.level3Button);
        level4Button = findViewById(R.id.level4Button);

       // youTubeRecyclerView = findViewById(R.id.youtubeRecyclerView);

        databaseReference = firebaseDatabase.getReference("CerebralPalsy/HelpVideos/YouTube");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                immunizationSnapshot = dataSnapshot;

                youTubeIDList.clear();
                descriptionList.clear();


                for (DataSnapshot snapshot : dataSnapshot.child("YouTube").getChildren()) {

                    String id = snapshot.getKey();
                    String description = snapshot.getValue(String.class);


                }

                youTubeDetailList = new YouTubeDetailList(youTubeIDList, descriptionList, HelpActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
                youTubeRecyclerView.setLayoutManager(layoutManager);
                youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                youTubeRecyclerView.setAdapter(youTubeDetailList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


                level1Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        immuneLevel = immunizationSnapshot.child("Cerebral Palsy");

                        immuneList.clear();

                        for (DataSnapshot snapshot : immuneLevel.getChildren()) {
                            youTubeIDList.add(snapshot.getKey());
                            descriptionList.add(snapshot.getValue(String.class));
                        }

                        youTubeDetailList = new YouTubeDetailList(youTubeIDList, descriptionList, HelpActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
                        youTubeRecyclerView.setLayoutManager(layoutManager);
                        youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        youTubeRecyclerView.setAdapter(immunizationDetailList);
                        youTubeRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_fall_down));


                    }
                });

                level2Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        immuneLevel = immunizationSnapshot.child("Cerebral Palsy");

                        immuneList.clear();

                        for (DataSnapshot snapshot : immuneLevel.getChildren()) {
                            youTubeIDList.add(snapshot.getKey());
                            descriptionList.add(snapshot.getValue(String.class));
                        }

                        youTubeDetailList = new YouTubeDetailList(youTubeIDList, descriptionList, HelpActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
                        youTubeRecyclerView.setLayoutManager(layoutManager);
                        youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        youTubeRecyclerView.setAdapter(immunizationDetailList);
                        youTubeRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_fall_down));

                    }
                });

                level3Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        immuneLevel = immunizationSnapshot.child("Cerebral Palsy");

                        immuneList.clear();

                        for (DataSnapshot snapshot : immuneLevel.getChildren()) {
                            youTubeIDList.add(snapshot.getKey());
                            descriptionList.add(snapshot.getValue(String.class));
                        }

                        youTubeDetailList = new YouTubeDetailList(youTubeIDList, descriptionList, HelpActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
                        youTubeRecyclerView.setLayoutManager(layoutManager);
                        youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        youTubeRecyclerView.setAdapter(immunizationDetailList);
                        youTubeRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_fall_down));


                    }
                });

                level4Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        immuneLevel = immunizationSnapshot.child("Cerebral Palsy");

                        immuneList.clear();

                        for (DataSnapshot snapshot : immuneLevel.getChildren()) {
                            youTubeIDList.add(snapshot.getKey());
                            descriptionList.add(snapshot.getValue(String.class));
                        }

                        youTubeDetailList = new YouTubeDetailList(youTubeIDList, descriptionList, HelpActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
                        youTubeRecyclerView.setLayoutManager(layoutManager);
                        youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                        youTubeRecyclerView.setAdapter(immunizationDetailList);
                        youTubeRecyclerView.setLayoutAnimation(AnimationUtils.loadLayoutAnimation(getApplicationContext(),R.anim.layout_fall_down));


                    }
                });



                backButton.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        youTubeDetailList.filter(text);

        return true;
    }
}
