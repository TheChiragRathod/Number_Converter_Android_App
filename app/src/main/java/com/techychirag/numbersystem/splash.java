package com.techychirag.numbersystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //To Move On Number Convertor Screen...
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                Intent intent =new Intent(splash.this, NumberConvertor.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}