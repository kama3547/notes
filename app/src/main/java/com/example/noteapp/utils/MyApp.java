package com.example.noteapp.utils;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.noteapp.room.NoteDataBase;

public class MyApp extends Application {
    public static NoteDataBase instance = null;
    public static Context context ;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initSP();
        getInstance();
    }

    private void initSP() {
     PrefrencesHelper prefrencesHelper = new PrefrencesHelper();
     prefrencesHelper.init(this);
    }

    public static NoteDataBase getInstance() {
        if (instance == null){
            instance = Room.databaseBuilder(context,
                     NoteDataBase.class,"note.database ")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
