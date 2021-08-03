package com.example.noteapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefrencesHelper {
    private SharedPreferences sharePreferences = null ;
    
    public void  init(Context context){
        sharePreferences = context.getSharedPreferences("settings",Context.MODE_PRIVATE);
            }
    public void onSaveOnBoardState(){
        sharePreferences.edit().putBoolean("isShown",true).apply();
    }
    public boolean isShown(){
        return sharePreferences.getBoolean("isShown",false);
    }

    public  void onSaveImage(){
        sharePreferences.edit().putBoolean("imaged", true).apply();

    }
    public  void OnsaveImageDefault(){
        sharePreferences.edit().putBoolean("imaged", false).apply();

    }
    public  boolean IsshwonImage(){
      return   sharePreferences.getBoolean("imaged", false);

    }
}

