package com.test.myapplication.Intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;
import com.test.myapplication.Compiler.CompilerActivity;
import com.test.myapplication.Forum.ForumActivity;
import com.test.myapplication.R;

public class CompileIntroActivity extends AppCompatActivity {
    private static int TIME_OUT = 2000;
     LottieAnimationView compile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_intro);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(CompileIntroActivity.this, CompilerActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },TIME_OUT);

        compile = findViewById(R.id.compile_lottie);
        compile.animate().translationY(-1600).setDuration(1000).setStartDelay(2000);
    }
}