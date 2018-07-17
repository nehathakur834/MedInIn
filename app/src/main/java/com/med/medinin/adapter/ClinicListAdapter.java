package com.med.medinin.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.med.medinin.R;
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.weather.ForecastAdapter;


import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class ClinicListAdapter extends RecyclerView.Adapter<ClinicListAdapter.CustomViewHolder> {

    Context context;
    List<ClinicListModel> tradeHistoryModelList;

    public ClinicListAdapter(Context context, List<ClinicListModel> tradeHistoryModelList) {
        this.context = context;
        this.tradeHistoryModelList = tradeHistoryModelList;
    }

    @Override
    public ClinicListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_clinic, parent, false);
        return new ClinicListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClinicListAdapter.CustomViewHolder holder, int position) {


        Glide.with(holder.image.getContext())
                .load(R.drawable.icon_heart);


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
            image = itemView.findViewById(R.id.iv_image);
            tv_lendingamount = itemView.findViewById(R.id.tv_clinicname);
            tv_lendingbonus = itemView.findViewById(R.id.tv_address);

        }

    }
}

