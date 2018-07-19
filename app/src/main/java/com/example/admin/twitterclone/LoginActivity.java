package com.example.admin.twitterclone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.ParseInstallation;
import com.parse.SignUpCallback;
// Front End Dependencies
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.parse.LogInCallback;

public class LoginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    String u,p;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        Parse.initialize(this);

        username = (EditText) findViewById(R.id.uname);
        password = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.lBtn);
        Intent i = getIntent();
        u = i.getStringExtra("USERNAME");
        p = i.getStringExtra("PASSWORD");
    }
    public void click(View view)
    {
        ParseUser.logInInBackground(username.getText().toString(),password.getText().toString(), new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                if (username.getText().toString().equals(u) && password.getText().toString().equals(p)) {
                    alertDisplayer("Sucessful Login","Welcome back " + username.getText().toString().toUpperCase() + " !");

                } else {
                    Toast.makeText(LoginActivity.this, "Wrong credentials", Toast.LENGTH_SHORT).show();

                }
            }
        });




    }

    private void alertDisplayer(String sucessful_login, String s) {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(sucessful_login)
                .setIcon(R.drawable.bird)
                .setMessage(s)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // don't forget to change the line below with the names of your Activities
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
        AlertDialog ok = builder.create();
        ok.show();
    }

}

