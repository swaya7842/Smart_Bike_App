package com.example.lironautomotivepvtltd.UserVehicleDetails;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lironautomotivepvtltd.R;

import java.util.List;

public class VehicleRecyclerAdapter extends RecyclerView.Adapter<VehicleRecyclerAdapter.VehicleHolder> {

     Context context;
     List<Vehicle> vehicles;

    public VehicleRecyclerAdapter(Context context, List<Vehicle> vehicles) {
        this.context = context;
        this.vehicles = vehicles;
    }

    @NonNull
    @Override
    public VehicleRecyclerAdapter.VehicleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vehicle_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vehicle_recycler_layout,parent,false);

        return new VehicleHolder(vehicle_view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleRecyclerAdapter.VehicleHolder holder, int position) {

        Vehicle vehicledata = vehicles.get(position);
        holder.model.setText(vehicledata.getModel());
        holder.vehicle_id_no.setText(vehicledata.getVehicle_Identification_No());
        holder.vehicle_reg_no.setText(vehicledata.getVehicle_Reg_No());

    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

public class VehicleHolder extends RecyclerView.ViewHolder{

        TextView model,vehicle_id_no,vehicle_reg_no;

    public VehicleHolder(@NonNull View itemView) {
        super(itemView);

        model = itemView.findViewById(R.id.bike_model_name);
        vehicle_id_no = itemView.findViewById(R.id.vehicle_id);
        vehicle_reg_no = itemView.findViewById(R.id.vehicle_reg_no);
    }
}
}
