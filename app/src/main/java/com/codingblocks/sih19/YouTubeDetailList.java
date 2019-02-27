package com.codingblocks.sih19;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class YouTubeDetailList extends RecyclerView.Adapter<YouTubeDetailList.MyHolder> {

    List<String> youTubeIDList;
    List<String> descriptionList;
    Context context;
    private ArrayList<String> arraylist;


    public YouTubeDetailList(List<String> youTubeIDList, List<String> descriptionList, Context context) {

        this.context = context;
        this.descriptionList = descriptionList;
        this.youTubeIDList = youTubeIDList;

    }

    @NonNull
    @Override
    public YouTubeDetailList.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.youtube_row, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final YouTubeDetailList.MyHolder holder, final int position) {

        Picasso.get().load("https://img.youtube.com/vi/" + youTubeIDList.get(position) + "/hqdefault.jpg").into(holder.thumbImageView);
        holder.descriptionTextView.setText(descriptionList.get(position));

        holder.youTubeLinearLayout.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + youTubeIDList.get(position)));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + youTubeIDList.get(position)));
                try {
                    context.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    context.startActivity(webIntent);


                }
            }
        }));


    }

    @Override
    public int getItemCount() {
        return youTubeIDList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        public TextView descriptionTextView;
        public ImageView thumbImageView;
        public LinearLayout youTubeLinearLayout;

        public MyHolder(View itemView) {
            super(itemView);

            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            thumbImageView = itemView.findViewById(R.id.thumbnailImageView);
            youTubeLinearLayout = itemView.findViewById(R.id.youTubeLinearLayout);
        }
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        descriptionList.clear();
        if (charText.length() == 0) {
            descriptionList.addAll(arraylist);
        } else {

        }
        notifyDataSetChanged();
    }

}
