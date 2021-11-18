package com.example.tp1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText Taille;
    EditText Masse;
    Button button;
    TextView result;
    ImageView img;
    int[] images={R.drawable.un, R.drawable.deux, R.drawable.trois, R.drawable.quatre, R.drawable.cinq, R.drawable.six, R.drawable.sept};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonclick();
    }
    public void buttonclick(){

        Taille = (EditText) findViewById(R.id.editTextNumberDecimal);
        Masse = (EditText) findViewById(R.id.editTextNumber);
        button = (Button) findViewById(R.id.button);
        result = (TextView) findViewById(R.id.textView);
        img = (ImageView) findViewById(R.id.imageView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double Tl = Double.parseDouble(Taille.getText().toString());
                double M = Double.parseDouble(Masse.getText().toString());
                double resulta = M / (Tl * Tl);


                if (resulta > 40) {
                    result.setText(Double.toString(resulta) + " : 'Obèsité morbide ou massive'");
                    img.setImageResource(images[0]);
                } else if (resulta > 35 && resulta < 40) {
                    result.setText(Double.toString(resulta) + " : 'Obèsité sévère'");
                    img.setImageResource(images[1]);
                } else if (resulta > 30 && resulta < 35) {
                    result.setText(Double.toString(resulta) + " : 'Obèsité modérée'");
                    img.setImageResource(images[2]);
                } else if (resulta > 25 && resulta < 30) {
                    result.setText(Double.toString(resulta) + " : 'Surpoids'");
                    img.setImageResource(images[3]);
                } else if (resulta > 18.5 && resulta < 25) {
                    result.setText(Double.toString(resulta) + " : 'Corpulence normale'");
                    img.setImageResource(images[4]);
                } else if (resulta > 16.5 && resulta < 18.5) {
                    result.setText(Double.toString(resulta) + " : 'Maigreur'");
                    img.setImageResource(R.drawable.six);
                } else if (resulta < 16.5) {
                    result.setText(Double.toString(resulta) + " : 'Famine'");
                    img.setImageResource(R.drawable.sept);
                }

            }
        });
    }
}