package com.med.medinin.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.med.medinin.MainActivity;
import com.med.medinin.R;

import com.med.medinin.activities.MapsActivity2;
import com.med.medinin.adapter.AppointListAdapter;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.model.AppointListModel;
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.model.Data;
import com.med.medinin.model.Hospital;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.med.medinin.utils.Apis.ALL_APPOINTMENT_URL;
import static com.med.medinin.utils.Apis.HOSPITALS_URL;

/**
 * Created by NEHA on 1/10/2018.
 */

public class AppointmentsFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<Data> dataList = new ArrayList<>();
    FloatingActionButton fab;
    ImageView img_search;
    TextView apptmnt_count;
    String count;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_appointmentlist, container, false);
        apptmnt_count = view.findViewById(R.id.appont_count);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_appointlist);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        appointMethod();
        img_search = (ImageView) view.findViewById(R.id.img_search);
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        fab = (FloatingActionButton)view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void appointMethod() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ALL_APPOINTMENT_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject rs = null;
                try {

                    rs = new JSONObject(response);
                    String status = rs.optString("msg");
                    String error = rs.optString("err");
                    if (status.equals("success")) {
                        JSONArray result = rs.getJSONArray("data");
                        for (int i = 0; i < result.length(); i++) {
                            JSONObject result1 = result.getJSONObject(i);
                            Data f = new Data();
                            f.setID(result1.getString("ID"));
                            f.setBooking_date(result1.getString("booking_date"));
                            f.setBooking_time(result1.getString("booking_time"));
                            JSONObject stobject = result1.getJSONObject("hospital");
                            Hospital ff=new Hospital();
                            ff.setHospital_name(stobject.getString("hospital_name"));
                            ff.setAddress(stobject.getString("address"));
                            JSONArray st = stobject.getJSONArray("services");
                            String[] street= new String[st.length()];
                            for(int j=0;j<st.length();j++)
                            {
                                street[j] = st.getString(j);
                                Log.i("..........",""+street);
                                ff.setServices(street);
                            }
                            f.setHospital(ff);
                            dataList.add(f);
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

                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        Toast.makeText(getActivity(), res, Toast.LENGTH_SHORT).show();
                        // Now you can use any deserializer to make sense of data
                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }
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

    }
    private void deptData() {
        AppointListAdapter clinicListAdapter = new AppointListAdapter(getActivity(), dataList);
        recyclerView.setAdapter(clinicListAdapter);
        count = ""+recyclerView.getAdapter().getItemCount();
        apptmnt_count.setText(count);
    }





}