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
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.model.Data;

import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.CustomViewHolder> {

    Context context;
    List<String> serviceslist;

    public ServicesAdapter(Context context, List<String> serviceslist) {
        this.context = context;
        this.serviceslist = serviceslist;
    }

    @Override
    public ServicesAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_services, parent, false);
        return new ServicesAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ServicesAdapter.CustomViewHolder holder, int position) {
        Glide.with(context)
                .load(R.drawable.icon_bloodtest)
                .into(holder.services_img);
        holder.services_txt.setText(serviceslist.get(position));

    }

    @Override
    public int getItemCount() {
        return serviceslist.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView services_img;
        TextView services_txt;
        public CustomViewHolder(View itemView) {
            super(itemView);
            services_img = itemView.findViewById(R.id.services_image);
            services_txt = itemView.findViewById(R.id.services_text);


        }

    }
}

