package com.codingblocks.sih19;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.google.firebase.FirebaseApp;
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


    YouTubeDetailList youTubeDetailList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        youTubeIDList = new ArrayList<>();
        descriptionList = new ArrayList<>();

        backButton = findViewById(R.id.backButton);

        youTubeRecyclerView = findViewById(R.id.youtubeRecyclerView);

        FirebaseApp.initializeApp(this);


        databaseReference = FirebaseDatabase.getInstance("https://sih19-dd177.firebaseio.com/").getReference("HelpVideos");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                youTubeIDList.clear();
                descriptionList.clear();


                for (DataSnapshot snapshot : dataSnapshot.child("YouTube").getChildren()) {

                    String id = snapshot.getKey();
                    String description = snapshot.getValue(String.class);

                    youTubeIDList.add(id);
                    descriptionList.add(description);
                }

                youTubeDetailList = new YouTubeDetailList(youTubeIDList, descriptionList, HelpActivity.this);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(HelpActivity.this);
                youTubeRecyclerView.setLayoutManager(layoutManager);
                youTubeRecyclerView.setItemAnimator(new DefaultItemAnimator());
                youTubeRecyclerView.setAdapter(youTubeDetailList);
                searchView= (SearchView)findViewById(R.id.seacrhView);
                searchView.setOnQueryTextListener(HelpActivity.this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
