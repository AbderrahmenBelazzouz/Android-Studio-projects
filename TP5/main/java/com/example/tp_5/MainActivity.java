package com.example.tp_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    TextView score, lune, lquatre;
    EditText ldeux, ltrois;
    ImageView good, bad;
    char c;
    String motjuste = "";
    BDDManager db = new BDDManager(this);
    int points = 0;
    int i = (int) (Math.random() * 10);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        Collection<String> les_mots = new ArrayList<>();
        String[] lesmots = new String[]{"Mars", "Lait", "Lion", "Fini", "Abbe", "Carl",
                "Done", "Rien", "Lune", "Mere"};
        for (int i = 0; i <lesmots.length; i++) {
            db.addMot(new Mot(lesmots[i]));
        }

        motjuste = nvMot(db);
        score = findViewById(R.id.score);
        lune = findViewById(R.id.lune);
        lquatre = findViewById(R.id.lquatre);
        ldeux = findViewById(R.id.ldeux);
        ltrois = findViewById(R.id.ltrois);
        good = findViewById(R.id.good);
        bad = findViewById(R.id.bad);
        start();


        Button button = findViewById(R.id.verification);
        button.setOnClickListener(new View.OnClickListener() {
            private Object SharedPreferences;

            @Override
            public void onClick(View view) {
                String reponse = motjuste.charAt(0) + ldeux.getText().toString() + ltrois.getText().toString() + motjuste.charAt(3);

                if (reponse.equals(motjuste)) {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {

                            if (points < 10) {
                                points++;
                                nvMot(db);
                                start();
                            }
                            score.setText("points : "+ points + "/10");
                            good.setVisibility(View.VISIBLE);
                        }
                    }, 400);
                    good.setVisibility(View.INVISIBLE);
                    bad.setVisibility(View.INVISIBLE);
                } else {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        public void run() {
                            bad.setVisibility(View.VISIBLE);
                        }
                    }, 400);
                    good.setVisibility(View.INVISIBLE);
                    bad.setVisibility(View.INVISIBLE);
                }
                EditText txte1=(EditText) findViewById(R.id.ldeux);
                EditText txte2=(EditText) findViewById(R.id.ltrois);

                /*SharedPreferences.Editor myedit=sharedref.edit();
                myedit.putString("data",txte1.getText().toString());
                myedit.putString("data",txte2.getText().toString());


                myedit.commit();

                EditText txte11=(EditText) findViewById(R.id.ldeux);
                txte11.setText(sharedref.getString("data","empty"));
                EditText txte22=(EditText) findViewById(R.id.ldeux);
                txte22.setText(sharedref.getString("data","empty"));*/

                //String n  = txte1.getText().toString();
                //String ph  = txte2.getText().toString();
                // String e  = ed3.getText().toString();

                /*SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(String.valueOf(ldeux), n);
                editor.putString(String.valueOf(ltrois), ph);

                editor.commit();*/

                SharedPreferences preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("val1", String.valueOf(txte1));
                editor.apply();
                String valu = getPreferences(MODE_PRIVATE).getString("valu", null);


            }});

    }

 /*   public void buclick(View view) {

        TextView txtv1=(TextView)findViewById(R.id.score);
        TextView txtv2=(TextView)findViewById(R.id.lune);
        TextView txtv3=(TextView)findViewById(R.id.lquatre);
        EditText txte1=(EditText) findViewById(R.id.ldeux);
        EditText txte2=(EditText) findViewById(R.id.ltrois);

        SharedPreferences.Editor myedit=sharedref.edit();
        myedit.putString("data",txte1.getText().toString());
        myedit.putString("data",txte2.getText().toString());


        myedit.commit();

        EditText txte11=(EditText) findViewById(R.id.ldeux);
        txte11.setText(sharedref.getString("data","empty"));
        EditText txte22=(EditText) findViewById(R.id.ldeux);
        txte22.setText(sharedref.getString("data","empty"));

    }*/

    public String nvMot(BDDManager db) {

        ArrayList<Mot> verifmot = new ArrayList();
        verifmot = db.getTousMots();
        i++;

        if (i > verifmot.size()) {
            i = (int) (Math.random() * 10);
        }
        motjuste = verifmot.get(i).mot;
        return motjuste;

    }

    public void start() {
        c = motjuste.charAt(0);
        lune.setText(String.valueOf(c));
        ldeux.setText("");
        ltrois.setText("");
        c = motjuste.charAt(3);
        lquatre.setText(String.valueOf(c));
    }
}