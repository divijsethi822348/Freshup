package com.example.freshup;

import android.app.Activity;
import android.content.SharedPreferences;

public class Login_Logout {
    public static void SaveToken(Activity activity){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyFile",0);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("Token","1");
        editor.commit();
    }

    public static String GetToken(Activity activity){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyFile",0);
        return sharedPreferences.getString("Token","");
    }

    public static void Logout(Activity activity){
        SharedPreferences sharedPreferences=activity.getSharedPreferences("MyFile",0);
        sharedPreferences.edit().clear().commit();
    }

}
