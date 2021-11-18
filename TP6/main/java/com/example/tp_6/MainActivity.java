package com.example.tp_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Environment;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    //ProgressBar bar;
    ConditionVariable mCondition;
    ImageButton imageView5;
    ImageButton imageView6;
    ImageButton imageView7;
    String isFileThere;
    MediaPlayer mediaPlayer;
    String Url;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText uri = (EditText) findViewById(R.id.uri);
        Button downloadBtn = (Button) findViewById(R.id.downloadBtn);
        //MyAsyncTask mat=new MyAsyncTask();
        //mat.execute();
        //new MyAsyncTask(bar).execute((Runnable) uri);

        imageView5 = (ImageButton) findViewById(R.id.playBtn);
        imageView6 = (ImageButton) findViewById(R.id.pauseBtn);
        imageView7 = (ImageButton) findViewById(R.id.stopBtn);

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(Uri.parse(uri.getText().toString()));
            }
        });

    }

    public void play(View v){
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(this,Uri.parse("/storage/emulated/0/Download/"+ isFileThere));
        }
        mediaPlayer.start();
        imageView5.setVisibility(View.INVISIBLE);
        imageView6.setVisibility(View.VISIBLE);
        imageView7.setVisibility(View.VISIBLE);
    }

    public void pause(View v){
        position = mediaPlayer.getCurrentPosition();
        mediaPlayer.pause();

        imageView5.setVisibility(View.VISIBLE);
        imageView6.setVisibility(View.INVISIBLE);
        imageView7.setVisibility(View.VISIBLE);
    }

    public void stop(View v){
        if (mediaPlayer != null){
            mediaPlayer.release(); mediaPlayer =null; }

        imageView5.setVisibility(View.VISIBLE);
        imageView6.setVisibility(View.INVISIBLE);
        imageView7.setVisibility(View.INVISIBLE);
    }

    public class MyAsyncTask extends AsyncTask<Uri,Integer,Integer> {
        WeakReference<ProgressBar> progressBar;
        // Constructor
        /*public MyAsyncTask(ProgressBar bar){
            this.progressBar = new WeakReference<>(bar);}

        public MyAsyncTask() {

        }*/

        /*@Override
        protected Integer doInBackground(Uri... uris) {
            DownloadData(uris[0],1);
            publishProgress(1);
            return 1;
        }*/

        @Override
        protected Integer doInBackground(Uri... uris) {
            DownloadData(uris[0]);
            publishProgress(1);
            return 1;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(),"Download", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            imageView5.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(),s+" Files Downloaded ", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this, "Please Give Permission to Download The File", Toast.LENGTH_SHORT).show();
        }
        else{
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }
    }

    private boolean checkPermission(){
        int result= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(result== PackageManager.PERMISSION_GRANTED){
            return true;
        }
        else{
            return false;
        }
    }

    // (,int i )
    private void DownloadData(Uri url) {
        if(checkPermission()){
            DownloadManager downloadmanager = (DownloadManager)
                    getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(url);
            // String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(url.toString());
            Url = "https://soundflux.islamicfinder.org/if-soundflux/api/v1/stream//quran/rahman-sudais/001.mp3";
            isFileThere = URLUtil.guessFileName(url.toString(), null, MimeTypeMap.getFileExtensionFromUrl(url.toString()));
            request.setTitle("Son");
            request.setDescription("Downloading");
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, isFileThere);

            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

            final long downloadId = downloadmanager.enqueue(request);

            mCondition = new ConditionVariable(false);

            IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
            BroadcastReceiver receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    if (downloadId == reference) {
                        mCondition.open(); }
                }
            };
            getApplicationContext().registerReceiver(receiver, filter);
            mCondition.block();
        }
        else{
            requestPermission();
        }
    }
}
