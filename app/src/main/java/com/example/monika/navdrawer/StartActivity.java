package com.example.monika.navdrawer;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.monika.navdrawer.LoginActivity;
import com.example.monika.navdrawer.R;

public class StartActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        new Handler().postDelayed(new Runnable()
                                  {
                                      @Override
                                      public void run(){
                                          Intent Home =new Intent(StartActivity.this, HomeActivity.class);
                                          startActivity(Home);
                                          finish();
                                      }
                                  },SPLASH_TIME_OUT
        );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start2);
    }
}
