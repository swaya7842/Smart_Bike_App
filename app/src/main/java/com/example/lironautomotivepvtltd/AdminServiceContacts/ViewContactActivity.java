package com.example.lironautomotivepvtltd.AdminServiceContacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lironautomotivepvtltd.R;

import static com.example.lironautomotivepvtltd.AdminServiceContacts.AppConstant.CONTACT_NAME;
import static com.example.lironautomotivepvtltd.AdminServiceContacts.AppConstant.CONTACT_PHONE;


public class ViewContactActivity extends AppCompatActivity {

    private String nameStr,phoneStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact2);

        loadContactInfo();
        Intent intent = getIntent();
        nameStr = intent.getStringExtra(CONTACT_NAME);
        phoneStr = intent.getStringExtra(CONTACT_PHONE);
    }

    private void loadContactInfo() {
        TextView name = findViewById(R.id.view_contact_name);
        name.setText(nameStr);

        TextView phone = findViewById(R.id.view_contact_phone);
        phone.setText(phoneStr);
    }
    }
