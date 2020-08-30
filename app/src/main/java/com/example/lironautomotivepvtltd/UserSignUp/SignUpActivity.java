package com.example.lironautomotivepvtltd.UserSignUp;

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
import android.widget.Toast;

import com.example.lironautomotivepvtltd.MainActivity;
import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.UtilityValidation;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private final String TAG = SignUpActivity.class.getSimpleName();
    private EditText Name, OwnerId, DOB, Email, MobileNo, Address, Model, DOP, VehicleRegNo,
            VehicleIdentificationNo, Password;

    private String Str_Name, Str_OwnerId, Str_DOB, Str_Email, Str_Address, Str_Model,
            Str_DOP, Str_VehicleRegNo, Str_VehicleIdentificationNo, Str_Password , Str_MobileNo;

    private ImageView PickDOB, PickDOP;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog1;
    private DatePickerDialog datePickerDialog;
    private Button Register;

    private ApiInterface apiInterface;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = (EditText) findViewById(R.id.name);
        OwnerId = (EditText) findViewById(R.id.owner_id);
        DOB = (EditText) findViewById(R.id.date_of_birth);
        Email = (EditText) findViewById(R.id.email_id);
        MobileNo = (EditText) findViewById(R.id.mobile_no);
        Address = (EditText) findViewById(R.id.address);
        Model = (EditText) findViewById(R.id.model);
        DOP = (EditText) findViewById(R.id.date_of_purchase);
        VehicleRegNo = (EditText) findViewById(R.id.vehicle_registration_number);
        VehicleIdentificationNo = (EditText) findViewById(R.id.chassis_no);
        Password = (EditText) findViewById(R.id.password);
        Register = (Button) findViewById(R.id.registerBtn);
        PickDOB = (ImageView) findViewById(R.id.pickdob);
        PickDOP = (ImageView) findViewById(R.id.pickdop);


        PickDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {

                        DOB.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);

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

                datePickerDialog1 = new DatePickerDialog(SignUpActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        DOP.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                    }
                }, day, month, year);

                datePickerDialog1.show();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                formValidation();
//                Log.e(TAG, "onClick: ");
            }
        });

    }



    public void formValidation() {

        Boolean Validationok = false;

        Str_Name = Name.getText().toString();
        Str_OwnerId = OwnerId.getText().toString();
        Str_DOB = DOB.getText().toString();
        Str_Email = Email.getText().toString();
        Str_MobileNo = MobileNo.getText().toString();
        Str_Address = Address.getText().toString();
        Str_Model = Model.getText().toString();
        Str_DOP = DOP.getText().toString();
        Str_VehicleRegNo = VehicleRegNo.getText().toString();
        Str_VehicleIdentificationNo = VehicleIdentificationNo.getText().toString();
        Str_Password = Password.getText().toString();

        if (Str_Name.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Name", Toast.LENGTH_LONG).show();
        } else if ((Str_Name != null) && (!Str_Name.equals(""))) {


            if (!UtilityValidation.FullName(Str_Name)) {

                Log.e(TAG, "User Name: " + Str_Name);
                Toast.makeText(getApplicationContext(), "User Name is Invalid ", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "User Name: " + Str_Name);


        }

        if (Str_OwnerId.isEmpty()) {


            Toast.makeText(getApplicationContext(), "Please enter Owner Id", Toast.LENGTH_LONG).show();
        } else if ((Str_OwnerId != null) && (!Str_OwnerId.equals(""))) {


            if (!UtilityValidation.OwnerID(Str_OwnerId)) {

                Toast.makeText(getApplicationContext(), "Owner Id is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "OwnerId: " + Str_OwnerId);

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
        if (Str_Email.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Email Id", Toast.LENGTH_LONG).show();
        } else if ((Str_Email != null) && (!Str_Email.equals(""))) {


            if (!UtilityValidation.Email(Str_Email)) {

                Toast.makeText(getApplicationContext(), "Email Id is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Email: " + Str_Email);

        }
        if (Str_MobileNo.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Mobile Number", Toast.LENGTH_LONG).show();
        } else if ((Str_MobileNo != null) && (!Str_MobileNo.equals(""))) {


            if (!UtilityValidation.MobileNo(Str_MobileNo)) {

                Toast.makeText(getApplicationContext(), "Mobile Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "MobileNo: " + Str_MobileNo);
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
        if (Str_VehicleIdentificationNo.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Vehicle Identification Number", Toast.LENGTH_LONG).show();
        } else if ((Str_VehicleIdentificationNo != null) && (!Str_VehicleIdentificationNo.equals(""))) {


            if (!UtilityValidation.VehicleIdNo(Str_VehicleIdentificationNo)) {

                Toast.makeText(getApplicationContext(), "Vehicle Identification Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "VehicleIdentificationNo: " + Str_VehicleIdentificationNo);


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
        if (Str_VehicleRegNo.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Vehicle Registration Number", Toast.LENGTH_LONG).show();
        } else if ((Str_VehicleRegNo != null) && (!Str_VehicleRegNo.equals(""))) {


            if (!UtilityValidation.VehicleRegNo(Str_VehicleRegNo)) {

                Toast.makeText(getApplicationContext(), "Vehicle Registration Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "VehicleRegNo: " + Str_VehicleRegNo);

        }

        if (Str_Password.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
        } else if ((Str_Password != null) && (!Str_Password.equals(""))) {


            if (!UtilityValidation.Password(Str_Password)) {

                Toast.makeText(getApplicationContext(), "Password is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Password: " + Str_Password);

        }

        if ((UtilityValidation.FullName(Str_Name)) &&( UtilityValidation.OwnerID(Str_OwnerId)) &&
        (UtilityValidation.Date(Str_DOB)) && (UtilityValidation.Email(Str_Email))
                && (UtilityValidation.MobileNo(Str_MobileNo)) && (UtilityValidation.Address(Str_Address))
                && (UtilityValidation.Model(Str_Model)) && (UtilityValidation.VehicleIdNo(Str_VehicleIdentificationNo))
                && (UtilityValidation.Date(Str_DOP)) && (UtilityValidation.VehicleRegNo(Str_VehicleRegNo))
                && (UtilityValidation.Password(Str_Password))) {

            Log.e(TAG, "Str_Name: " + Str_Name);
            Log.e(TAG, "Str_OwnerId: " + Str_OwnerId);
            Log.e(TAG, "Str_DOB: " + Str_DOB);
            Log.e(TAG, "Str_Email: " + Str_Email);
            Log.e(TAG, "Str_MobileNo: " + Str_MobileNo);
            Log.e(TAG, "Str_Address: " + Str_Address);
            Log.e(TAG, "Str_Model: " + Str_Model);
            Log.e(TAG, "Str_VehicleIdentificationNo: " + Str_VehicleIdentificationNo);
            Log.e(TAG, "Str_DOP: " + Str_DOP);
            Log.e(TAG, "Str_VehicleRegNo: " + Str_VehicleRegNo);
            Log.e(TAG, "Str_Password: " + Str_Password);

            Validationok = true;
            if (Validationok == true) {

                apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<SignUpPojo> signUpCall = apiInterface.PerformRegistration(
                        Str_Name, Str_OwnerId, Str_DOB, Str_Email, Str_MobileNo,
                        Str_Address, Str_Model, Str_VehicleIdentificationNo, Str_DOP, Str_VehicleRegNo, Str_Password);

                signUpCall.enqueue(new Callback<SignUpPojo>() {
                    @Override
                    public void onResponse(Call<SignUpPojo> call, Response<SignUpPojo> response) {
                        if (response.body().getResponse().equals("Success")) {

                            Toast.makeText(getApplicationContext(), "Registration success.. Please do Login now..", Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);


                        } else if (response.body().getResponse().equals("Exist")) {

                            Toast.makeText(getApplicationContext(),"User already exist.........",Toast.LENGTH_LONG).show();
                        } else if (response.body().getResponse().equals("Error")) {
                            Toast.makeText(getApplicationContext(),"Something went wrong.........",Toast.LENGTH_LONG).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<SignUpPojo> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });

                 Name.setText("");
                 OwnerId.setText("");
                 DOB.setText("") ;
                 Email.setText("");
                 MobileNo.setText("");
                 Address.setText("");
                 Model.setText("");
                 VehicleIdentificationNo.setText("");
                 DOP.setText("") ;
                 VehicleRegNo.setText("");
                 Password.setText("");

            }


        }
    }

}
