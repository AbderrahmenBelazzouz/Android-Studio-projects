package com.example.tp_4;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    TweetAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            tweets = savedInstanceState.getParcelableArrayList("tweets");

        }else{
             tweets.add(new Tweet("Abbe","ok"));
        }
        adapter = new TweetAdapter(MainActivity.this, tweets);
        ListView mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.twitter_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()){
            case R.id.twittericon:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_layout);
                dialog.setTitle("Ajouter un Tweet");
                Button btnValider = (Button) dialog.findViewById(R.id.tweet_btn);
                dialog.show();

                btnValider.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        EditText tweet = (EditText) dialog.findViewById(R.id.qdn);
                        tweets.add( new Tweet("Abbe",tweet.getText().toString()) );
                            adapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                return true;
           default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setTitle("Suppression");
        confirm.setIcon(android.R.drawable.ic_dialog_alert);
        confirm.setMessage("Vous confirmez la suppression ?");

        final int toRemove = position;

        confirm.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int idBtn) {

                        tweets.remove(toRemove); adapter.notifyDataSetChanged();

                    }
                });
        confirm.setNegativeButton(android.R.string.no, null);
        confirm.show();
        return true;
    }
}