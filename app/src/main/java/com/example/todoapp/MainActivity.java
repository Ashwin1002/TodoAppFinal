package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todoapp.database.Repository;
import com.example.todoapp.model.ETodo;
import com.example.todoapp.model.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private ETodoAdapter adapter;
    private ViewModel viewModel;
    private Repository repository;
    LiveData<List<ETodo>> tasks;
    private FloatingActionButton fabAddNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ETodoAdapter(tasks);

        repository = Repository.getRepository(this.getApplication());
        tasks = repository.getAllTodos();
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);
        viewModel.getAllTodos().observe(this, new Observer<List<ETodo>>() {
            @Override
            public void onChanged(List<ETodo> tasks) {
                if(tasks != null)
                    adapter.setData(tasks);
            }
        });
        adapter = new ETodoAdapter(tasks);
        recyclerView.setAdapter(adapter);

        fabAddNew = findViewById(R.id.fab_add_new_todo);
        fabAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditTodoActivity.class);
                startActivity(intent);
            }
        });
    }
}