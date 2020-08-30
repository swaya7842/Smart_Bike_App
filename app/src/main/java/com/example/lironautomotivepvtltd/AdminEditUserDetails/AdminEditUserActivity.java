package com.example.lironautomotivepvtltd.AdminEditUserDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lironautomotivepvtltd.Admin.AdminActivity;
import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.UserProfile.UserDetailPojo;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.UtilityValidation;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminEditUserActivity extends AppCompatActivity {

    private final String TAG = AdminEditUserActivity.class.getSimpleName();
    EditText MobileNo,User_Model,User_Owner_Id,User_VehicleRegNo,User_VehicleIdNo,
            User_DOP,User_Name;
    String Str_User_Mobile_No,Str_User_OwnerId, Str_User_Model,
            Str_User_DOP, Str_User_VehicleRegNo, Str_User_VehicleIdentificationNo;
    Button Next,Update;
    ApiInterface apiInterface;
    RelativeLayout relativeLayout,relativeLayout2;
    private ImageView PickDOP;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit_user);

        MobileNo = (EditText)findViewById(R.id.mobile_no);
        Next = (Button)findViewById(R.id.user_nextBtn);

        User_Name = (EditText)findViewById(R.id.user_name);
        User_Owner_Id = (EditText)findViewById(R.id.user_owner_id);
        User_Model = (EditText)findViewById(R.id.user_model);
        User_DOP = (EditText)findViewById(R.id.user_date_of_purchase);
        User_VehicleRegNo = (EditText)findViewById(R.id.user_vehicle_registration_number);
        User_VehicleIdNo = (EditText)findViewById(R.id.user_vehicle_Identification_number);
        Update = (Button)findViewById(R.id.user_update_button);

        relativeLayout = (RelativeLayout)findViewById(R.id.relative_user_detail);
        relativeLayout2 = (RelativeLayout)findViewById(R.id.relative_mobile_no);


        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout2.setVisibility(View.INVISIBLE);
                relativeLayout.setVisibility(View.VISIBLE);
                sendMobileNo();
            }
        });


        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateUserDetails();
            }
        });

        PickDOP = (ImageView) findViewById(R.id.pickdop);



        PickDOP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog1 = new DatePickerDialog(AdminEditUserActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        User_DOP.setText(mDay+"/"+(mMonth + 1) + "/"+mYear);
                    }
                },day , month ,year);

                datePickerDialog1.show();
            }
        });


    }

    public void sendMobileNo(){

        Boolean Validationok = false;
        Str_User_Mobile_No = MobileNo.getText().toString();

        if (Str_User_Mobile_No.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Mobile Number", Toast.LENGTH_LONG).show();
        } else if ((Str_User_Mobile_No != null) && (!Str_User_Mobile_No.equals(""))) {


            if (!UtilityValidation.MobileNo(Str_User_Mobile_No)) {

                Toast.makeText(getApplicationContext(), "Mobile Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "MobileNo: " + Str_User_Mobile_No);
        }

        if (UtilityValidation.MobileNo(Str_User_Mobile_No)) {
            Log.e(TAG, "MobileNo: " + Str_User_Mobile_No);
            Validationok = true;
            if (Validationok == true) {

                apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

                Call<List<UserDetailPojo>> uListCall = apiInterface.UserDetails(Str_User_Mobile_No);

                uListCall.enqueue(new Callback<List<UserDetailPojo>>() {
                    @Override
                    public void onResponse(Call<List<UserDetailPojo>> call, Response<List<UserDetailPojo>> response) {

                        List<UserDetailPojo> userDetailPojos = response.body();

                        Log.e(TAG, "onResponseJson: "+userDetailPojos );


                        String name = userDetailPojos.get(0).getFull_Name();
                        User_Name.setText(name);
                        Log.e(TAG, "Name: " + User_Name);

                        String ownerid = userDetailPojos.get(0).getOwner_Id();
                        User_Owner_Id.setText(ownerid);
                        Log.e(TAG, "OwnerId: " +  User_Owner_Id);


                        String model = userDetailPojos.get(0).getModel();
                        User_Model.setText(model);
                        Log.e(TAG, "Model: " +  User_Model);

                        String vehicleregno = userDetailPojos.get(0).getVehicle_Reg_No();
                        User_VehicleRegNo.setText(vehicleregno);
                        Log.e(TAG, "VehicleRegNo: " +  User_VehicleRegNo);

                        String dop = userDetailPojos.get(0).getDOP();
                        User_DOP.setText(dop);
                        Log.e(TAG, "DOP: " +  User_DOP);

                        String vehicleidentification = userDetailPojos.get(0).getVehicle_Identification_No();
                        User_VehicleIdNo.setText(vehicleidentification);
                        Log.e(TAG, "VehicleIdentificationNo: " +  User_VehicleIdNo);



                    }

                    @Override
                    public void onFailure(Call<List<UserDetailPojo>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }
        }
    }

    public void UpdateUserDetails(){
        Boolean Validationok = false;

        Str_User_OwnerId = User_Owner_Id.getText().toString();
        Str_User_Model = User_Model.getText().toString();
        Str_User_DOP = User_DOP.getText().toString();
        Str_User_VehicleRegNo = User_VehicleRegNo.getText().toString();
        Str_User_VehicleIdentificationNo = User_VehicleIdNo.getText().toString();
        Str_User_Mobile_No = MobileNo.getText().toString();

        if (Str_User_OwnerId.isEmpty()) {


            Toast.makeText(getApplicationContext(), "Please enter Owner Id", Toast.LENGTH_LONG).show();
        } else if ((Str_User_OwnerId != null) && (!Str_User_OwnerId.equals(""))) {


            if (!UtilityValidation.OwnerID(Str_User_OwnerId)) {

                Toast.makeText(getApplicationContext(), "Owner Id is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "OwnerId: " + Str_User_OwnerId);

        }

        if (Str_User_Model.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Model", Toast.LENGTH_LONG).show();
        } else if ((Str_User_Model != null) && (!Str_User_Model.equals(""))) {


            if (!UtilityValidation.Model(Str_User_Model)) {

                Toast.makeText(getApplicationContext(), "Bike Model is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Model: " + Str_User_Model);

        }
        if (Str_User_DOP.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Date of Purchase", Toast.LENGTH_LONG).show();
        }
        else if ((Str_User_DOP != null) && (!Str_User_DOP.equals(""))) {

            Log.e(TAG, "DOP822: " + Str_User_DOP);
            if (!UtilityValidation.Date(Str_User_DOP)){
                Log.e(TAG, "DOP22: " + Str_User_DOP);
                Toast.makeText(getApplicationContext(), " Date of Purchase is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "DOP: " + Str_User_DOP);

        }

        if (Str_User_VehicleRegNo.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Vehicle Registration Number", Toast.LENGTH_LONG).show();
        } else if ((Str_User_VehicleRegNo != null) && (!Str_User_VehicleRegNo.equals(""))) {


            if (!UtilityValidation.VehicleRegNo(Str_User_VehicleRegNo)) {

                Toast.makeText(getApplicationContext(), "Vehicle Registration Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "VehicleRegNo: " + Str_User_VehicleRegNo);

        }
        if (Str_User_VehicleIdentificationNo.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Vehicle Identification Number", Toast.LENGTH_LONG).show();
        } else if ((Str_User_VehicleIdentificationNo != null) && (!Str_User_VehicleIdentificationNo.equals(""))) {


            if (!UtilityValidation.VehicleIdNo(Str_User_VehicleIdentificationNo)) {

                Toast.makeText(getApplicationContext(), "Vehicle Identification Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "VehicleIdentificationNo: " + Str_User_VehicleIdentificationNo);


        }

        if ((UtilityValidation.OwnerID(Str_User_OwnerId)) && (UtilityValidation.VehicleRegNo(Str_User_VehicleRegNo))
                && (UtilityValidation.VehicleIdNo(Str_User_VehicleIdentificationNo)) && (UtilityValidation.Model(Str_User_Model)) &&
                (UtilityValidation.Date(Str_User_DOP))
        ) {

            Log.e(TAG, "Str_User_OwnerId: " + Str_User_OwnerId);
            Log.e(TAG, "Str_DOB: " + Str_User_DOP);
            Log.e(TAG, "Str_Email: " + Str_User_VehicleRegNo);
            Log.e(TAG, "Str_Address: " + Str_User_VehicleIdentificationNo);
            Log.e(TAG, "Str_Model: " + Str_User_Model);
            Log.e(TAG, "Str_DOP: " + Str_User_DOP);


            Validationok = true;
            if (Validationok == true){


                apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<EditUserPojo> editUserPojoCall = apiInterface.PerformUserUpdatation(Str_User_Mobile_No,
                        Str_User_OwnerId,Str_User_Model,Str_User_DOP,Str_User_VehicleRegNo,Str_User_VehicleIdentificationNo);

                editUserPojoCall.enqueue(new Callback<EditUserPojo>() {
                    @Override
                    public void onResponse(Call<EditUserPojo> call, Response<EditUserPojo> response) {
                        if (response.body().getResponse().equals("Success")) {

                            Toast.makeText(getApplicationContext(), "Information Updated successfully..", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);


                        }  else if (response.body().getResponse().equals("Error")) {
                            Toast.makeText(getApplicationContext(),"Something went wrong.........",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<EditUserPojo> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
            }

        }


    }
}
