package com.example.todoapp.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapp.database.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel{
    private Repository mTodoRepository;
    private LiveData<List<ETodo>> mAllTodos;

    public ViewModel(@NonNull Application application) {
        super(application);
        mTodoRepository = Repository.getRepository(application);
        mAllTodos = mTodoRepository.getAllTodos();
    }

    public LiveData<List<ETodo>> getAllTodos() {
        return mAllTodos;
    }

}
