package com.med.medinin.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.med.medinin.R;

import com.med.medinin.adapter.AppointListAdapter;
import com.med.medinin.model.AppointListModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEHA on 1/10/2018.
 */

public class AppointmentsFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<AppointListModel> clinicListModelList = null;
    FloatingActionButton fab;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_appointmentlist, container, false);

        lendingTableItemList();

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_appointlist);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        AppointListAdapter clinicListAdapter = new AppointListAdapter(getActivity(), clinicListModelList);
        recyclerView.setAdapter(clinicListAdapter);

        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }
    /* Initialise car items in list. */
    private void lendingTableItemList() {
        if (clinicListModelList == null) {
            clinicListModelList = new ArrayList<AppointListModel>();
            clinicListModelList.add(new AppointListModel( "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new AppointListModel( "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new AppointListModel( "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new AppointListModel( "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new AppointListModel( "Hc Total Clinic","2112,Diposrd Walk,US"));
            clinicListModelList.add(new AppointListModel( "Hc Total Clinic","2112,Diposrd Walk,US"));


        }
    }




}