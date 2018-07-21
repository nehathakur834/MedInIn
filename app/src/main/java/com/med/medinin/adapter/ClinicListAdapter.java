package com.med.medinin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.med.medinin.R;
import com.med.medinin.activities.TimeSlotActivity;
import com.med.medinin.model.ClinicListModel;



import java.util.List;

/**
 * Created by NEHA on 5/3/2018.
 */

public class ClinicListAdapter extends RecyclerView.Adapter<ClinicListAdapter.CustomViewHolder> {

    Context context;
    List<ClinicListModel> tradeHistoryModelList;
    ImageView iv_close1;
    Button btnAppointment;
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
        holder.listarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_clinic);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getDecorView().getResources().getColor(R.color.greydialog);
                dialog.setCanceledOnTouchOutside(false);
                btnAppointment = dialog.findViewById(R.id.btn_bookappointment);
                iv_close1 = dialog.findViewById(R.id.icon_close1);
                iv_close1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnAppointment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, TimeSlotActivity.class);
                        context.startActivity(i);
                    }
                });

                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tradeHistoryModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tv_lendingid, tv_lendingamount, tv_lendingbonus ;
        LinearLayout listarrow;
        public CustomViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
            tv_lendingamount = itemView.findViewById(R.id.tv_clinicname);
            tv_lendingbonus = itemView.findViewById(R.id.tv_address);
            listarrow = itemView.findViewById(R.id.arrow);
        }

    }
}

