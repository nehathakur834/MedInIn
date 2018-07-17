package com.med.medinin.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.med.medinin.R;
import com.med.medinin.activities.ClinicListActivity;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.adapter.HomeAdapter;
import com.med.medinin.model.AppointListModel;
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.model.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NEHA on 1/10/2018.
 */

public class HomeFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<DataModel> dataModelList = null;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_home, container, false);
        lendingTableItemList();
        recyclerView = (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        HomeAdapter clinicListAdapter = new HomeAdapter(getActivity(), dataModelList);
        recyclerView.setAdapter(clinicListAdapter);
        return view;
    }

    /* Initialise car items in list. */
    private void lendingTableItemList() {
        if (dataModelList == null) {
            dataModelList = new ArrayList<DataModel>();
            dataModelList.add(new DataModel(R.drawable.icon_stethoscope, "Hc Total Clinic"));
            dataModelList.add(new DataModel(R.drawable.icon_stethoscope, "Hc Total Clinic"));
            dataModelList.add(new DataModel(R.drawable.icon_stethoscope, "Hc Total Clinic"));
            dataModelList.add(new DataModel(R.drawable.icon_stethoscope, "Hc Total Clinic"));
            dataModelList.add(new DataModel(R.drawable.icon_stethoscope, "Hc Total Clinic"));
            }
    }


}