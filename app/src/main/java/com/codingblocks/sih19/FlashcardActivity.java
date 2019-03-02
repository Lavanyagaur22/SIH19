package com.codingblocks.sih19;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FlashcardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_item_flashcard);
        final ImageView imgFront=findViewById(R.id.imgFront);
        imgFront.setImageResource(R.drawable.a);
        ImageView imgBack=findViewById(R.id.imgBack);
        imgBack.setImageResource(R.drawable.a_img);

        ImageButton nextFlashcard=findViewById(R.id.btnNextFlashcard);
        nextFlashcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   imgFront.setImageResour
            }
        });

    }

    public static Drawable getImage(Context context, String name) {
        return context.getResources().getDrawable(context.getResources().getIdentifier(name, "drawable", context.getPackageName()));
    }

}
