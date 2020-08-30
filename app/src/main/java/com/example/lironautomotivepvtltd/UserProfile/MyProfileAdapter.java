package com.example.lironautomotivepvtltd.UserProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lironautomotivepvtltd.R;

import java.util.List;

public class MyProfileAdapter extends RecyclerView.Adapter<MyProfileAdapter.ProfileHolder> {

    Context context;
    List<UserDetailPojo> userDetailPojoList;

    public MyProfileAdapter(Context context, List<UserDetailPojo> userDetailPojoList) {
        this.context = context;
        this.userDetailPojoList = userDetailPojoList;
    }

    @NonNull
    @Override
    public MyProfileAdapter.ProfileHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View profile_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myprofile_recyclerview_layout,parent,false);

        return new ProfileHolder(profile_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProfileAdapter.ProfileHolder holder, int position) {

        UserDetailPojo userDetail = userDetailPojoList.get(position);
        holder.Name.setText(userDetail.getFull_Name());
        holder.OwnerId.setText(userDetail.getOwner_Id());
        holder.DOB.setText(userDetail.getDOB());
        holder.Email.setText(userDetail.getEmail_Id());
        holder.MobileNo.setText(userDetail.getMobile_No());
        holder.Address.setText(userDetail.getAddress());
        holder.Model.setText(userDetail.getModel());
        holder.DOP.setText(userDetail.getDOP());
        holder.VehicleRegNo.setText(userDetail.getVehicle_Reg_No());
        holder.VehicleIdentificationNo.setText(userDetail.getVehicle_Identification_No());

    }

    @Override
    public int getItemCount() {
        return userDetailPojoList.size();
    }
    
    public class ProfileHolder extends RecyclerView.ViewHolder{
        private EditText Name, OwnerId, DOB, Email, MobileNo, Address, Model, DOP, VehicleRegNo,
                VehicleIdentificationNo;

        public ProfileHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.name);
            OwnerId = itemView.findViewById(R.id.owner_id);
            DOB = itemView.findViewById(R.id.date_of_birth);
            Email = itemView.findViewById(R.id.email_id);
            MobileNo = itemView.findViewById(R.id.mobile_no);
            Address = itemView.findViewById(R.id.address);
            Model = itemView.findViewById(R.id.model);
            DOP = itemView.findViewById(R.id.date_of_purchase);
            VehicleRegNo = itemView.findViewById(R.id.vehicle_registration_number);
            VehicleIdentificationNo = itemView.findViewById(R.id.vehicle_Identification_number);

        }
    }
}
