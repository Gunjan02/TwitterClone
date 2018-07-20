package com.example.admin.twitterclone;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import com.parse.Parse;

import com.parse.ParseObject;

import com.parse.ParseInstallation;
import com.parse.SignUpCallback;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    TextView name;
    Button logout,profile;
    ArrayList<PersonsList> usernames;
    ArrayAdapter arrayAdapter;
    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ParseUser currentUser = ParseUser.getCurrentUser();
        String user = currentUser.getUsername().toString();
        name = (TextView) findViewById(R.id.nameTExt);
        logout = (Button) findViewById(R.id.logoutBtn);


        name.setText("You are logged in as " + user);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logOut();
                finish();
            }
        });


        usernames = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<PersonsList>(this, android.R.layout.simple_list_item_1, usernames);
         userList = (ListView) findViewById(R.id.listview);

        if(ParseUser.getCurrentUser().get("isFollowing")==null){
            List<String> emptyList=new ArrayList<>();
            ParseUser.getCurrentUser().put("isFollowing",emptyList);
            getList();
            userList.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
            userList.setAdapter(arrayAdapter);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menubar,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tweetButton :
                tweetMessage();
                return true;

            case R.id.feedButton :
                Toast.makeText(this, "Your Feed is ready", Toast.LENGTH_SHORT).show();
                return true;
        }

        return true;
    }

    private void tweetMessage(){
        Toast.makeText(this, "Tweet Here!!!", Toast.LENGTH_SHORT).show();
    }
    private void getList() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null) {
                    usernames.clear();
                    if (objects.size() > 0) {

                        for (ParseUser user : objects) {
                            usernames.add(new PersonsList(user.getUsername(),R.drawable.notfollowing));
                        }

                        arrayAdapter.notifyDataSetChanged();
                        for(PersonsList users:usernames){
                            if(ParseUser.getCurrentUser().getList("isFollowing").contains(users)){
                                userList.setItemChecked(usernames.indexOf(users),true);
                            }
                        }

                    }else {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CheckedTextView checkedTextView=(CheckedTextView)view;
        if(checkedTextView.isChecked()){
            ParseUser.getCurrentUser().getList("isFollowing").add(usernames.get(i));
            ParseUser.getCurrentUser().saveInBackground();
        }else{
            ParseUser.getCurrentUser().getList("isFollowing").remove(usernames.get(i));
            ParseUser.getCurrentUser().saveInBackground();
        }
    }
}
