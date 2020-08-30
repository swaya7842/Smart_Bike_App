package com.example.lironautomotivepvtltd.AdminMyProfile;

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

import com.example.lironautomotivepvtltd.Admin.AdminActivity;
import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.UtilityValidation;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminMyProfileActivity extends AppCompatActivity {

    private final String TAG = AdminMyProfileActivity.class.getSimpleName();
    EditText Admin_Mobile_No,Admin_Full_Name,Admin_DOB,Admin_Email_Id,Admin_Address,Admin_Organization,
    Admin_Designtion;
    String Str_Admin_Mobile_No,Str_Admin_Full_Name,Str_Admin_DOB,Str_Admin_Email_Id,
            Str_Admin_Address, Str_Admin_Designtion;
    ApiInterface apiInterface;
    Button Update;
    private ImageView PickDOB;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    String MobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_my_profile);



        Admin_Mobile_No = (EditText)findViewById(R.id.admin_mobile_no);
        Admin_Full_Name = (EditText)findViewById(R.id.admin_name);
        Admin_DOB = (EditText)findViewById(R.id.admin_date_of_birth);
        Admin_Email_Id = (EditText)findViewById(R.id.admin_email_id);
        Admin_Address = (EditText)findViewById(R.id.admin_address);
        Admin_Organization = (EditText)findViewById(R.id.admin_organization);
        Admin_Designtion = (EditText)findViewById(R.id.admin_designation);

        SharedPreferences dashboardPref = getSharedPreferences("Mobile_No", Context.MODE_PRIVATE);
        MobileNumber = dashboardPref.getString("Mobile_No","null");
        Log.e(TAG, "MobileNumberAdmin: "+MobileNumber );


        Update = (Button) findViewById(R.id.admin_update_button);
        PickDOB = (ImageView) findViewById(R.id.pickdob);


        PickDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(AdminMyProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        Admin_DOB.setText(mYear+"/"+(mMonth + 1) + "/"+mDay);
                    }
                }, day, month ,year);

                datePickerDialog.show();


            }
        });
        ShowAdminDetails();
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UpdateAdminProfile();
            }
        });
    }

    public void ShowAdminDetails(){

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<AdminProfileFetchPojo>> fetchPojoCall = apiInterface.AdminDetails(MobileNumber);

        fetchPojoCall.enqueue(new Callback<List<AdminProfileFetchPojo>>() {
            @Override
            public void onResponse(Call<List<AdminProfileFetchPojo>> call, Response<List<AdminProfileFetchPojo>> response) {

                List<AdminProfileFetchPojo> adminProfileFetchPojos = response.body();
                Log.e(TAG, "onResponseJson: "+adminProfileFetchPojos );

                String name = adminProfileFetchPojos.get(0).getFull_Name();
                Admin_Full_Name.setText(name);
                Log.e(TAG, "Name: " + Admin_Full_Name);

                String DOB = adminProfileFetchPojos.get(0).getDOB();
                Admin_DOB.setText(DOB);
                Log.e(TAG, "Admin_DOB: " + Admin_DOB);

                String email = adminProfileFetchPojos.get(0).getEmail_Id();
                Admin_Email_Id.setText(email);
                Log.e(TAG, "Email: " + Admin_Email_Id);

                String mobileno = adminProfileFetchPojos.get(0).getMobile_No();
                Admin_Mobile_No.setText(mobileno);
                Log.e(TAG, "MobileNo: " + Admin_Mobile_No);

                String address = adminProfileFetchPojos.get(0).getAddress();
                Admin_Address.setText(address);
                Log.e(TAG, "Model: " + Admin_Address);

                String organization = adminProfileFetchPojos.get(0).getOrganization();
                Admin_Organization.setText(organization);
                Log.e(TAG, "Admin_Organization: " + Admin_Organization);

                String designation = adminProfileFetchPojos.get(0).getDesignation();
                Admin_Designtion.setText(designation);
                Log.e(TAG, "Admin_Designtion: " + Admin_Designtion);

            }

            @Override
            public void onFailure(Call<List<AdminProfileFetchPojo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void UpdateAdminProfile(){

        Boolean Validationok = false;

        Str_Admin_Full_Name = Admin_Full_Name.getText().toString();
        Str_Admin_Email_Id = Admin_Email_Id.getText().toString();
        Str_Admin_Address = Admin_Address.getText().toString();
        Str_Admin_DOB = Admin_DOB.getText().toString();
        Str_Admin_Designtion = Admin_Designtion.getText().toString();

        if (Str_Admin_Full_Name.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_LONG).show();
        } else if ((Str_Admin_Full_Name != null) && (!Str_Admin_Full_Name.equals(""))) {


            if (!UtilityValidation.FullName(Str_Admin_Full_Name)) {

                Log.e(TAG, "User Name: " + Str_Admin_Full_Name);
                Toast.makeText(getApplicationContext(), "User Name is Invalid ", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "User Name: " + Str_Admin_Full_Name);


        }
        if (Str_Admin_Email_Id.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Email Id", Toast.LENGTH_LONG).show();
        } else if ((Str_Admin_Email_Id != null) && (!Str_Admin_Email_Id.equals(""))) {


            if (!UtilityValidation.Email(Str_Admin_Email_Id)) {

                Toast.makeText(getApplicationContext(), "Email Id is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Email: " + Str_Admin_Email_Id);

        }
        if (Str_Admin_Address.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Address", Toast.LENGTH_LONG).show();
        } else if ((Str_Admin_Address != null) && (!Str_Admin_Address.equals(""))) {


            if (!UtilityValidation.Address(Str_Admin_Address)) {
                Toast.makeText(getApplicationContext(), "Address is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Address: " + Str_Admin_Address);

        }
        if (Str_Admin_DOB.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Date of birth", Toast.LENGTH_LONG).show();
        }
        else if ((Str_Admin_DOB != null) && (!Str_Admin_DOB.equals(""))) {


            if (!UtilityValidation.Date(Str_Admin_DOB)){

                Toast.makeText(getApplicationContext(), "Date of birth is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "DOB: " + Str_Admin_DOB);

        }

        if (Str_Admin_Designtion.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Date of birth", Toast.LENGTH_LONG).show();
        }
        else if ((Str_Admin_Designtion != null) && (!Str_Admin_Designtion.equals(""))) {


            if (!UtilityValidation.Designation(Str_Admin_Designtion)){

                Toast.makeText(getApplicationContext(), "Date of birth is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "DOB: " + Str_Admin_Designtion);

        }


        if ((UtilityValidation.FullName(Str_Admin_Full_Name)) &&
                (UtilityValidation.Date(Str_Admin_DOB)) && (UtilityValidation.Email(Str_Admin_Email_Id))
                && (UtilityValidation.Address(Str_Admin_Address)) && (UtilityValidation.Designation(Str_Admin_Designtion))
                ) {

            Log.e(TAG, "Str_Name: " + Str_Admin_Full_Name);
            Log.e(TAG, "Str_DOB: " + Str_Admin_DOB);
            Log.e(TAG, "Str_Email: " + Str_Admin_Email_Id);
            Log.e(TAG, "Str_Address: " + Str_Admin_Address);
            Log.e(TAG, "Str_Model: " + Str_Admin_Designtion);


            Validationok = true;
            if (Validationok == true) {

                apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<AdminProfileUpdatePojo> adminProfileUpdatePojoCall = apiInterface.PerformAdminUpdatation(
                        MobileNumber,Str_Admin_Full_Name,Str_Admin_DOB,Str_Admin_Email_Id,Str_Admin_Address,
                        Str_Admin_Designtion);

                adminProfileUpdatePojoCall.enqueue(new Callback<AdminProfileUpdatePojo>() {
                    @Override
                    public void onResponse(Call<AdminProfileUpdatePojo> call, Response<AdminProfileUpdatePojo> response) {
                        if (response.body().getResponse().equals("Success")) {

                            Toast.makeText(getApplicationContext(), "Information Updated successfully..", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(), AdminActivity.class);
                            startActivity(intent);


                        }  else if (response.body().getResponse().equals("Error")) {
                            Toast.makeText(getApplicationContext(),"Something went wrong.........",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<AdminProfileUpdatePojo> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        }

            }
}
