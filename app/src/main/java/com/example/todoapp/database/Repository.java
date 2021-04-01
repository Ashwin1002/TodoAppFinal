package com.example.todoapp.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoapp.model.ETodo;

import java.util.List;


public class Repository {
    private static Repository INSTANCE;
    public TodoRoomDatabase database;
    public TodoDAO todoDAO;
    public  LiveData<List<ETodo>> allTasks;


    public Repository(Application application) {

        TodoRoomDatabase database = (TodoRoomDatabase) TodoRoomDatabase.getDatabase(application);
        todoDAO = database.todoDao();

    }

    public static Repository getRepository(Application application){
        if(INSTANCE == null){
            INSTANCE = new Repository(application);
        }
        return INSTANCE;
    }
    public LiveData<List<ETodo>> getAllTodos(){
        return todoDAO.getAllTasks();
    }


    public void  deleteAll(){
        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDAO.deleteAll();
            }
        });

    }
    public void addTask(ETodo task){


        TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                todoDAO.insert(task);
            }
        });
    }

}

