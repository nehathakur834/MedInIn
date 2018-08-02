package com.med.medinin.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.med.medinin.R;
import com.med.medinin.activities.TimeSlotActivity;
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.model.Data;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.med.medinin.utils.CommonMethods.HOSPITAL_ADDRESS_FIELD;
import static com.med.medinin.utils.CommonMethods.HOSPITAL_ID_FIELD;
import static com.med.medinin.utils.CommonMethods.HOSPITAL_NAME_FIELD;
import static com.med.medinin.utils.CommonMethods.editor;
import static com.med.medinin.utils.CommonMethods.myPref;
import static com.med.medinin.utils.CommonMethods.sharedPreferences;

/**
 * Created by NEHA on 5/3/2018.
 */

public class ClinicListAdapter extends RecyclerView.Adapter<ClinicListAdapter.CustomViewHolder> {

    Context context;
    List<ClinicListModel> tradeHistoryModelList;
    ImageView iv_close1;
    Button btnAppointment;
    RecyclerView rViewServices;
    private static final int NUMBER_COLUMNS = 3;
    List<String> serviceList;

    public ClinicListAdapter(Context context, List<ClinicListModel> tradeHistoryModelList,List<String> serviceList) {
        this.context = context;
        this.tradeHistoryModelList = tradeHistoryModelList;
        this.serviceList=serviceList;
    }

    @Override
    public ClinicListAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_clinic, parent, false);
        return new ClinicListAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClinicListAdapter.CustomViewHolder holder, final int position) {


        Glide.with(holder.image.getContext())
                .load(R.drawable.icon_heart);


        holder.tv_lendingamount.setText(tradeHistoryModelList.get(position).getHospital_name());
        holder.tv_lendingbonus.setText(tradeHistoryModelList.get(position).getAddress());
        holder.tv_distance.setText(tradeHistoryModelList.get(position).getDistance_from());
        holder.tv_review.setText(tradeHistoryModelList.get(position).getTotal_reviews());
        holder.ratingbar.setRating(Float.parseFloat(tradeHistoryModelList.get(position).getRating()));

        holder.listarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialog_clinic);
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getDecorView().getResources().getColor(R.color.greydialog);
                dialog.setCanceledOnTouchOutside(false);

              //  List<ClinicListModel> servicesList = new ArrayList<>();

                btnAppointment = dialog.findViewById(R.id.btn_bookappointment);
                iv_close1 = dialog.findViewById(R.id.icon_close1);
                RatingBar rate=(RatingBar)dialog.findViewById(R.id.rate);
                TextView hospName=dialog.findViewById(R.id.dlg_hosp_name);
                TextView hospAddress=dialog.findViewById(R.id.dlg_hosp_address);
                TextView hospReview=dialog.findViewById(R.id.dlg_hosp_review);
                hospName.setText(tradeHistoryModelList.get(position).getHospital_name());
                hospAddress.setText(tradeHistoryModelList.get(position).getAddress());
                hospReview.setText(tradeHistoryModelList.get(position).getTotal_reviews());
                rate.setRating(Float.parseFloat(tradeHistoryModelList.get(position).getRating()));

                rViewServices = (RecyclerView)dialog.findViewById(R.id.rView_services);
                rViewServices.setLayoutManager(new GridLayoutManager(context, NUMBER_COLUMNS));
                rViewServices.setHasFixedSize(true);
                ServicesAdapter servicesAdapter = new ServicesAdapter(context, serviceList);
                rViewServices.setAdapter(servicesAdapter);

                iv_close1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                btnAppointment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sharedPreferences =context.getSharedPreferences(myPref, Context.MODE_PRIVATE);
                        editor = sharedPreferences.edit();
                        String hospital_id = tradeHistoryModelList.get(position).getID();
                        String hospital_name = tradeHistoryModelList.get(position).getHospital_name();
                        String hospital_address = tradeHistoryModelList.get(position).getAddress();

                        editor.putString(HOSPITAL_ID_FIELD, hospital_id);
                        editor.putString(HOSPITAL_NAME_FIELD, hospital_name);
                        editor.putString(HOSPITAL_ADDRESS_FIELD, hospital_address);
                        editor.apply();
                        Intent intent = new Intent(context, TimeSlotActivity.class);
                        context.startActivity(intent);

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
        TextView tv_distance, tv_lendingamount, tv_lendingbonus,tv_review ;
        LinearLayout listarrow;
        RatingBar ratingbar;
        public CustomViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.iv_image);
            tv_lendingamount = itemView.findViewById(R.id.tv_clinicname);
            tv_lendingbonus = itemView.findViewById(R.id.tv_address);
            tv_distance = itemView.findViewById(R.id.distance_from);
            tv_review = itemView.findViewById(R.id.tv_review);
            ratingbar = itemView.findViewById(R.id.rating);
            listarrow = itemView.findViewById(R.id.arrow);
        }

    }
}

