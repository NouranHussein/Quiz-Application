package com.example.quizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

 class WrongDialog {
    private Context context;
    public Dialog wrongdialog;
    private MainActivity mainActivity;
    WrongDialog(Context context, MainActivity mn) {
        this.context = context;
        mainActivity=mn;
    }
     void wrongDialog(String corretans){
        wrongdialog =new Dialog(context);
        wrongdialog.setContentView(R.layout.wrong_dialog );
        final Button btfinal=(Button) wrongdialog.findViewById(R.id.wrong_btn);
        TextView txt=(TextView)wrongdialog.findViewById(R.id.txtcorrectans);
        txt.setText(corretans);
        btfinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wrongdialog.dismiss();
                mainActivity.setQuestions();

            }
        });
        wrongdialog.show();
        wrongdialog.setCancelable(false);
        wrongdialog.setCanceledOnTouchOutside(false);
         wrongdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    }




}
