package com.example.todoapp.database;

import android.icu.util.EthiopicCalendar;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.model.ETodo;

import java.util.List;


@Dao
public interface TodoDAO {


    @Query("select * from todo_table order  by priority")
    public LiveData<List<ETodo> > getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(ETodo task);

    @Query("delete from todo_table")
    void deleteAll();

    @Delete
    void delete(ETodo task);

    @Update
    void update(ETodo task);

}