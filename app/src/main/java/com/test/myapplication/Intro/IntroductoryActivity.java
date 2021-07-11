package com.test.myapplication.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.test.myapplication.MainActivity;
import com.test.myapplication.R;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView logo,intro_bg;
    //LottieAnimationView lottieBG;
    private static int TIME_OUT = 700;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(IntroductoryActivity.this,
                        MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },TIME_OUT);
       // lottieBG = findViewById(R.id.lottie_bg);
        logo = findViewById(R.id.logo);
        intro_bg = findViewById(R.id.intro_bg);
        logo.animate().setDuration(700).setStartDelay(700);

       // lottieBG.animate().setDuration(700).setStartDelay(700);


    }
}