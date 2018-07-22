package com.example.admin.twitterclone;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class TweetActivity extends AppCompatActivity {
    CardView cv1,cv2;
    EditText tweetMsg;
    Button sendBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tweet);

        cv1=(CardView)findViewById(R.id.cv1);
        cv2=(CardView)findViewById(R.id.cv2);

        tweetMsg=(EditText)findViewById(R.id.msgText);
        sendBtn=(Button)findViewById(R.id.send);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser currentUser=ParseUser.getCurrentUser();
                String currentuserUsername=currentUser.getUsername();
                String message=tweetMsg.getText().toString();
                ParseObject tweetObj = new ParseObject("Tweets");
                tweetObj.put("newMsg",message);
                tweetObj.put("user",currentuserUsername);
                tweetObj.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){

                            Toast.makeText(TweetActivity.this, "Tweet Successful!", Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(TweetActivity.this,FeedActivity.class);
                            startActivity(i);
                        }else{
                            final AlertDialog.Builder alertDialog=new AlertDialog.Builder(TweetActivity.this);
                            alertDialog.setMessage(e.getMessage());
                            alertDialog.setTitle("Sorry");
                            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            AlertDialog dialog=alertDialog.create();
                            dialog.show();

                        }
                    }
                });
            }
        });

    }
}
