package com.example.quizapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class QuesViewModel extends AndroidViewModel {
private QuestionRepository repository;
private LiveData<List<Question>> listLiveData;
    public QuesViewModel(@NonNull Application application) {
        super(application);
    repository=new QuestionRepository(application);
    listLiveData=repository.getAllQuestions();
    }

    public void insert(Question question){
        repository.insert(question);
    }
   public void delete(Question question){
        repository.delete(question);
    }
    public void deleteAllQuestions(){
        repository.deleteAllQustions();
    }
    public LiveData<List<Question>>getAllQuestions(){
        return listLiveData;
    }
    public  LiveData<List<Question>>getQuestionsByCategory(String category){
        listLiveData=repository.getQuestionsByCategory(category);
        return listLiveData;
    }
}
