package com.example.lironautomotivepvtltd.UserVehicleDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lironautomotivepvtltd.Network.ApiClient;
import com.example.lironautomotivepvtltd.Network.ApiInterface;
import com.example.lironautomotivepvtltd.R;
import com.example.lironautomotivepvtltd.SharedPref;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VehicleDetailsActivity extends AppCompatActivity {

    private final String TAG = VehicleDetailsActivity.class.getSimpleName();

    ApiInterface apiInterface;
    String MobileNumber;

    TextView Bike_Model,Vehicle_Id,Vehicle_Reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        Bike_Model = (TextView)findViewById(R.id.bike_model_name);
        Vehicle_Id = (TextView)findViewById(R.id.vehicle_id);
        Vehicle_Reg = (TextView)findViewById(R.id.vehicle_reg_no);

//        vehicle_recyclerView = (RecyclerView)findViewById(R.id.recycle_vehicle);
//        vehicle_recyclerView.setHasFixedSize(true);
//        vehicle_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        vehicle_list = new ArrayList<>();

//
//        SharedPreferences preferences = getSharedPreferences("Mobile_No",MODE_PRIVATE);
//        Mobile = preferences.getString("Mobile_No","");
//      int  MobileNo = preferences.getInt("Mobile_No",0);
//        Log.e(TAG, "Mobile_No_vehicle_details: "+Mobile );
//        Log.e(TAG, "Mobile_No_vehicle_detailsInt: "+MobileNo );
//        testing.setText(Mobile);

//
        SharedPreferences dashboardPref = getSharedPreferences("Mobile_No", Context.MODE_PRIVATE);
        MobileNumber = dashboardPref.getString("Mobile_No","null");
        Log.e(TAG, "vehicle_MobileNumber: "+MobileNumber );

//        Mobile = sharedPref.getMobileNumber();
//        Log.e(TAG, "onCreateMobile: "+Mobile );


        getvehicleDetails();

    }

    public void getvehicleDetails(){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Vehicle>> vehicle_details = apiInterface.getVehicles(MobileNumber);

        vehicle_details.enqueue(new Callback<List<Vehicle>>() {
            @Override
            public void onResponse(Call<List<Vehicle>> call, Response<List<Vehicle>> response) {

//                vehicle_list = response.body();
//                 vehicleRecyclerAdapter = new VehicleRecyclerAdapter(getApplicationContext(),vehicle_list);
//                vehicle_recyclerView.setAdapter(vehicleRecyclerAdapter);

                List<Vehicle> vehicles = response.body();
                Log.e(TAG, "onResponseJson: "+vehicles );

                String name = vehicles.get(0).getModel();
                Bike_Model.setText(name);
                Log.e(TAG, "Name: " + Bike_Model);

                String DOB = vehicles.get(0).getVehicle_Identification_No();
                Vehicle_Id.setText(DOB);
                Log.e(TAG, "Admin_DOB: " + Vehicle_Id);

                String email = vehicles.get(0).getVehicle_Reg_No();
                Vehicle_Reg.setText(email);
                Log.e(TAG, "Email: " + Vehicle_Reg);


            }

            @Override
            public void onFailure(Call<List<Vehicle>> call, Throwable t) {

                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
