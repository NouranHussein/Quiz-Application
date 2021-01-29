package com.example.quizapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuestionRepository {
    private QuestionDao questionDao;
    private LiveData<List<Question>>listLiveData;
    public QuestionRepository(Application application){
        QuestionRoomDatabase questionRoomDatabase= QuestionRoomDatabase.getINSTANCE(application);
        questionDao=questionRoomDatabase.questionDao();
        listLiveData=questionDao.getAllQuestions();
    }
   public void insert(Question question){
    new InsertQuestionAsynctask(questionDao).execute(question);
    }

    public void delete(Question question){
    new DeleteQuestionAsynctask(questionDao).execute(question);
    }

    public void deleteAllQustions(){
    new DeleteAllQuestionsAsynctask(questionDao).execute();
    }
    public  LiveData<List<Question>> getAllQuestions(){
        return listLiveData;
    }
    public  LiveData<List<Question>> getQuestionsByCategory(String category){
        listLiveData=questionDao.getQuestionsByCategory(category);
        return listLiveData;
    }

    private static class InsertQuestionAsynctask extends AsyncTask<Question,Void,Void>{
        QuestionDao questionDao;
        private InsertQuestionAsynctask(QuestionDao questionDao){
            this.questionDao=questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.insert(questions[0]);
            return null;
        }
    }
    private static class DeleteQuestionAsynctask extends AsyncTask<Question,Void,Void>{
        QuestionDao questionDao;
        private DeleteQuestionAsynctask(QuestionDao questionDao){
            this.questionDao=questionDao;
        }

        @Override
        protected Void doInBackground(Question... questions) {
            questionDao.delete(questions[0]);
            return null;
        }
    }

    private static class DeleteAllQuestionsAsynctask extends AsyncTask<Void,Void,Void>{
        QuestionDao questionDao;
        private DeleteAllQuestionsAsynctask(QuestionDao questionDao){
            this.questionDao=questionDao;
        }

        @Override
        protected Void doInBackground(Void...Voids) {
            questionDao.deleteAllQuestions();
            return null;
        }
    }
}
