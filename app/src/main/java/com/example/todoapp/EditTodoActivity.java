package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.database.Repository;
import com.example.todoapp.model.ETodo;

import java.util.Date;


public class EditTodoActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText descEditText;
    private Repository repository;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);
        titleEditText = findViewById(R.id.title_et);
        descEditText = findViewById(R.id.desc_dt);
        submitButton = findViewById(R.id.submit_btn);
        repository = Repository.getRepository(this.getApplication());


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String desc = descEditText.getText().toString();
                Date date = new Date();
                ETodo task = new ETodo("Title", "Description", date, 1, false);
                repository.addTask(task);
                Intent intent = new Intent( EditTodoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
