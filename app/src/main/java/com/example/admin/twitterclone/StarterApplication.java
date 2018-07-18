package com.example.admin.twitterclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;

public class StarterApplication extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.enableLocalDatastore(this);

        // Add Your Initialization code here.
        Parse.initialize(this);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        //optionally enable public read access
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL,true);

    }
}
