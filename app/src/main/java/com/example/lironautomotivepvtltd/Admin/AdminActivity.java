package com.example.lironautomotivepvtltd.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lironautomotivepvtltd.AdminEditUserDetails.AdminEditUserActivity;
import com.example.lironautomotivepvtltd.AdminMyProfile.AdminMyProfileActivity;
import com.example.lironautomotivepvtltd.AdminService.AdminServiceActivity;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.SharedPref;

public class AdminActivity extends AppCompatActivity {

    CardView MyProfile,UserProfile,Service,Logout;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        MyProfile = (CardView)findViewById(R.id.admin_profile);
        UserProfile = (CardView)findViewById(R.id.owner_details);
        Service = (CardView)findViewById(R.id.admin_service);
        Logout = (CardView)findViewById(R.id.admin_logout);

        MyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AdminMyProfileActivity.class);
                startActivity(intent);
            }
        });

        UserProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AdminEditUserActivity.class);
                startActivity(intent);
            }
        });

        Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), AdminServiceActivity.class);
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
}
