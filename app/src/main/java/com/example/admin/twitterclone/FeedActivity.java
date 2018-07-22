package com.example.admin.twitterclone;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeedActivity extends ListActivity {


    FeedAdapter arrayAdapter;

    ArrayList<PersonsList> tweetdata;

    ListView userList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);


        userList2 = (ListView) findViewById(R.id.lv2);
        tweetdata = new ArrayList<>();
        arrayAdapter = new FeedAdapter(this,android.R.layout.simple_list_item_1, tweetdata);


        getList();





    }




    private void getList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweets");

        query.whereContainedIn("user",ParseUser.getCurrentUser().getList("isFollowing") ); //assume you have a DonAcc column in your Country table
        query.orderByDescending("createdAt"); //Parse has a built createAt
        query.setLimit(20);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> tweetObjects, ParseException e) {
               if(e == null){
                   if(tweetObjects.size() > 0){
                       for(ParseObject tweetObject : tweetObjects){
                           PersonsList tweet = new PersonsList(tweetObject.getString("user"),tweetObject.getString("newMsg"));

                           tweetdata.add(tweet);

                       }
                       userList2.setAdapter(arrayAdapter);
                   }
               }else {
                   Log.d("error","Error"+e.getMessage());
               }
            }
        });
    }



}
