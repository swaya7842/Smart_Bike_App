package com.example.lironautomotivepvtltd.UserVehicleDetails;

public class Vehicle {

    String Model;
    String Vehicle_Identification_No;
    String Vehicle_Reg_No;
    String Mobile_No;

    public Vehicle(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String mobile_No) {
        Mobile_No = mobile_No;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getVehicle_Identification_No() {
        return Vehicle_Identification_No;
    }

    public void setVehicle_Identification_No(String vehicle_Identification_No) {
        Vehicle_Identification_No = vehicle_Identification_No;
    }

    public String getVehicle_Reg_No() {
        return Vehicle_Reg_No;
    }

    public void setVehicle_Reg_No(String vehicle_Reg_No) {
        Vehicle_Reg_No = vehicle_Reg_No;
    }

    @Override
    public String toString(){
        return
                "VehicleDetails{" +
                        "Model = '" + Model + '\'' +
                        ",Vehicle_Identification_No = '" + Vehicle_Identification_No + '\'' +
                        ",Vehicle_Reg_No = '" + Vehicle_Reg_No + '\'' +
                        "}";
    }
}
