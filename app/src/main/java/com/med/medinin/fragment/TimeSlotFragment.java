package com.med.medinin.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.med.medinin.R;
import com.med.medinin.adapter.MorningAdapter;
import com.med.medinin.model.MorningTimeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.VolleyLog.TAG;
import static com.med.medinin.utils.Apis.CREATE_APPOINTMENT_URL;
import static com.med.medinin.utils.CommonMethods.HOSPITAL_ID_FIELD;
import static com.med.medinin.utils.CommonMethods.TIME_SLOT_FIELD;
import static com.med.medinin.utils.CommonMethods.editor;
import static com.med.medinin.utils.CommonMethods.myPref;
import static com.med.medinin.utils.CommonMethods.sharedPreferences;

/**
 * Created by NEHA on 1/10/2018.
 */

public class TimeSlotFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    private List<MorningTimeModel> morningTimeModelList = null;
    private static final int NUMBER_COLUMNS = 3;

    RecyclerView recyclerViewAftrn;
    GridLayoutManager gridLayoutManager1;
    private List<MorningTimeModel> afternoonTimeModelList = null;
    private static final int NUMBER_COLUMNS_AFTR = 3;

    RecyclerView recyclerViewEveng;
    GridLayoutManager gridLayoutManager2;
    private List<MorningTimeModel> eveningTimeModelList = null;
    private static final int NUMBER_COLUMNS_EVENG= 3;

    ProgressDialog dialog;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_timeslot, container, false);
        lendingTableItemList();
        recyclerView = (RecyclerView)view.findViewById(R.id.rView_morning);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), NUMBER_COLUMNS));
        recyclerView.setHasFixedSize(true);
        MorningAdapter morningAdapter = new MorningAdapter(getActivity(), morningTimeModelList);
        recyclerView.setAdapter(morningAdapter);

        ItemListAftrnoon();
        recyclerViewAftrn = (RecyclerView)view.findViewById(R.id.rView_aftrnoon);
        recyclerViewAftrn.setLayoutManager(new GridLayoutManager(getActivity(), NUMBER_COLUMNS));
        recyclerViewAftrn.setHasFixedSize(true);
        MorningAdapter afternoonAdapter = new MorningAdapter(getActivity(), afternoonTimeModelList);
        recyclerViewAftrn.setAdapter(afternoonAdapter);

        ItemListEvening();
        recyclerViewEveng = (RecyclerView)view.findViewById(R.id.rView_evening);
        recyclerViewEveng.setLayoutManager(new GridLayoutManager(getActivity(), NUMBER_COLUMNS));
        recyclerViewEveng.setHasFixedSize(true);
        MorningAdapter eveningAdapter = new MorningAdapter(getActivity(), eveningTimeModelList);
        recyclerViewEveng.setAdapter(eveningAdapter);

        return view;
    }

    private void lendingTableItemList() {
        if (morningTimeModelList == null) {
            morningTimeModelList = new ArrayList<MorningTimeModel>();
            morningTimeModelList.add(new MorningTimeModel( "9:30am"));
            morningTimeModelList.add(new MorningTimeModel( "10:20am"));
            morningTimeModelList.add(new MorningTimeModel( "11:30am"));
            }
    }

    private void ItemListAftrnoon() {
        if (afternoonTimeModelList == null) {
            afternoonTimeModelList = new ArrayList<MorningTimeModel>();
            afternoonTimeModelList.add(new MorningTimeModel( "12:00pm"));
            afternoonTimeModelList.add(new MorningTimeModel( "12:30pm"));
            afternoonTimeModelList.add(new MorningTimeModel( "13:00pm"));
            afternoonTimeModelList.add(new MorningTimeModel( "14:30pm"));
            afternoonTimeModelList.add(new MorningTimeModel( "15:00pm"));
            afternoonTimeModelList.add(new MorningTimeModel( "16:30pm"));
        }
    }

    private void ItemListEvening() {
        if (eveningTimeModelList == null) {
            eveningTimeModelList = new ArrayList<MorningTimeModel>();
            eveningTimeModelList.add(new MorningTimeModel( "17:30pm"));
            eveningTimeModelList.add(new MorningTimeModel( "19:00pm"));

        }
    }

 /*   private void deptData() {
        HomeAdapter clinicListAdapter = new HomeAdapter(getActivity(), dataModelList);
        recyclerView.setAdapter(clinicListAdapter);
    }*/


}