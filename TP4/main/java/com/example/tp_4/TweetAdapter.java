package com.example.tp_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TweetAdapter extends ArrayAdapter<Tweet> {
    public TweetAdapter(@NonNull Context context, List<Tweet> tweets) {
        super(context, 0, tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_layout,parent,false);
        }
        TweetHolder tweetHolder = (TweetHolder) convertView.getTag();
        if(tweetHolder == null){
            tweetHolder = new TweetHolder();
            tweetHolder.nom = (TextView) convertView.findViewById(R.id.nom);
            tweetHolder.tweet = (TextView) convertView.findViewById(R.id.tweet);
            convertView.setTag(tweetHolder);
        }
        Tweet tweet = getItem(position);

        tweetHolder.nom.setText(tweet.getNom());
        tweetHolder.tweet.setText(tweet.getTweet());
        return convertView;
    }
}
