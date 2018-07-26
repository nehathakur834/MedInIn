package com.med.medinin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.med.medinin.R;
import com.med.medinin.model.Data;

import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class AppointListAdapter extends RecyclerView.Adapter<AppointListAdapter.CustomViewHolder> {

    Context context;
    List<Data> data;

    public AppointListAdapter(Context context, List<Data> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public AppointListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_appoint, parent, false);
        return new AppointListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointListAdapter.CustomViewHolder holder, int position) {

        holder.txt_bookingdate.setText(data.get(position).getBooking_date());
        holder.txt_clinicname.setText(data.get(position).getHospital().getHospital_name());
        holder.txt_aptmt_address.setText(data.get(position).getHospital().getAddress());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView txt_clinicname, txt_bookingdate, txt_aptmt_address ;

        public CustomViewHolder(View itemView) {
            super(itemView);
            txt_bookingdate = itemView.findViewById(R.id.aptmnt_bookingdate);
            txt_clinicname = itemView.findViewById(R.id.aptmnt_clinicname);
            txt_aptmt_address = itemView.findViewById(R.id.aptmt_address);

        }

    }
}

