package com.med.medinin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.med.medinin.R;
import com.med.medinin.activities.MapStartActivity;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.model.ClinicListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEHA on 1/10/2018.
 */

public class ClinicListFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<ClinicListModel> clinicListModelList = null;
    ImageView mapView;
    Fragment fragment;
    private FragmentManager fragmentManager;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_cliniclist, container, false);

        lendingTableItemList();
        mapView=view.findViewById(R.id.map_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_clinic);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        ClinicListAdapter clinicListAdapter = new ClinicListAdapter(getActivity(), clinicListModelList);
        recyclerView.setAdapter(clinicListAdapter);
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapStartActivity.class);
                startActivity(i);


            }
        });
        return view;
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