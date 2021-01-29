package com.example.quizapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PlayActivity extends AppCompatActivity {
Button btn_ply;
private long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        btn_ply=findViewById(R.id.playact_btn_play);
        btn_ply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(PlayActivity.this,MainActivity.class);
                startActivity(in);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(time+2000> System.currentTimeMillis()){
            new AlertDialog.Builder(this).setTitle("Do you want to Exit ?").setNegativeButton("No",null)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setResult(RESULT_OK,new Intent().putExtra("Exit",true));
                            finish();
                        }
                    }).create().show();
        }else Toast.makeText(this, "Press again to Exit.", Toast.LENGTH_LONG).show();
        time=System.currentTimeMillis();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}