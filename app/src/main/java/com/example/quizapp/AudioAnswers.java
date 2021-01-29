package com.example.quizapp;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioAnswers {
    private Context context;
    public MediaPlayer mediaPlayer;
private int aud;
    public AudioAnswers(Context context) {
        this.context = context;
    }
    public void setAudio(final int f){
        switch (f){
            case 1:
                int correctaudio=R.raw.correct;
                playMusic(correctaudio);
                break;
            case 2:
                int wrongaudio=R.raw.wrong;
                playMusic(wrongaudio);
                break;
            case 3:
                int timeaudio=R.raw.timer;
                playMusic(timeaudio);
                break;
        }

    }

    private void playMusic(int audio) {
        mediaPlayer=MediaPlayer.create(context,audio);
        aud=audio;
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
    }

}
