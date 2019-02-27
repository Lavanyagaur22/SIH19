package com.codingblocks.sih19;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.storage.FirebaseStorage;

import java.util.List;

public class GovtSchemsDetailList extends RecyclerView.Adapter<GovtSchemsDetailList.MyHolder> {

    List<GovtDetailClass> govtDetailClassList;

    private Context context;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.govt_scheme_row, parent, false);

        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

        final GovtDetailClass govtDetailClass = govtDetailClassList.get(position);

      /*  StorageReference storageReference = storage.getReferenceFromUrl("gs://sih19-dd177.appspot.com").child("GovtSchems");

        final long ONE_MEGABYTE = 1024 * 1024;

        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.schemeImage.setImageBitmap(bitmap);
            }
        });*/

        holder.nameTV.setText(govtDetailClass.getName());
        holder.contentTV.setText(govtDetailClass.getContent());
        final String detailContent = govtDetailClass.getDetail();

        holder.readmoreTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(context, GovtDetailActivity.class);
             intent.putExtra(R.string.detail_content+"",detailContent);
             intent.putExtra("Name",govtDetailClass.getName());
             context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return govtDetailClassList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {


        public TextView  nameTV,contentTV,readmoreTV;
         ImageView schemeImage;

        public MyHolder(View itemView) {
            super(itemView);
             nameTV = itemView.findViewById(R.id.nameTextView);
             contentTV = itemView.findViewById(R.id.contentTextView);
             readmoreTV=itemView.findViewById(R.id.readmoreTextView);
             schemeImage=itemView.findViewById(R.id.circleImageView);
        }
    }

    public GovtSchemsDetailList(List<GovtDetailClass> govtDetailClassList , Context context) {
        this.govtDetailClassList = govtDetailClassList;
        this.context = context;
    }


}
