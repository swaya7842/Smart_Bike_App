package com.example.lironautomotivepvtltd.AdminService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lironautomotivepvtltd.AdminServiceContacts.ContactsActivity;
import com.example.lironautomotivepvtltd.AdminServiceNotifications.NotificationsActivity;
import com.example.lironautomotivepvtltd.R;

public class AdminServiceActivity extends AppCompatActivity {

    CardView Contacts,Notifications;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_service);
        Contacts = (CardView)findViewById(R.id.Contacts_Card);
        Notifications = (CardView)findViewById(R.id.Notifications_Card);

        Contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), ContactsActivity.class);
                startActivity(intent);
            }
        });

        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), NotificationsActivity.class);
                startActivity(intent);
            }
        });

    }
}
