package com.example.lironautomotivepvtltd.UserProfile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lironautomotivepvtltd.MainActivity;
import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.UtilityValidation;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {

    private final String TAG = MyProfileActivity.class.getSimpleName();
    private EditText Name, OwnerId, DOB, Email, MobileNo, Address, Model, DOP, VehicleRegNo,
            VehicleIdentificationNo;
    private String Str_Name, Str_OwnerId, Str_DOB, Str_Email, Str_Address, Str_Model,
            Str_DOP, Str_VehicleRegNo, Str_VehicleIdentificationNo, Str_Password , Str_MobileNo;

    private ImageView PickDOB,PickDOP;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog;
    String MobileNumber;
    Button Update;
    private ApiInterface apiInterface;
    UserDetailPojo userDetailP;


    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

//        dialogbox();
        Name = (EditText)findViewById(R.id.name);
        OwnerId = (EditText)findViewById(R.id.owner_id);
        DOB = (EditText)findViewById(R.id.date_of_birth);
        Email = (EditText)findViewById(R.id.email_id);
        MobileNo = (EditText)findViewById(R.id.mobile_no);
        Address = (EditText)findViewById(R.id.address);
        Model = (EditText)findViewById(R.id.model);
        DOP = (EditText)findViewById(R.id.date_of_purchase);
        VehicleRegNo = (EditText)findViewById(R.id.vehicle_registration_number);
        VehicleIdentificationNo = (EditText)findViewById(R.id.vehicle_Identification_number);

        Update = (Button)findViewById(R.id.update_button);

        SharedPreferences dashboardPref = getSharedPreferences("Mobile_No", Context.MODE_PRIVATE);
        MobileNumber = dashboardPref.getString("Mobile_No","null");
        Log.e(TAG, "vehicle_MobileNumber: "+MobileNumber );

        showUserDetails();

        PickDOB = (ImageView) findViewById(R.id.pickdob);
        PickDOP = (ImageView) findViewById(R.id.pickdop);

        PickDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(MyProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        DOB.setText(mYear+"/"+(mMonth + 1) + "/"+mDay);
                    }
                }, day, month ,year);

                datePickerDialog.show();


            }
        });

        PickDOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog1 = new DatePickerDialog(MyProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        DOP.setText(mDay+"/"+(mMonth + 1) + "/"+mYear);
                    }
                },day , month ,year);

                datePickerDialog1.show();
            }
        });



        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateForm();

            }
        });
    }

    private void showUserDetails() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<UserDetailPojo>> uListCall = apiInterface.UserDetails(MobileNumber);

        uListCall.enqueue(new Callback<List<UserDetailPojo>>() {
            @Override
            public void onResponse(Call<List<UserDetailPojo>> call, Response<List<UserDetailPojo>> response) {

                           List<UserDetailPojo> userDetailPojos = response.body();
                           Log.e(TAG, "onResponseJson: "+userDetailPojos );

                           String name = userDetailPojos.get(0).getFull_Name();
                           Name.setText(name);
                           Log.e(TAG, "Name: " + Name);

                           String ownerid = userDetailPojos.get(0).getOwner_Id();
                           OwnerId.setText(ownerid);
                           Log.e(TAG, "OwnerId: " + OwnerId);

                           String dob = userDetailPojos.get(0).getDOB();
                           DOB.setText(dob);
                           Log.e(TAG, "DOB: " + DOB);

                           String email = userDetailPojos.get(0).getEmail_Id();
                           Email.setText(email);
                           Log.e(TAG, "Email: " + Email);

                           String mobileno = userDetailPojos.get(0).getMobile_No();
                           MobileNo.setText(mobileno);
                           Log.e(TAG, "MobileNo: " + MobileNo);

                           String model = userDetailPojos.get(0).getModel();
                           Model.setText(model);
                           Log.e(TAG, "Model: " + Model);

                           String address = userDetailPojos.get(0).getAddress();
                           Address.setText(address);
                           Log.e(TAG, "Address: " + Address);

                           String vehicleregno = userDetailPojos.get(0).getVehicle_Reg_No();
                           VehicleRegNo.setText(vehicleregno);
                           Log.e(TAG, "VehicleRegNo: " + VehicleRegNo);

                           String dop = userDetailPojos.get(0).getDOP();
                           DOP.setText(dop);
                           Log.e(TAG, "DOP: " + DOP);

                           String vehicleidentification = userDetailPojos.get(0).getVehicle_Identification_No();
                           VehicleIdentificationNo.setText(vehicleidentification);
                           Log.e(TAG, "VehicleIdentificationNo: " + VehicleIdentificationNo);



            }

            @Override
            public void onFailure(Call<List<UserDetailPojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

/*
public void dialogbox(){

//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.setTitle(R.string.dialog_title);
//        dialog.show();
//


    //Dialog
    AlertDialog.Builder builder = new AlertDialog.Builder(context);
    builder.setCancelable(false);
    builder.setTitle("Be Aware");
    builder.setMessage("You don't have permission to edit Mobile Number, Owner ID, Vehicle Registration Number and Vehicle Identification Number");


    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });

    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });

   final AlertDialog alertDialog = builder.create();
    alertDialog.show();
}*/
    private void UpdateForm() {
        Boolean Validationok = false;

        Str_Name = Name.getText().toString();
        Str_Email = Email.getText().toString();
        Str_Address = Address.getText().toString();
        Str_DOB = DOB.getText().toString();
        Str_Model = Model.getText().toString();
        Str_DOP = DOP.getText().toString();
        Str_VehicleRegNo = VehicleRegNo.getText().toString();

        if (Str_Name.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_LONG).show();
        } else if ((Str_Name != null) && (!Str_Name.equals(""))) {


            if (!UtilityValidation.FullName(Str_Name)) {

                Log.e(TAG, "User Name: " + Str_Name);
                Toast.makeText(getApplicationContext(), "User Name is Invalid ", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "User Name: " + Str_Name);


        }
        if (Str_Email.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Email Id", Toast.LENGTH_LONG).show();
        } else if ((Str_Email != null) && (!Str_Email.equals(""))) {


            if (!UtilityValidation.Email(Str_Email)) {

                Toast.makeText(getApplicationContext(), "Email Id is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Email: " + Str_Email);

        }
        if (Str_Address.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_LONG).show();
        } else if ((Str_Address != null) && (!Str_Address.equals(""))) {


            if (!UtilityValidation.Address(Str_Address)) {
                Toast.makeText(getApplicationContext(), "Address is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Address: " + Str_Address);

        }
        if (Str_Model.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Model", Toast.LENGTH_LONG).show();
        } else if ((Str_Model != null) && (!Str_Model.equals(""))) {


            if (!UtilityValidation.Model(Str_Model)) {

                Toast.makeText(getApplicationContext(), "Bike Model is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Model: " + Str_Model);

        }
        if (Str_DOP.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Date of Purchase", Toast.LENGTH_LONG).show();
        }
        else if ((Str_DOP != null) && (!Str_DOP.equals(""))) {

            Log.e(TAG, "DOP822: " + Str_DOP);
            if (!UtilityValidation.Date(Str_DOP)){
                Log.e(TAG, "DOP22: " + Str_DOP);
                Toast.makeText(getApplicationContext(), " Date of Purchase is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "DOP: " + Str_DOP);

        }


        if (Str_DOB.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Date of birth", Toast.LENGTH_LONG).show();
        }
        else if ((Str_DOB != null) && (!Str_DOB.equals(""))) {


            if (!UtilityValidation.Date(Str_DOB)){

                Toast.makeText(getApplicationContext(), "Date of birth is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "DOB: " + Str_DOB);

        }

        if ((UtilityValidation.FullName(Str_Name)) &&
                (UtilityValidation.Date(Str_DOB)) && (UtilityValidation.Email(Str_Email))
                && (UtilityValidation.Address(Str_Address)) && (UtilityValidation.Model(Str_Model)) &&
                (UtilityValidation.Date(Str_DOP))
               ) {

            Log.e(TAG, "Str_Name: " + Str_Name);
            Log.e(TAG, "Str_DOB: " + Str_DOB);
            Log.e(TAG, "Str_Email: " + Str_Email);
            Log.e(TAG, "Str_Address: " + Str_Address);
            Log.e(TAG, "Str_Model: " + Str_Model);
            Log.e(TAG, "Str_DOP: " + Str_DOP);


            Validationok = true;
            if (Validationok == true){

                apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                Call<ProfilePojo> profilePojoCall = apiInterface.PerformUpdatation(MobileNumber,
                        Str_Name,Str_DOB,Str_Email,Str_Address,Str_Model,Str_DOP);


                profilePojoCall.enqueue(new Callback<ProfilePojo>() {
                    @Override
                    public void onResponse(Call<ProfilePojo> call, Response<ProfilePojo> response) {
                        if (response.body().getResponse().equals("Success")) {

                            Toast.makeText(getApplicationContext(), "Information Updated successfully..", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);


                        }  else if (response.body().getResponse().equals("Error")) {
                            Toast.makeText(getApplicationContext(),"Something went wrong.........",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ProfilePojo> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }

        }

        }

}