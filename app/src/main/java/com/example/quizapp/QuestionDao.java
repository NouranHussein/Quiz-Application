package com.example.quizapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface QuestionDao {

    @Query("SELECT * from questionTable ")
    LiveData<List<Question>>getAllQuestions();
    @Query("SELECT * from questionTable WHERE  questionTable.category = :category")
    LiveData<List<Question>>getQuestionsByCategory( String category);
    @Insert
    void insert(Question questions);
    @Delete
    void delete(Question questions);
    @Query("DELETE FROM questionTable")
    void deleteAllQuestions();

}
