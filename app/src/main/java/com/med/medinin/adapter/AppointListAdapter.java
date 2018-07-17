package com.med.medinin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.med.medinin.R;
import com.med.medinin.model.AppointListModel;
import com.med.medinin.model.ClinicListModel;

import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class AppointListAdapter extends RecyclerView.Adapter<AppointListAdapter.CustomViewHolder> {

    Context context;
    List<AppointListModel> tradeHistoryModelList;

    public AppointListAdapter(Context context, List<AppointListModel> tradeHistoryModelList) {
        this.context = context;
        this.tradeHistoryModelList = tradeHistoryModelList;
    }

    @Override
    public AppointListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_appoint, parent, false);
        return new AppointListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppointListAdapter.CustomViewHolder holder, int position) {



        holder.tv_lendingamount.setText(tradeHistoryModelList.get(position).getName());
        holder.tv_lendingbonus.setText(tradeHistoryModelList.get(position).getAddress());

    }

    @Override
    public int getItemCount() {
        return tradeHistoryModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_lendingid, tv_lendingamount, tv_lendingbonus ;

        public CustomViewHolder(View itemView) {
            super(itemView);

            tv_lendingamount = itemView.findViewById(R.id.clinicname);
            tv_lendingbonus = itemView.findViewById(R.id.address);

        }

    }
}

