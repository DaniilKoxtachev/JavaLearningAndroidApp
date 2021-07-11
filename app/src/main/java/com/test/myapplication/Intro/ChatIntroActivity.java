package com.test.myapplication.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.test.myapplication.Forum.ForumActivity;
import com.test.myapplication.MainActivity;
import com.test.myapplication.R;

public class ChatIntroActivity extends AppCompatActivity {
    private static int TIME_OUT = 2000;
    LottieAnimationView chat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_intro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(ChatIntroActivity.this, ForumActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },TIME_OUT);
        chat= findViewById(R.id.lottie_chat);
        chat.animate().translationY(-1600).setDuration(1000).setStartDelay(2000);
    }
}