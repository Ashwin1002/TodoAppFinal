package com.example.todoapp.database;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoapp.model.ETodo;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {ETodo.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class TodoRoomDatabase extends RoomDatabase {

    public  static TodoRoomDatabase INSTANCE;

    public abstract TodoDAO todoDao();

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static RoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodoRoomDatabase.class, "todo.db")
                            .addCallback(CALLBACK)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static final RoomDatabase.Callback CALLBACK = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            TodoDAO dao = INSTANCE.todoDao();

            TodoRoomDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    dao.deleteAll();
                    Date date = new Date();
                    ETodo task = new ETodo("Title", "Description", date, 1, false);
                    dao.insert(task);
                    task = new ETodo("Title1", "Description1", date, 2, false);
                    dao.insert(task);

                }
            });

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}