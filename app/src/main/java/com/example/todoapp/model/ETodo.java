package com.example.todoapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.todoapp.database.DateConverter;

import java.util.Date;

@Entity(tableName = "todo_table")
public class ETodo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name = "createdDate")
    @TypeConverters({DateConverter.class})
    private Date createdDate;

    @ColumnInfo(name = "priority")
    private int priority;


    public ETodo(String title, String description, int priority, Date createdDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createdDate = createdDate;
    }

    @Ignore
    public ETodo(int id, String title, String description, int priority, Date createdDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.createdDate = createdDate;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date todo_date) {
        this.createdDate = createdDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


}
