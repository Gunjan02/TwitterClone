package com.example.admin.twitterclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.parse.Parse;
import android.app.Application;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    EditText user,pass;
    Button login,signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("zkWp32VMHYDy4qp8g54dhDzg8MZvks5Hc9JYjVRL")
                .server("https://parseapi.back4app.com/")
                .build()
        );


        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView3);
        user=(EditText)findViewById(R.id.username);
        pass=(EditText)findViewById(R.id.password);

        login=(Button)findViewById(R.id.logBtn);
        signup=(Button)findViewById(R.id.signupBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent home=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(home);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent home=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(home);

            }
        });
    }


}
