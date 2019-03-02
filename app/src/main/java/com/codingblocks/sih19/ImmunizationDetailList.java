package com.codingblocks.sih19;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.gson.JsonParser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImmunizationDetailList extends RecyclerView.Adapter<ImmunizationDetailList.MyHolder> {

    DataSnapshot immuneLevel;
    Context context;
    List<String> immuneList;
    String days;

    DataSnapshot immunizationFAQs;

    List<String> questions, answers;

    Map<String, String> faqs;

    public ImmunizationDetailList(List<String> immuneList, DataSnapshot immuneLevel, String days, Context context) {

        this.context = context;
        this.immuneList = immuneList;
        this.immuneLevel = immuneLevel;
        this.days = days;

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        faqs = new HashMap<>();

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.immunization_row, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {

        holder.immunizationTextView.setText(immuneList.get(position));

        holder.viewFAQTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String immunizationName = immuneList.get(position);
                immunizationFAQs = immuneLevel.child(days).child(immunizationName);

                for (DataSnapshot snapshot : immunizationFAQs.getChildren()) {
                    questions.add(snapshot.getKey());

                    String question = snapshot.getKey();
                    answers.add(immunizationFAQs.child(question).getValue(String.class));
                }

                Log.e("answer", answers.toString());

                Intent intent = new Intent(context, ImmunizationFAQActivity.class);
                intent.putStringArrayListExtra("Question", (ArrayList<String>) questions);
                intent.putStringArrayListExtra("Answer", (ArrayList<String>) answers);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return immuneList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public TextView immunizationTextView, viewFAQTextView;

        public MyHolder(View itemView) {
            super(itemView);

            immunizationTextView = itemView.findViewById(R.id.immunizationTextView);
            viewFAQTextView = itemView.findViewById(R.id.viewFAQTextView);
        }
    }
}
