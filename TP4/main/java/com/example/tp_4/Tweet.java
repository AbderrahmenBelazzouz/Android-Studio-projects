package com.example.tp_4;


import android.os.Parcel;
import android.os.Parcelable;

public class Tweet implements Parcelable {
    String nom;
    String Tweet;

    public Tweet(String nom, String Tweet){
        this.nom = nom;
        this.Tweet = Tweet;
    }

    public String getNom() {
        return nom;
    }

    public String getTweet() {
        return Tweet;
    }

    @Override
    public int describeContents() {
        return 0;
    }
   @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nom);
        dest.writeString(Tweet);
    }
}