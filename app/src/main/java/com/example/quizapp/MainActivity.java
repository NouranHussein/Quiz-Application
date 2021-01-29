package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
      private QuesViewModel quesViewModel;
        TextView txtcategory,txttimer,txtquestion,txtscore,txtquescount;
        RadioButton rb1,rb2,rb3,rb4;
        RadioGroup rg;
        private long time;
        boolean ans=false;
        Question currques;
        private static int f=0;
        Button btnnext;
        private int quescounter=0,quesTotalCount;
        List<Question>questionList;
        private Handler handler=new Handler();

        int corrans=0,wrongans=0,score=0;
        private WrongDialog wrongDialog;
        private CorrectDialog correctDialog;
    private int totalsizeofquiz=0;
    private int flag=0;
    private AudioAnswers audioAnswers;
private static final long COUNTDOWN=30000;
private CountDownTimer countDownTimer;
private long timeleft;
    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_UI();
        wrongDialog=new WrongDialog(this,this);
        correctDialog=new CorrectDialog(this,this);
        audioAnswers=new AudioAnswers(this);
        quesViewModel=  new ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.getApplication())).get(QuesViewModel.class);
    quesViewModel.getAllQuestions().observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
            fetchContent(questions);
            }
        });
        }

    private void fetchContent(List<Question> questions) {
    questionList=questions;
    startQuiz();
    }
    private void startQuiz(){
    setQuestions();
    rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.radiobutn1:
                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_select));
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    break;
                case R.id.radiobutn2:
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_select));
                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    break;
                case R.id.radiobutn3:
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_select));
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    break;
                case R.id.radiobutn4:
                    rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_select));
                    rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
                    break;


            }
        }
    });
    btnnext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(!ans){
                if(rb1.isChecked() || rb2.isChecked() ||rb3.isChecked()||rb4.isChecked()){
                quizOperation();
                }
                else Toast.makeText(MainActivity.this, "please, select answer", Toast.LENGTH_SHORT).show();

            }
        }
    });
    }

    private void quizOperation() {
    ans=true;
    countDownTimer.cancel();
    RadioButton selectedans=findViewById(rg.getCheckedRadioButtonId());
    int answer=rg.indexOfChild(selectedans)+1;
    switch (currques.getAns()){
        case 1:
        if(currques.getAns()==answer){
            rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_right));
            corrans++;
            score+=10;
            txtscore.setText("Score: "+score);

            correctDialog.correctDialog(score);
            flag=1;
            audioAnswers.setAudio(flag);
        }
        else{
            changeIncorrectColor(selectedans);
            wrongans++;
            final String correctans=(String)rb1.getText();
            wrongDialog.wrongDialog(correctans);
            flag=2;
            audioAnswers.setAudio(flag);
          }

            break;
        case 2:
            if(currques.getAns()==answer){
                rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_right));
                corrans++;
                score+=10;
                txtscore.setText("Score: "+score);
                correctDialog.correctDialog(score);
                flag=1;
                audioAnswers.setAudio(flag);
                }
            else{
                changeIncorrectColor(selectedans);
                wrongans++;
                final String correctans=(String)rb2.getText();
                wrongDialog.wrongDialog(correctans);
                flag=2;
                audioAnswers.setAudio(flag);
                           }

            break;
        case 3:
            if(currques.getAns()==answer){
                rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_right));
                corrans++;
                score+=10;
                txtscore.setText("Score: "+score);
                correctDialog.correctDialog(score);
                flag=1;
                audioAnswers.setAudio(flag);
              }
            else{
                changeIncorrectColor(selectedans);
                wrongans++;
                final String correctans=(String)rb3.getText();
                wrongDialog.wrongDialog(correctans);
                flag=2;
                audioAnswers.setAudio(flag);
            }

            break;
        case 4:
            if(currques.getAns()==answer){
                rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_right));
                corrans++;
                score+=10;
                txtscore.setText("Score: "+score);
                correctDialog.correctDialog(score);
                flag=1;
                audioAnswers.setAudio(flag);
                }
            else{
                changeIncorrectColor(selectedans);
                wrongans++;
                final String correctans=(String)rb4.getText();
                wrongDialog.wrongDialog(correctans);
                flag=2;
                audioAnswers.setAudio(flag);
            }

            break;

    }

    }

    private void changeIncorrectColor(RadioButton selectedans) {
        selectedans.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.when_ans_wrong));
    }

     void setQuestions(){
    rg.clearCheck();
        rb4.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
        rb2.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
        rb3.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
        rb1.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.defaultsoln));
    quesTotalCount=questionList.size();
    if(f==0)
    {Collections.shuffle(questionList);
    f++;
    }
    if(quesTotalCount-1 > quescounter || quesTotalCount==1){
    currques=questionList.get(quescounter);
        txtquestion.setText(currques.getQuestion());
    rb1.setText("A. "+currques.getOptA());
        rb2.setText("B. "+currques.getOptB());
        rb3.setText("C. "+currques.getOptC());
        rb4.setText("D. "+currques.getOptD());
        quescounter++;
        ans=false;
        if(quesTotalCount-1==quescounter)
            btnnext.setText("Finish");
        else btnnext.setText("Next");
        txtcategory.setText(currques.getCategory()+" Question");
        txtquescount.setText(quescounter+"/"+ (quesTotalCount-1));
        timeleft= COUNTDOWN;

            startCountDown();
    }

    else {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(MainActivity.this,ResultActivity.class);
                intent.putExtra("score",score);
                intent.putExtra("total", quesTotalCount-1);
                intent.putExtra("wrong",wrongans);
                intent.putExtra("correct",corrans);
                startActivity(intent);

            }
        },500);

    }
    }

    private void startCountDown() {
        countDownTimer=new CountDownTimer(timeleft,1000) {
            @Override
            public void onTick(long l) {
timeleft=l;
updateTimeTXT();
            }

            @Override
            public void onFinish() {
timeleft=0;
updateTimeTXT();
            }
        }.start();
    }

    private void updateTimeTXT() {
        int min=(int)(timeleft/1000)/60;
        int sec=(int)(timeleft/1000)%60;
        String timeFormat=String.format(Locale.getDefault(),"%02d:%02d",min,sec);
        txttimer.setText(timeFormat);
        if(timeleft<10000)
        {txttimer.setTextColor(Color.RED);
        flag=3;
        audioAnswers.setAudio(flag);
        }
        else {
            txttimer.setTextColor(Color.BLACK);
        }
        if(timeleft==0){
            Toast.makeText(this, "Times Up!", Toast.LENGTH_LONG).show();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent=new Intent(getApplicationContext(),PlayActivity.class);
                    startActivity(intent);
                }
            },2000);

        }


    }



    void init_UI(){
        txtcategory=findViewById(R.id.txtcategory);
        txtscore=findViewById(R.id.txtscore);
            txttimer=findViewById(R.id.txttimer);
            txtquestion=findViewById(R.id.txtquestioncontainer);
            txtquescount=findViewById(R.id.txtTotalQuestion);
            btnnext=findViewById(R.id.nextbtn);
            rg=findViewById(R.id.radiogroup);
            rb1=findViewById(R.id.radiobutn1);
            rb2=findViewById(R.id.radiobutn2);
            rb3=findViewById(R.id.radiobutn3);
            rb4=findViewById(R.id.radiobutn4);

        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null)
            countDownTimer.cancel();
    }
    @Override
    public void onBackPressed() {
        if(time+2000> System.currentTimeMillis()){
            Intent n=new Intent(MainActivity.this,PlayActivity.class);
            startActivity(n);
        }else Toast.makeText(this, "Press again to Exit.", Toast.LENGTH_LONG).show();
        time=System.currentTimeMillis();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(countDownTimer!=null)
            countDownTimer.cancel();
        finish();
    }



}