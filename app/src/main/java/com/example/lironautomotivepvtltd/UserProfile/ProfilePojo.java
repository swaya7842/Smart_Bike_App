package com.example.lironautomotivepvtltd.UserProfile;

import com.google.gson.annotations.SerializedName;

public class ProfilePojo {

    @SerializedName("Response")
    private String Response;


    public String getResponse() {
        return Response;
    }

    public ProfilePojo(String response) {
        Response = response;
    }

    public void setResponse(String response) {
        Response = response;
    }


    String Full_Name;
    String DOB;
    String Email_Id;
    String Mobile_No;
    String Address;
    String Model;
    String DOP;
    String Vehicle_Reg_No;

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }


    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getEmail_Id() {
        return Email_Id;
    }

    public void setEmail_Id(String email_Id) {
        Email_Id = email_Id;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }


    public String getDOP() {
        return DOP;
    }

    public void setDOP(String DOP) {
        this.DOP = DOP;
    }

    public String getVehicle_Reg_No() {
        return Vehicle_Reg_No;
    }

    public void setVehicle_Reg_No(String vehicle_Reg_No) {
        Vehicle_Reg_No = vehicle_Reg_No;
    }

}
