package com.example.lironautomotivepvtltd.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lironautomotivepvtltd.Admin.AdminActivity;
import com.example.lironautomotivepvtltd.ForgotPassword.ForgotPasswordActivity;
import com.example.lironautomotivepvtltd.MainActivity;
import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.SharedPref;
import com.example.lironautomotivepvtltd.UserSignUp.SignUpActivity;
import com.example.lironautomotivepvtltd.UtilityValidation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();
    private Button Login;
    private EditText Mobile_No,Password;
    private String Str_Mobile_No, Str_Password;
    private TextView Forgot_Pass;
    private ApiInterface apiInterface;
    SharedPref sharedPref;
    ProgressDialog loading;
    Context mContext;
    SharedPreferences sharedPreferences;
    TextView Register;
    LoginPojo loginPojo;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String MOBILENO = "Mobile_No";

    //    OnLoginFormActivityListener onLoginFormActivityListener;
//    public interface OnLoginFormActivityListener{
//
//        public void performLogin(String name);
//    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mobile_No = (EditText)findViewById(R.id.mobile_no);
        Password = (EditText)findViewById(R.id.login_password);
        Forgot_Pass = (TextView) findViewById(R.id.forgot_password);
        Register = (TextView) findViewById(R.id.register_here);


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        mContext = this;
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        sharedPref = new SharedPref(this);

        Login = (Button)findViewById(R.id.LoginBtn);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loading = ProgressDialog.show(mContext, null, "Loading", true, false);

                loginvalidation();

//                String MobileNumber =Mobile_No.getText().toString();
//                sharedPref.setMobileNumber(MobileNumber);

                String MobileNumber =Mobile_No.getText().toString();
                SharedPreferences sharedPreferences =getSharedPreferences("Mobile_No",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Mobile_No",MobileNumber);
                editor.apply();
//                sharedPref.setMobileNumber(MobileNumber);
                Log.e(TAG, "onClick_MobileNumber: "+MobileNumber );
//                sharedPref.createLogin(MobileNumber);
//                sharedPref.isLoggedIn();
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        Forgot_Pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        if (sharedPref.readLoginStatus()){

            startActivity(new Intent(getApplicationContext(), MainActivity.class)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

          finish();

        }
    }



    private void loginvalidation() {

        Boolean Validationok = false;
        Str_Mobile_No = Mobile_No.getText().toString().trim();
        Str_Password = Password.getText().toString().trim();



        if (Str_Mobile_No.isEmpty()) {

            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Please enter Mobile Number", Toast.LENGTH_LONG).show();
        } else if ((Str_Mobile_No != null) && (!Str_Mobile_No.equals(""))) {

            loading.dismiss();
            if (!UtilityValidation.MobileNo(Str_Mobile_No)) {

                Toast.makeText(getApplicationContext(), "Mobile Number is Invalid", Toast.LENGTH_LONG).show();
            }

            Log.e(TAG, "MobileNo: " + Str_Mobile_No);
        }

        if (Str_Password.isEmpty()) {

            loading.dismiss();
            Toast.makeText(getApplicationContext(), "Please enter Password", Toast.LENGTH_LONG).show();
        } else if ((Str_Password != null) && (!Str_Password.equals(""))) {
            loading.dismiss();

            if (!UtilityValidation.Password(Str_Password)) {

                Toast.makeText(getApplicationContext(), "Password is Invalid", Toast.LENGTH_LONG).show();
            }
            Log.e(TAG, "Password: " + Str_Password);

        }

        if (UtilityValidation.MobileNo(Str_Mobile_No) && UtilityValidation.Password(Str_Password)){

            Validationok = true;
            if (Validationok == true){


                apiInterface.PerformLogin(Str_Mobile_No,Str_Password)
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                                Log.e(TAG, "onResponseResult: "+response.body().toString());
                                if (response.isSuccessful()){

                                    loading.dismiss();
                                    try {
                                      JSONObject jsonObject =new JSONObject(response.body().string());
                                      if (jsonObject.getString("Response").equals("True")){
                                          Toast.makeText(mContext,"Login successfully!",Toast.LENGTH_LONG).show();

                                          if (jsonObject.getString("Role").equals("Admin")){

                                              Intent intent = new Intent(mContext, AdminActivity.class);

                                              startActivity(intent);
                                          }
                                         else  if (jsonObject.get("Role").equals("User")){
                                              Intent intent = new Intent(mContext, MainActivity.class);

                                              startActivity(intent);
                                          }
                                      }
                                    else  if (jsonObject.getString("Response").equals("False") && jsonObject.getString("Message")
                                      .equals("Parameter missing!")){
                                             loading.dismiss();
                                            Toast.makeText(mContext,"Parameter missing! ",Toast.LENGTH_LONG).show();

                                        }
                                    else  if (jsonObject.getString("Response").equals("False") && jsonObject.getString("Message")
                                      .equals("Invalid Mobile No or Password!")){
                                          loading.dismiss();
                                            Toast.makeText(mContext,"Invalid Mobile No or Password!",Toast.LENGTH_LONG).show();

                                        }
                                    else  if (jsonObject.getString("Response").equals("False") && jsonObject.getString("Message")
                                      .equals("Error occured, please try again!")){
                                          loading.dismiss();
                                            Toast.makeText(mContext,"Error occured, please try again!",Toast.LENGTH_LONG).show();

                                        }
                                    }catch (JSONException e){

                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(mContext,t.getMessage(),Toast.LENGTH_LONG).show();


                            }

                        });




            }



        }


    }
}
