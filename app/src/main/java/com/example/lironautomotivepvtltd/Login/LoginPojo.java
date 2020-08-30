package com.example.lironautomotivepvtltd.Login;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

public class LoginPojo {


    private final String TAG = LoginPojo.class.getSimpleName();
   private String Response;
   private String Message;
   private String Role;
    private String Mobile_No;
    private String Password;

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public LoginPojo(String response, String mobile_No, String password, String role) {
        Response = response;
        Role = role;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRole() {
        Log.e(TAG, "getRole: "+Role );
        return Role;

    }

    public void setRole(String role) {
        Role = role;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }


}
