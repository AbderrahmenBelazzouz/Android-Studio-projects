package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;
    HorizontalScrollView hzphone;
    HorizontalScrollView hzsum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        img1 = (ImageView)findViewById(R.id.image1) ;
        img2 = (ImageView)findViewById(R.id.image2) ;


        img1.setImageResource(R.drawable.i1);
        img2.setImageResource(R.drawable.s1);



        hzphone = (HorizontalScrollView) findViewById(R.id.hzphone);
        hzsum = (HorizontalScrollView) findViewById(R.id.hzsum);


        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = "100000 DA";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP | Gravity.CENTER, 0, 750);
                toast.show();
            }
        });


        img1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                img1.setVisibility(View.INVISIBLE);
                hzphone.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                hzsum.setVisibility(View.INVISIBLE);
                return true;
            }
        });


        img2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = "90000 DA";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                toast.show();
            }
        });


        img2.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                img1.setVisibility(View.VISIBLE);
                hzphone.setVisibility(View.INVISIBLE);
                img2.setVisibility(View.INVISIBLE);
                hzsum.setVisibility(View.VISIBLE);
                return true;
            }
        });
    }

}