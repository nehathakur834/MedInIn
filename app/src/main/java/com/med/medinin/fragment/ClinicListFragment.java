package com.med.medinin.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.med.medinin.R;
import com.med.medinin.activities.MapStartActivity;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.adapter.HomeAdapter;
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.model.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.med.medinin.utils.Apis.DEPARTMENTS_URL;
import static com.med.medinin.utils.Apis.HOSPITALS_URL;

/**
 * Created by NEHA on 1/10/2018.
 */

public class ClinicListFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<ClinicListModel> clinicListModelList = new ArrayList<>();
    ImageView mapView;
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
        view = inflater.inflate(R.layout.frag_cliniclist, container, false);

        //lendingTableItemList();
        mapView=view.findViewById(R.id.map_view);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_clinic);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        hospitalMethod();
        mapView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapStartActivity.class);
                startActivity(i);


            }
        });
        return view;
    }

   /* *//* Initialise car items in list. *//*
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


        }*/

    private void hospitalMethod() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, HOSPITALS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject rs = null;
                try {
                    if (dialog != null) {
                        dialog.dismiss();
                        dialog = null;
                    }
                    rs = new JSONObject(response);
                    String status = rs.optString("msg");
                    String error = rs.optString("err");
                    if (status.equals("success")) {
                        JSONArray result = rs.getJSONArray("data");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject result1 = result.getJSONObject(i);
                            ClinicListModel f = new ClinicListModel();
                            f.setHospital_name(result1.getString("hospital_name"));
                            f.setAddress(result1.getString("address"));
                            f.setRating(result1.getString("rating"));
                            f.setDistance_from(result1.getString("distance_from"));
                            f.setTotal_reviews(result1.getString("total_reviews"));
                            JSONArray st = result1.getJSONArray("services");
                            String[] street= new String[st.length()];
                            for(int j=0;j<st.length();j++)
                            {
                                street[j] = st.getString(j);
                                Log.i("..........",""+street);
                                f.setServices(street);
                            }

                            clinicListModelList.add(f);
                        }
                        deptData();

                    } else if (status.equals("false")) {
                        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (dialog != null) {
                    dialog.dismiss();
                    dialog = null;
                }
                Toast.makeText(getActivity(), "Some error occurred, please try again", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        stringRequest.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Please wait....");
        dialog.setTitle("");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
    private void deptData() {
        ClinicListAdapter clinicListAdapter = new ClinicListAdapter(getActivity(), clinicListModelList);
        recyclerView.setAdapter(clinicListAdapter);
    }





}