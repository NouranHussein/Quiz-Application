package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
TextView txtscore,txtcorrect,txtwrong,txttotalcount;
Button btn_playagain,btn_mainmenu;
int highscore=0;
    private long time;
public static final String SHARED="shared";
public static final String SHARED_score="shared_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        txtscore=findViewById(R.id.res_txt_high_score);
        txtcorrect=findViewById(R.id.res_txt_correct);
        txtwrong=findViewById(R.id.res_txt_wrong);
        txttotalcount=findViewById(R.id.res_txt_total_ques);
        btn_mainmenu=findViewById(R.id.res_btn_main);
        btn_playagain=findViewById(R.id.res_btn_ply);
        btn_playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent=new Intent(ResultActivity.this, MainActivity.class);
                startActivity(itent);

            }
        });
        btn_mainmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itent=new Intent(ResultActivity.this, PlayActivity.class);
                startActivity(itent);

            }
        });

        loadScore();
        Intent in=getIntent();
        int score=in.getIntExtra("score",0);
        int totalcount=in.getIntExtra("total",0);
        int correct=in.getIntExtra("correct",0);
        int wrong=in.getIntExtra("wrong",0);
        txttotalcount.setText("Total Questions: "+totalcount);
        txtcorrect.setText("Correct: "+correct);
        txtwrong.setText("Wrong: "+wrong);
        if(score>highscore)
            updateScore(score);
    }

    private void updateScore(int score) {
        highscore=score;
        txtscore.setText("High Score:"+ highscore);
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt(SHARED_score,highscore);
        editor.apply();
    }

    private void loadScore() {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED,MODE_PRIVATE);
        highscore=sharedPreferences.getInt(SHARED_score,0);
        txtscore.setText("High Score:"+ highscore);

    }
    @Override
    public void onBackPressed() {
        if(time+2000> System.currentTimeMillis()){
           Intent n=new Intent(ResultActivity.this,PlayActivity.class);
           startActivity(n);
        }else Toast.makeText(this, "Press again to Exit.", Toast.LENGTH_LONG).show();
        time=System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}