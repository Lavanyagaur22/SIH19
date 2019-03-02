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

public class DranDropGame extends AppCompatActivity implements View.OnTouchListener,View.OnDragListener {

        private TextView text1,text2,text3,text4,text5,text6;

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

                if(options.equals("1")&&text.equals("One"))
                {
                    text4.setOnDragListener(this);
                    Toast.makeText(this, "1 one", Toast.LENGTH_SHORT).show();
                    Log.e("TAG","INSIDE 1");
                }
                else if (options.equals("2")&&text.equals("Two")){
                    text5.setOnDragListener(this);
                    Toast.makeText(this, "2 two ", Toast.LENGTH_SHORT).show();

                    Log.e("TAG"," INSIDE 2");
                }
                else if(options.equals("3")&&text.equals("Three"))
                {
                    text6.setOnDragListener(this);
                    Toast.makeText(this, "3 three", Toast.LENGTH_SHORT).show();
                    Log.e("TAG","INSIDE 2");
                }
                else{
                    Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
                    Log.e("TAG","else");
                }
               if(text.equals(text1.getText().toString())) text1.setVisibility(View.VISIBLE);
                else if(text.equals(text2.getText().toString())) text2.setVisibility(View.VISIBLE);
                else if(text.equals(text3.getText().toString())) text3.setVisibility(View.VISIBLE);

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
            setContentView(R.layout.activity_drag_drop);

            text1 = (TextView)findViewById(R.id.text1);
            text2 = (TextView)findViewById(R.id.text2);
            text3 = (TextView)findViewById(R.id.text3);
            text4 = (TextView)findViewById(R.id.text4);
            text5 = (TextView)findViewById(R.id.text5);
            text6 = (TextView)findViewById(R.id.text6);

            text1.setOnTouchListener(this);
            text2.setOnTouchListener(this);
            text3.setOnTouchListener(this);
            text4.setOnDragListener(this);
            text5.setOnDragListener(this);
            text6.setOnDragListener(this);
        }

    }
