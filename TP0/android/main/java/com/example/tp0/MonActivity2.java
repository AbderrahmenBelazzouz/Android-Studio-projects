package com.example.tp0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MonActivity2 extends AppCompatActivity {
    public static final String TAG = "Salem";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon2);
        Log.i(TAG, "Ok !");
        Button b=(Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MonActivity2.this,"OK", Toast.LENGTH_SHORT).show();
            }
        });
    }
}