package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.med.medinin.R;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.model.ClinicListModel;

import java.util.ArrayList;
import java.util.List;

public class ClinicListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<ClinicListModel> clinicListModelList = null;
    ImageView mapView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliniclist);
        lendingTableItemList();
        mapView=findViewById(R.id.map_view);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_clinic);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ClinicListAdapter clinicListAdapter = new ClinicListAdapter(ClinicListActivity.this, clinicListModelList);
        recyclerView.setAdapter(clinicListAdapter);
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClinicListActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
    /* Initialise car items in list. */
    private void lendingTableItemList() {
        if (clinicListModelList == null) {
            clinicListModelList = new ArrayList<ClinicListModel>();
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new ClinicListModel(R.drawable.icon_heart, "Hc Total Clinic","2112,Diposrd Walk,US"));


        }
    }
}
