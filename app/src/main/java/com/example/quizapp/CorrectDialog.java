package com.example.quizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CorrectDialog {
    private Context context;
    public Dialog correctdialog;
    private MainActivity mainActivity;
    public CorrectDialog(Context context,MainActivity mn) {
        this.context = context;mainActivity=mn;
    }
    public void correctDialog(int score){
        correctdialog =new Dialog(context);
        correctdialog.setContentView(R.layout.correct_dialog );
        final Button btfinal=(Button) correctdialog.findViewById(R.id.correct_btn);
        Score(score);
        btfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correctdialog.dismiss();
mainActivity.setQuestions();
            }
        });
        correctdialog.show();
        correctdialog.setCancelable(false);
        correctdialog.setCanceledOnTouchOutside(false);

        correctdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    private void Score(int score) {
 TextView txt=(TextView)correctdialog.findViewById(R.id.txtscore_correct);
 txt.setText("Your Score: "+score );
        }
}
