package com.codingblocks.sih19;


import android.annotation.TargetApi;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ColourDailyObject extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ball, bat, chair, pen, pencil, table;
    LinearLayout black, red, orange, yellow, green, blue;
    LinearLayout output;
    boolean isShapeSelected = false;
    boolean isColorSelected = false;
    LinearLayout shapeSelected;
    LinearLayout colorSelected;

    ImageView objectImages;
    TextView outputText;



    MediaPlayer Sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour);

        ball = (LinearLayout) findViewById(R.id.ball);
        bat = (LinearLayout) findViewById(R.id.bat);
        chair = (LinearLayout) findViewById(R.id.chair);
        pen = (LinearLayout) findViewById(R.id.pen);
        pencil = (LinearLayout) findViewById(R.id.pencil);
        table = (LinearLayout) findViewById(R.id.table);
        output = (LinearLayout) findViewById(R.id.output);
        black = (LinearLayout) findViewById(R.id.black);
        red = (LinearLayout) findViewById(R.id.red);
        orange = (LinearLayout) findViewById(R.id.orange);
        yellow = (LinearLayout) findViewById(R.id.yellow);
        green = (LinearLayout) findViewById(R.id.green);
        blue = (LinearLayout) findViewById(R.id.blue);

        objectImages = (ImageView) findViewById(R.id.objectImages);
        outputText = (TextView) findViewById(R.id.outputText);

        ball.setOnClickListener(this);
        bat.setOnClickListener(this);
        chair.setOnClickListener(this);
        pen.setOnClickListener(this);
        pencil.setOnClickListener(this);
        table.setOnClickListener(this);
        black.setOnClickListener(this);
        orange.setOnClickListener(this);
        yellow.setOnClickListener(this);
        green.setOnClickListener(this);
        blue.setOnClickListener(this);
        red.setOnClickListener(this);

        Sound = MediaPlayer.create(this, R.raw.blue);

        /*
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .tagForChildDirectedTreatment(true)
                .build();
        mAdView.loadAd(adRequest);
        */


    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        int id = v.getId();

        if(!isShapeSelected){

            switch (id) {
                case R.id.black:
                    output.setBackgroundColor(getResources().getColor(R.color.black));
                    isColorSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.black);
                    Sound.start();


                    //info
                    objectImages.setVisibility(View.GONE);
                    outputText.setText("Black");
                    outputText.setTextSize(50);
                    outputText.setTextColor(Color.parseColor("#FFFFFF"));

                    colorSelected = black;

                    break;
                case R.id.blue:
                    output.setBackgroundColor(getResources().getColor(R.color.blue));
                    isColorSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.blue);
                    Sound.start();
                    colorSelected = blue;

                    //info
                    objectImages.setVisibility(View.GONE);
                    outputText.setText("Blue");
                    outputText.setTextSize(50);
                    outputText.setTextColor(Color.parseColor("#FFFFFF"));

                    break;
                case R.id.green:
                    output.setBackgroundColor(getResources().getColor(R.color.green));
                    isColorSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.green);
                    Sound.start();
                    colorSelected = green;

                    //info
                    objectImages.setVisibility(View.GONE);
                    outputText.setText("Green");
                    outputText.setTextSize(50);
                    outputText.setTextColor(Color.parseColor("#FFFFFF"));

                    break;
                case R.id.yellow:
                    output.setBackgroundColor(getResources().getColor(R.color.yellow));
                    isColorSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.yellow);
                    Sound.start();
                    colorSelected = yellow;

                    //info
                    objectImages.setVisibility(View.GONE);
                    outputText.setText("Yellow");
                    outputText.setTextSize(50);
                    outputText.setTextColor(Color.parseColor("#FFFFFF"));

                    break;
                case R.id.red:
                    output.setBackgroundColor(getResources().getColor(R.color.red));
                    isColorSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.red);
                    Sound.start();
                    colorSelected = red;

                    //info
                    objectImages.setVisibility(View.GONE);
                    outputText.setText("Red");
                    outputText.setTextSize(50);
                    outputText.setTextColor(Color.parseColor("#FFFFFF"));

                    break;
                case R.id.orange:
                    output.setBackgroundColor(getResources().getColor(R.color.orange));
                    isColorSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.orange);
                    Sound.start();
                    colorSelected = orange;

                    //info
                    objectImages.setVisibility(View.GONE);
                    outputText.setText("Orange");
                    outputText.setTextSize(50);
                    outputText.setTextColor(Color.parseColor("#FFFFFF"));

                    break;
                case R.id.ball:
                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    isShapeSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.ball);
                    Sound.start();
                    shapeSelected = ball;

                    //object info

                    objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                    objectImages.setVisibility(View.VISIBLE);
                    outputText.setTextColor(Color.parseColor("#000000"));
                    outputText.setText("Ball");
                    outputText.setTextSize(30);

                    break;
                case R.id.bat:
                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                    isShapeSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.bat);
                    Sound.start();
                    shapeSelected = bat;

                    objectImages.setVisibility(View.VISIBLE);
                    outputText.setTextColor(Color.parseColor("#000000"));
                    outputText.setText("Bat");
                    outputText.setTextSize(30);

                    break;
                case R.id.chair:
                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                    isShapeSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.chair);
                    Sound.start();
                    shapeSelected = chair;
                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    objectImages.setVisibility(View.VISIBLE);
                    outputText.setTextColor(Color.parseColor("#000000"));
                    outputText.setText("Chair");
                    outputText.setTextSize(30);

                    break;
                case R.id.pen:
                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                    isShapeSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.pen);
                    Sound.start();
                    shapeSelected = pen;

                    objectImages.setVisibility(View.VISIBLE);
                    outputText.setTextColor(Color.parseColor("#000000"));
                    outputText.setText("Pen");
                    outputText.setTextSize(30);

                    break;
                case R.id.pencil:
                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                    isShapeSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.pencil);
                    Sound.start();
                    shapeSelected = pencil;

                    objectImages.setVisibility(View.VISIBLE);
                    outputText.setTextColor(Color.parseColor("#000000"));
                    outputText.setText("Pencil");
                    outputText.setTextSize(30);

                    break;
                case R.id.table:
                    objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                    isShapeSelected=true;
                    Sound.release();
                    Sound = MediaPlayer.create(this, R.raw.table);
                    Sound.start();
                    shapeSelected = table;

                    output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    objectImages.setVisibility(View.VISIBLE);
                    outputText.setTextColor(Color.parseColor("#000000"));
                    outputText.setText("Table");
                    outputText.setTextSize(30);

                    break;

            }

        } else {

            switch (shapeSelected.getId()){

                case R.id.table:
                    switch (id) {
                        case R.id.black:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blacktable));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blacktable);
                            Sound.start();
                            colorSelected = black;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Black Table");
                            outputText.setTextSize(30);

                            break;
                        case R.id.blue:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bluetable));
                            colorSelected = blue;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bluetable);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Blue Table");
                            outputText.setTextSize(30);

                            break;
                        case R.id.green:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.greentable));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.greentable);
                            Sound.start();
                            colorSelected = green;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Green Table");
                            outputText.setTextSize(30);

                            break;
                        case R.id.yellow:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.yellowtable));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.yellowtable);
                            Sound.start();
                            colorSelected = yellow;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Yellow Table");
                            outputText.setTextSize(30);

                            break;
                        case R.id.red:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.redtable));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.redtable);
                            Sound.start();
                            colorSelected = red;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Red Table");
                            outputText.setTextSize(30);
                            break;

                        case R.id.orange:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.orangetable));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.orangetable);
                            Sound.start();
                            colorSelected = orange;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Orange Table");
                            outputText.setTextSize(30);
                            break;

                        case R.id.ball:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.ball);
                            Sound.start();
                            isShapeSelected=true;
                            shapeSelected = ball;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.bat:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bat);
                            Sound.start();
                            shapeSelected = bat;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.chair:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.chair);
                            Sound.start();
                            shapeSelected = chair;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pen:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pen);
                            Sound.start();
                            shapeSelected = pen;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pencil:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pencil);
                            Sound.start();
                            shapeSelected = pencil;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.table:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.table);
                            Sound.start();
                            shapeSelected = table;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Table");
                            outputText.setTextSize(30);

                            break;
                    }
                    break;
                case R.id.ball:
                    switch (id) {
                        case R.id.black:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blackball));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blackball);
                            Sound.start();
                            colorSelected = black;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Black Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.blue:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blueball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blueball);
                            Sound.start();
                            colorSelected = blue;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Blue Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.green:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.greenball));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.greenball);
                            Sound.start();
                            colorSelected = green;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Green Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.yellow:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.yellowball));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.yellowball);
                            Sound.start();
                            colorSelected = yellow;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Yellow Ball");
                            outputText.setTextSize(30);
                            break;
                        case R.id.red:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.redball));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.redball);
                            Sound.start();
                            colorSelected = red;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Red Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.orange:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.orangeball));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.orangeball);
                            Sound.start();
                            colorSelected = orange;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Orange Ball");
                            outputText.setTextSize(30);

                            break;

                        case R.id.ball:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.ball);
                            Sound.start();
                            isShapeSelected=true;
                            shapeSelected = ball;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.bat:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bat);
                            Sound.start();
                            shapeSelected = bat;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.chair:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.chair);
                            Sound.start();
                            shapeSelected = chair;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pen:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pen);
                            Sound.start();
                            shapeSelected = pen;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pencil:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pencil);
                            Sound.start();
                            shapeSelected = pencil;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.table:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.table);
                            Sound.start();
                            shapeSelected = table;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Table");
                            outputText.setTextSize(30);

                            break;
                    }
                    break;

                case R.id.pencil:
                    switch (id) {
                        case R.id.black:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blackpencil));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blackpencil);
                            Sound.start();
                            colorSelected = black;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Black Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.blue:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bluepencil));
                            colorSelected = blue;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bluepencil);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Blue Pencil");
                            outputText.setTextSize(30);
                            break;
                        case R.id.green:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.greenpencil));
                            isColorSelected = true;
                            colorSelected = green;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.greenpencil);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Green Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.yellow:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.yellowpencil));
                            isColorSelected = true;
                            colorSelected = yellow;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.yellowpencil);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Yellow Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.red:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.redpencil));
                            isColorSelected = true;
                            colorSelected = red;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.redpencil);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Red Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.orange:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.orangepencil));
                            isColorSelected = true;
                            colorSelected = orange;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.orangepencil);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Orange Pencil");
                            outputText.setTextSize(30);

                            break;

                        case R.id.ball:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.ball);
                            Sound.start();
                            isShapeSelected=true;
                            shapeSelected = ball;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.bat:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bat);
                            Sound.start();
                            shapeSelected = bat;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.chair:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.chair);
                            Sound.start();
                            shapeSelected = chair;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pen:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pen);
                            Sound.start();
                            shapeSelected = pen;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pencil:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pencil);
                            Sound.start();
                            shapeSelected = pencil;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.table:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.table);
                            Sound.start();
                            shapeSelected = table;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Table");
                            outputText.setTextSize(30);

                            break;
                    }
                    break;
                case R.id.bat:
                    switch (id) {
                        case R.id.black:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blackbat));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blackbat);
                            Sound.start();
                            colorSelected = black;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Black Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.blue:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bluebat));
                            colorSelected = blue;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bluebat);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Blue Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.green:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.greenbat));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.greenbat);
                            Sound.start();
                            colorSelected = green;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Green Bat");
                            outputText.setTextSize(30);
                            break;
                        case R.id.yellow:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.yellowbat));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.yellowbat);
                            Sound.start();
                            colorSelected = yellow;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Yellow Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.red:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.redbat));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.redbat);
                            Sound.start();
                            colorSelected = red;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Red Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.orange:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.orangebat));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.orangebat);
                            Sound.start();
                            colorSelected = orange;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Orange Bat");
                            outputText.setTextSize(30);

                            break;

                        case R.id.ball:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.ball);
                            Sound.start();
                            isShapeSelected=true;
                            shapeSelected = ball;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.bat:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bat);
                            Sound.start();
                            shapeSelected = bat;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.chair:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.chair);
                            Sound.start();
                            shapeSelected = chair;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pen:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pen);
                            Sound.start();
                            shapeSelected = pen;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pencil:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pencil);
                            Sound.start();
                            shapeSelected = pencil;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.table:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.table);
                            Sound.start();
                            shapeSelected = table;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Table");
                            outputText.setTextSize(30);

                            break;
                    }
                    break;

                case R.id.pen:
                    switch (id) {
                        case R.id.black:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blackpen));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blackpen);
                            Sound.start();
                            colorSelected = black;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Black Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.blue:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bluepen));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bluepen);
                            Sound.start();
                            colorSelected = blue;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Blue Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.green:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.greenpen));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.greenpen);
                            Sound.start();
                            colorSelected = green;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Green Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.yellow:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.yellowpen));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.yellowpen);
                            Sound.start();
                            colorSelected = yellow;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Yellow Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.red:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.redpen));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.redpen);
                            Sound.start();
                            colorSelected = red;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Red Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.orange:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.orangepen));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.orangepen);
                            Sound.start();
                            colorSelected = orange;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Orange Pen");
                            outputText.setTextSize(30);

                            break;

                        case R.id.ball:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.ball);
                            Sound.start();
                            isShapeSelected=true;
                            shapeSelected = ball;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.bat:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bat);
                            Sound.start();
                            shapeSelected = bat;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.chair:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.chair);
                            Sound.start();
                            shapeSelected = chair;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pen:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pen);
                            Sound.start();
                            shapeSelected = pen;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pencil:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pencil);
                            Sound.start();
                            shapeSelected = pencil;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.table:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.table);
                            Sound.start();
                            shapeSelected = table;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Table");
                            outputText.setTextSize(30);

                            break;
                    }
                    break;
                case R.id.chair:
                    switch (id) {
                        case R.id.black:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.blackchair));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.blackchair);
                            Sound.start();
                            colorSelected = black;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Black Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.blue:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bluechair));
                            colorSelected = blue;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bluechair);
                            Sound.start();

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Blue Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.green:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.greenchair));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.greenchair);
                            Sound.start();
                            colorSelected = green;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Green Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.yellow:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.yellowchair));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.yellowchair);
                            Sound.start();
                            colorSelected = yellow;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Yellow Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.red:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.redchair));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.redchair);
                            Sound.start();
                            colorSelected = red;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Red Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.orange:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.orangechair));
                            isColorSelected = true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.orangechair);
                            Sound.start();
                            colorSelected = orange;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Orange Chair");
                            outputText.setTextSize(30);

                            break;

                        case R.id.ball:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.ball));
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.ball);
                            Sound.start();
                            isShapeSelected=true;
                            shapeSelected = ball;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Ball");
                            outputText.setTextSize(30);

                            break;
                        case R.id.bat:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.bat));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.bat);
                            Sound.start();
                            shapeSelected = bat;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Bat");
                            outputText.setTextSize(30);

                            break;
                        case R.id.chair:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.chair));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.chair);
                            Sound.start();
                            shapeSelected = chair;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Chair");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pen:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pen));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pen);
                            Sound.start();
                            shapeSelected = pen;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pen");
                            outputText.setTextSize(30);

                            break;
                        case R.id.pencil:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.pencil));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.pencil);
                            Sound.start();
                            shapeSelected = pencil;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Pencil");
                            outputText.setTextSize(30);

                            break;
                        case R.id.table:
                            objectImages.setBackground(getResources().getDrawable(R.drawable.table));
                            isShapeSelected=true;
                            Sound.release();
                            Sound = MediaPlayer.create(this, R.raw.table);
                            Sound.start();
                            shapeSelected = table;

                            output.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            objectImages.setVisibility(View.VISIBLE);
                            outputText.setTextColor(Color.parseColor("#000000"));
                            outputText.setText("Table");
                            outputText.setTextSize(30);

                            break;
                    }
                    break;

            }

        }



    }
}
