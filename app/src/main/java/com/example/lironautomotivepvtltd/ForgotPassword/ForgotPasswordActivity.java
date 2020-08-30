package com.example.lironautomotivepvtltd.ForgotPassword;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lironautomotivepvtltd.Login.LoginActivity;
import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.UtilityValidation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {

    private final String TAG = ForgotPasswordActivity.class.getSimpleName();

    EditText New_Password,Re_Password,Mobile_No;
    Button Submit;
    String Str_Password,Str_Mobile_No,Str_Re_Password,Final_Pass;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        New_Password = (EditText)findViewById(R.id.new_password);
//        Re_Password = (EditText)findViewById(R.id.re_enter_password);
        Mobile_No = (EditText)findViewById(R.id.forgot_mobile_no);
        Submit = (Button) findViewById(R.id.SubmitBtn);


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChangePassword();
            }

        });
    }

    public void ChangePassword(){

        Boolean Validationok = false;

        Str_Mobile_No = Mobile_No.getText().toString();
        Str_Password = New_Password.getText().toString();
//        Str_Re_Password = Re_Password.getText().toString();

        if (Str_Mobile_No.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Mobile Number", Toast.LENGTH_LONG).show();
        } else if ((Str_Mobile_No != null) && (!Str_Mobile_No.equals(""))) {


            if (!UtilityValidation.MobileNo(Str_Mobile_No)) {

                Toast.makeText(getApplicationContext(), "Mobile Number is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "MobileNo: " + Str_Mobile_No);
        }

        if (Str_Password.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
        } else if ((Str_Password != null) && (!Str_Password.equals(""))) {


            if (!UtilityValidation.Password(Str_Password)) {

                Toast.makeText(getApplicationContext(), "Password is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Password: " + Str_Password);

        }



        if ((UtilityValidation.Password(Str_Mobile_No)) && (UtilityValidation.Password(Str_Password))) {

            Log.e(TAG, "Str_Mobile_No: " + Str_Mobile_No);

            Log.e(TAG, "Str_Password: " + Str_Password);


                Validationok = true;
                if (Validationok == true) {

                    apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                    Call<ForgotPassPojo> forgotPassPojoCall = apiInterface.PerformForgotPass(Str_Mobile_No,Str_Password);

                    forgotPassPojoCall.enqueue(new Callback<ForgotPassPojo>() {
                        @Override
                        public void onResponse(Call<ForgotPassPojo> call, Response<ForgotPassPojo> response) {

                            if (response.body().getResponse().equals("Success")) {

                                Toast.makeText(getApplicationContext(), "Password Change successfully..", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);

                            } else if (response.body().getResponse().equals("Error")) {
                                Toast.makeText(getApplicationContext(), "Something went wrong.........", Toast.LENGTH_LONG).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<ForgotPassPojo> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });

                }
            }


//        }

    }
}
