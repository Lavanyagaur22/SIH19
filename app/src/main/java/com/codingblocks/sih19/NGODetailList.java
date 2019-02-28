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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class NGODetailList extends RecyclerView.Adapter<NGODetailList.MyHolder> {

    List<NGODetailClass> ngoDetailClassList;

    private Context context;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public NGODetailList.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ngo_scheme_row, parent, false);

        return new NGODetailList.MyHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final NGODetailList.MyHolder holder, int position) {

        final NGODetailClass ngoDetailClass = ngoDetailClassList.get(position);

      /*  StorageReference storageReference = storage.getReferenceFromUrl("gs://sih19-dd177.appspot.com").child("GovtSchems");

        final long ONE_MEGABYTE = 1024 * 1024;

        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.schemeImage.setImageBitmap(bitmap);
            }
        });*/

        holder.nameTV.setText(ngoDetailClass.getName());
        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:"+ngoDetailClass.getContactNo()));
                    context.startActivity(callIntent);
                }
                catch (ActivityNotFoundException activityException) {
                    Toast.makeText(context, "Call failed", Toast.LENGTH_SHORT).show();
                }
                catch (SecurityException e) {
                    Toast.makeText(context, "Call failed!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        final String detailContent = ngoDetailClass.getDescription();

        holder.readmoreTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NGODetailActivity.class);
                intent.putExtra("Ngo Detail",detailContent);
                intent.putExtra("Ngo Name",ngoDetailClass.getName());
                intent.putExtra("website",ngoDetailClass.getWebsite());
                intent.putExtra("phone",ngoDetailClass.getContactNo());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ngoDetailClassList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {


        public TextView nameTV,readmoreTV;
        ImageView schemeImage;
        Button callBtn,locationBtn;

        public MyHolder(View itemView) {
            super(itemView);
            nameTV        = itemView.findViewById(R.id.nameTextView);
            callBtn       = itemView.findViewById(R.id.CallButton);
            readmoreTV    =itemView.findViewById(R.id.readmoreTextView);
            schemeImage   =itemView.findViewById(R.id.circleImageView);
            locationBtn   =itemView.findViewById(R.id.locationBtn);
        }
    }

    public NGODetailList(List<NGODetailClass> ngoDetailClassList , Context context) {
        this.ngoDetailClassList = ngoDetailClassList;
        this.context = context;
    }


}


