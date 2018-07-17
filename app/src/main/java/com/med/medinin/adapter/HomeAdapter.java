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
import com.med.medinin.model.DataModel;

import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder> {

    Context context;
    List<DataModel> dataModelList;

    public HomeAdapter(Context context, List<DataModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @Override
    public HomeAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_recycler, parent, false);
        return new HomeAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HomeAdapter.CustomViewHolder holder, int position) {


        Glide.with(holder.image.getContext())
                .load(R.drawable.icon_heart);


        holder.tv_lendingamount.setText(dataModelList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_lendingid, tv_lendingamount, tv_lendingbonus ;

        public CustomViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_recycler);
            tv_lendingamount = itemView.findViewById(R.id.title_recycler);


        }

    }
}

