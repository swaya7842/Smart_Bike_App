package com.example.lironautomotivepvtltd;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.lironautomotivepvtltd.Login.LoginActivity;

public class SharedPref {

    private final String TAG = SharedPref.class.getSimpleName();

    private static final String INTRO = "Intro";
   private static final String KEY_MOBILE = "Mobile_No";
   private static final String LOGIN = "Login";
   private static final String KEY_IS_LOGGED_IN  = "isLoggedIn";
   private static final int MOBILENO = 0;

    private Context context;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    public SharedPref(Context context) {

        this.context = context;
        sharedPreferences =context.getSharedPreferences(INTRO,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void writeLoginStaus(boolean status){

        editor.putBoolean(LOGIN,status);
        editor.commit();

    }

    public boolean readLoginStatus(){

        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void writeMobile(String Mobile){

        editor.putString(KEY_MOBILE,Mobile);
        editor.commit();
        Log.e(TAG, "writeMobile: "+KEY_MOBILE );

    }



    public void clearSession() {
        editor.clear();
        editor.commit();
    }
}
