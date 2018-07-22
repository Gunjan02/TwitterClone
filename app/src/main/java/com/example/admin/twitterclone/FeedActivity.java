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
import java.util.List;

public class FeedActivity extends ListActivity {


    FeedAdapter arrayAdapter;

    String user;


    Button logout;
    ArrayList<PersonsList> users;

    ListView userList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String user = currentUser.getUsername().toString();

        logout = (Button) findViewById(R.id.logoutBtn);




        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                finish();
            }
        });


        users = new ArrayList<>();
        arrayAdapter = new FeedAdapter(this, R.layout.feed_layout, users);
        userList2 = (ListView) findViewById(R.id.lv2);

        getList();





    }




    private void getList() {
        userList2= (ListView) findViewById(R.id.lv2);

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tweets");

        query.whereEqualTo("user",user ); //assume you have a DonAcc column in your Country table
        query.orderByDescending("createdAt"); //Parse has a built createAt
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> nameList, ParseException e) {
                if (e == null) {
                     users = new ArrayList<>();

                    for (ParseObject object: nameList) {
                        String name = object.getString("user"); //assume you have a name column in your Country table
                        String msg = object.getString("newMsg");
                        users.add(new PersonsList(name,msg));
                    }
                    arrayAdapter = new FeedAdapter(FeedActivity.this,
                            R.layout.feed_layout, users);
                    userList2.setAdapter(arrayAdapter);
                } else {
                    Log.d("error", "Error: " + e.getMessage());
                }
            }
        });
    }



}
