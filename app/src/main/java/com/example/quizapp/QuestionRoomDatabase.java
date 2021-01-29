package com.example.quizapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Question.class},version = 2)
public abstract class QuestionRoomDatabase extends RoomDatabase {
    private static QuestionRoomDatabase INSTANCE;
    public abstract QuestionDao questionDao();
    private static RoomDatabase.Callback rcallback=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDB(INSTANCE).execute();
        }
    };
    public static synchronized QuestionRoomDatabase getINSTANCE(final Context context){
        if(INSTANCE==null)
            INSTANCE= Room.databaseBuilder(context.getApplicationContext(),QuestionRoomDatabase.class,"questions_database")
                    .fallbackToDestructiveMigration().addCallback(rcallback).build();
        return INSTANCE;
    }
    private static class PopulateDB extends AsyncTask<Void,Void,Void>{
    QuestionDao questionDao;
    private PopulateDB(QuestionRoomDatabase database){
        this.questionDao=database.questionDao();
    }
        @Override
        protected Void doInBackground(Void... voids) {
        questionDao.insert(new Question("what is the Smallest Country in the world ?","Vatican","USA","Egypt","Canada",1,"Geographic"));
            questionDao.insert(new Question("What did the Romans call Scotland ?","Caledonia","Baledonia","Hledonia","Kalednia",1,"Historical"));
            questionDao.insert(new Question("What is Japanese cake from ?","Carrot","Rice","Wine","Sugar",2,"General"));
            questionDao.insert(new Question("Which year was the Premier League Founded ?","1975","1955","1987","1992",4,"Sport"));
            questionDao.insert(new Question("Who is the father of Science ?","Albert Einstein","Newton","Galileo","Watson",3,"Scientific"));
            questionDao.insert(new Question("Which language is used in Android ?","CPP","Java","Kotlin","b & c",4,"Technology"));
            questionDao.insert(new Question("Who is currently the president of Brazil ?","Jair","Dilma","Fernando","Itamar",1,"Policy"));
            return null;
        }
    }


}
