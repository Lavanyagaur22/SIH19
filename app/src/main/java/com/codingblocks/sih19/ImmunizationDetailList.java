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

import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImmunizationDetailList extends RecyclerView.Adapter<ImmunizationDetailList.MyHolder> {

    DataSnapshot immuneLevel;
    Context context;
    List<String> immuneList;

    public ImmunizationDetailList(List<String> immuneList, DataSnapshot immuneLevel, Context context) {

        this.context = context;
        this.immuneList = immuneList;
        this.immuneLevel = immuneLevel;

    }

    @NonNull
    @Override
    public ImmunizationDetailList.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.immunization_row, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ImmunizationDetailList.MyHolder holder, final int position) {

        holder.immunizationTextView.setText(immuneList.get(position));

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
