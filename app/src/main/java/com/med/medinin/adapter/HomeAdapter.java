package com.med.medinin.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.med.medinin.R;
import com.med.medinin.model.DataModel;

import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.CustomViewHolder> {

    Context context;
    List<DataModel> dataModelList;
    int row_index;
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
    public void onBindViewHolder(HomeAdapter.CustomViewHolder holder, final int position) {
       // Glide.with(context).load(dataModelList.get(position)).into(holder.image);
        Glide.with(context)
                .load(R.drawable.icon_respiratory)
                .into(holder.image);
        //holder.image.setImageResource(dataModelList.get(position).getImage());
        holder.tv_lendingamount.setText(dataModelList.get(position).getName());
        holder.row_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            holder.image.setColorFilter(Color.argb(255, 255, 255, 255));
            holder.row_linearlayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.et_gradient_blue) );
            holder.tv_lendingamount.setTextColor(Color.parseColor("#ffffff"));
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.color_white),PorterDuff.Mode.SRC_IN);
        }
        else
        {
            holder.image.setColorFilter(ContextCompat.getColor(context, R.color.color_gray),PorterDuff.Mode.SRC_IN);
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.tv_lendingamount.setTextColor(Color.parseColor("#899bab"));

        }

    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_lendingid, tv_lendingamount, tv_lendingbonus;
        LinearLayout row_linearlayout;

        public CustomViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_recycler);
            tv_lendingamount = itemView.findViewById(R.id.title_recycler);
            row_linearlayout = (LinearLayout) itemView.findViewById(R.id.linear_lyt);


        }

    }
}

