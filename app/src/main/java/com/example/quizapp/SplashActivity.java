package com.example.quizapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity {
TextView splashtxt;
ImageView imglogo;
private static final int EXIT=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imglogo=findViewById(R.id.imglogo);
        splashtxt=findViewById(R.id.txtsplash);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.transiton);
        imglogo.setAnimation(animation);
        splashtxt.setAnimation(animation);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    goActivity();
                }

            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==EXIT){
            if(resultCode==RESULT_OK)
            {
                if(data.getBooleanExtra("Exit",true)){
                    finish();
                }
            }
        }
    }

    private void goActivity() {
        startActivityForResult(new Intent(SplashActivity.this,PlayActivity.class),EXIT);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}