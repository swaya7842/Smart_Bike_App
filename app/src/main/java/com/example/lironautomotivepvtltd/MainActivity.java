package com.example.lironautomotivepvtltd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lironautomotivepvtltd.UserAbout.AboutCompanyActivity;
import com.example.lironautomotivepvtltd.UserBatteryUages.BatteryActivity;
import com.example.lironautomotivepvtltd.UserFindMyVehicle.FindMyVehicleActivity;
import com.example.lironautomotivepvtltd.UserProfile.MyProfileActivity;
import com.example.lironautomotivepvtltd.UserService.ServiceActivity;
import com.example.lironautomotivepvtltd.UserTips.TipsActivity;
import com.example.lironautomotivepvtltd.UserVehicleDetails.VehicleDetailsActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    private CardView My_Profile, Vehicle_details, Battery_Usages, Sevice_card, FindMyVehicle, Tips, About, Logout;
    SharedPref sharedPref;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        My_Profile = (CardView) findViewById(R.id.my_profile);
        Vehicle_details = (CardView) findViewById(R.id.vehicle_details);
        Battery_Usages = (CardView) findViewById(R.id.battery_usage);
        Sevice_card = (CardView) findViewById(R.id.service);
        FindMyVehicle = (CardView) findViewById(R.id.myvehicle);
        Tips = (CardView) findViewById(R.id.tips);
        About = (CardView) findViewById(R.id.about);
        Logout = (CardView) findViewById(R.id.logout);



//        Log.e(TAG, "telephonyManager: "+mobileNo);
//        String number = getMyPhoneNo();
//        Toast.makeText(getApplicationContext(), "My Phone Number is: "
//                + number, Toast.LENGTH_SHORT).show();

        My_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences dashboardPref = getSharedPreferences("Mobile_No", Context.MODE_PRIVATE);
                String MobileNumber = dashboardPref.getString("Mobile_No", "null");
                Log.e(TAG, "dashboard_MobileNumber: " + MobileNumber);

                Intent intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                startActivity(intent);
            }
        });


        Vehicle_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences dashboardPref = getSharedPreferences("Mobile_No", Context.MODE_PRIVATE);
                String MobileNumber = dashboardPref.getString("Mobile_No", "null");
                Log.e(TAG, "dashboard_MobileNumber: " + MobileNumber);

                Intent intent = new Intent(getApplicationContext(), VehicleDetailsActivity.class);
                startActivity(intent);
            }
        });


        Battery_Usages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences dashboardPref = getSharedPreferences("Mobile_No", Context.MODE_PRIVATE);
                String MobileNumber = dashboardPref.getString("Mobile_No", "null");
                Log.e(TAG, "dashboard_MobileNumber: " + MobileNumber);

                Intent intent = new Intent(getApplicationContext(), BatteryActivity.class);
                startActivity(intent);
            }
        });


        Sevice_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ServiceActivity.class);
                startActivity(intent);
            }
        });


        FindMyVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindMyVehicleActivity.class);
                startActivity(intent);
            }
        });


        Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TipsActivity.class);
                startActivity(intent);
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AboutCompanyActivity.class);
                startActivity(intent);
            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPref.writeLoginStaus(false);
                sharedPref.writeMobile("LoginPojo");
            }
        });


    }


    @Override
    public void onBackPressed() {

//        super.onBackPressed();

        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
//        finish();


    }
}
