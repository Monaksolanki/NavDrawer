package com.example.monika.navdrawer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView Register,Login;

        Register= (TextView) findViewById(R.id.register);
        Login= (TextView) findViewById(R.id.login);

        Register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //  attemptLogin();
                Intent i = new Intent(HomeActivity.this, RegisterActivity.class);
                startActivity(i);
        }});
        Login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                //  attemptLogin();
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                startActivity(i);
            }});


        }
    }

