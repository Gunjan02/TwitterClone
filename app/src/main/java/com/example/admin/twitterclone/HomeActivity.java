package com.example.admin.twitterclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.parse.ParseUser;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rv;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    toolbar=(Toolbar)findViewById(R.id.toolbar);

    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    if(ParseUser.getCurrentUser()!=null){
        Toast.makeText(this, "Hello There!", Toast.LENGTH_SHORT).show();
    }else{
        startActivity(new Intent(this,MainActivity.class));
    }
    }
}
