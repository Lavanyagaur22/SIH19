package com.codingblocks.sih19;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ColourDragDropActivity extends AppCompatActivity implements View.OnTouchListener,View.OnDragListener {

    private TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13,text14;

    @Override
    public boolean onDrag(View v, DragEvent event) {
        if (event.getAction()==DragEvent.ACTION_DROP)
        {
            TextView dropped = (TextView)event.getLocalState();
            TextView dropTarget = (TextView) v;

            dropped.setVisibility(View.INVISIBLE);
            String options=dropped.getText().toString();
            String text=dropTarget.getText().toString();//target
            Log.e("TAG","Target"+text+" options"+options);

            if(options.equals("Red")&&text.equals("1"))
            {
                Toast.makeText(this, "1 one", Toast.LENGTH_SHORT).show();
                Log.e("TAG","INSIDE 1");
            }
            else if (options.equals("Black")&&text.equals("2")){
                Toast.makeText(this, "2 two ", Toast.LENGTH_SHORT).show();
                Log.e("TAG"," INSIDE 2");
            }
            else if(options.equals("Yellow")&&text.equals("3"))
            {
                Toast.makeText(this, "3 three", Toast.LENGTH_SHORT).show();
                Log.e("TAG","INSIDE 2");
            }
            else if(options.equals("Blue")&&text.equals("Four"))
            {
                Toast.makeText(this, "4 three", Toast.LENGTH_SHORT).show();
                Log.e("TAG","INSIDE 2");
            }
            else if(options.equals("Orange")&&text.equals("5"))
            {
                Toast.makeText(this, "5 three", Toast.LENGTH_SHORT).show();
                Log.e("TAG","INSIDE 2");
            }
            else if(options.equals("Pink")&&text.equals("6"))
            {
                Toast.makeText(this, "6 three", Toast.LENGTH_SHORT).show();
                Log.e("TAG","INSIDE 2");
            }
            else if(options.equals("Green")&&text.equals("7"))
            {
                Toast.makeText(this, "7 three", Toast.LENGTH_SHORT).show();
                Log.e("TAG","INSIDE 2");
            }

            else{
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                Log.e("TAG","else");
            }
            /*if(text.equals(text1.getText().toString())) text1.setVisibility(View.VISIBLE);
            else if(text.equals(text2.getText().toString())) text2.setVisibility(View.VISIBLE);
            else if(text.equals(text3.getText().toString())) text3.setVisibility(View.VISIBLE);
*/
            dropTarget.setText(dropped.getText());
            dropTarget.setBackgroundColor(Color.BLUE);
        }
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction()==MotionEvent.ACTION_DOWN)
        {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(null, shadowBuilder, v, 0);
            return true;
        }
        else return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colour_drag_drop);

        text1 = (TextView)findViewById(R.id.textView1);
        text2 = (TextView)findViewById(R.id.textView2);
        text3 = (TextView)findViewById(R.id.textView3);
        text4 = (TextView)findViewById(R.id.textView4);
        text5 = (TextView)findViewById(R.id.textView5);
        text6 = (TextView)findViewById(R.id.textView6);
        text7 =(TextView) findViewById(R.id.textView7);
        text8 =(TextView) findViewById(R.id.textView8);
        text9 =(TextView) findViewById(R.id.textView9);
        text10 =(TextView) findViewById(R.id.textView10);
        text11 =(TextView) findViewById(R.id.textView11);
        text12 =(TextView) findViewById(R.id.textView12);
        text13 =(TextView) findViewById(R.id.textView13);
        text14 =(TextView) findViewById(R.id.textView14);

        text1.setOnTouchListener(this);
        text2.setOnTouchListener(this);
        text3.setOnTouchListener(this);
        text4.setOnTouchListener(this);
        text5.setOnTouchListener(this);
        text6.setOnTouchListener(this);
        text7.setOnTouchListener(this);
        text8.setOnDragListener(this);
        text9.setOnDragListener(this);
        text10.setOnDragListener(this);
        text11.setOnDragListener(this);
        text12.setOnDragListener(this);
        text13.setOnDragListener(this);
        text14.setOnDragListener(this);
    }

}
