package com.example.admin.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    TextView name;
    Button logout,profile;
    ArrayList<PersonsList> usernames;
    ListAdapter arrayAdapter;
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
        arrayAdapter = new ListAdapter(this, R.layout.userslist, usernames);
         userList = (ListView) findViewById(R.id.lv2);

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
                Intent feed=new Intent(HomeActivity.this,FeedActivity.class);
                startActivity(feed);
                return true;
        }

        return true;
    }

    private void tweetMessage(){

                // don't forget to change the line below with the names of your Activities
                Intent intent = new Intent(HomeActivity.this, TweetActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


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
                            usernames.add(new PersonsList(user.getUsername(),R.drawable.tick1));
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
        ImageButton checkedTextView=(ImageButton) view;
        if(checkedTextView.isEnabled()){
            ParseUser.getCurrentUser().getList("isFollowing").add(usernames.get(i));
            ParseUser.getCurrentUser().saveInBackground();
        }else{
            ParseUser.getCurrentUser().getList("isFollowing").remove(usernames.get(i));
            ParseUser.getCurrentUser().saveInBackground();
        }
    }
}
