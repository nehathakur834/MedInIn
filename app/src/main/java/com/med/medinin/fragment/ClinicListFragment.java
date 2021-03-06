package com.med.medinin.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.med.medinin.MainActivity;
import com.med.medinin.R;
import com.med.medinin.activities.ConfirmAppointActivity;
import com.med.medinin.activities.MapStartActivity;
import com.med.medinin.activities.SearchClinicActivity;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.model.ClinicListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;
import static com.med.medinin.utils.Apis.CREATE_APPOINTMENT_URL;
import static com.med.medinin.utils.Apis.HOSPITALS_URL;
import static com.med.medinin.utils.CommonMethods.ADDRESS_FIELD;
import static com.med.medinin.utils.CommonMethods.DEPARTMENT_ID_FIELD;
import static com.med.medinin.utils.CommonMethods.DEPARTMENT_NAME_FIELD;
import static com.med.medinin.utils.CommonMethods.HOSPITAL_ID_FIELD;
import static com.med.medinin.utils.CommonMethods.LATITUDE_FIELD;
import static com.med.medinin.utils.CommonMethods.LONGITUDE_FIELD;
import static com.med.medinin.utils.CommonMethods.TIME_SLOT_FIELD;
import static com.med.medinin.utils.CommonMethods.editor;
import static com.med.medinin.utils.CommonMethods.myPref;
import static com.med.medinin.utils.CommonMethods.sharedPreferences;

/**
 * Created by NEHA on 1/10/2018.
 */

public class ClinicListFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<ClinicListModel> clinicListModelList = new ArrayList<>();
    ImageView mapView;
    ProgressDialog dialog;
    LinearLayout linear_circle;
    String depart_id,depart_name,address_field,st_lang,st_longt;
    TextView frg_clinicname,frg_cliniccount,back_text;
    List<String> serviceList;
    String count;
    EditText edit_spin;
    Spinner mEditSpinner;
    String[] bankNames = {"Sort","Price", "Brand","Size"};
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
        sharedPreferences =getActivity().getSharedPreferences(myPref, Context.MODE_PRIVATE);
        depart_id = sharedPreferences.getString(DEPARTMENT_ID_FIELD, null);
        depart_name = sharedPreferences.getString(DEPARTMENT_NAME_FIELD, null);
        address_field = sharedPreferences.getString(ADDRESS_FIELD, null);
        st_lang = sharedPreferences.getString(LATITUDE_FIELD, null);
        st_longt = sharedPreferences.getString(LONGITUDE_FIELD, null);

        edit_spin =  view.findViewById(R.id.editable_spinner);
        mEditSpinner = (Spinner) view.findViewById(R.id.spinner_sort);
        mEditSpinner.setOnItemSelectedListener(this);
        edit_spin.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View arg0, boolean hasFocus) {
                // TODO Auto-generated method stub
                if(hasFocus){
                    mEditSpinner.setVisibility(View.VISIBLE);

                }
            }
        });

        mEditSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                //edit_spin.setText(mEditSpinner.getSelectedItem().toString()); //this is taking the first value of the spinner by default.

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });
       /* ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(getActivity(), R.layout.spinner_text, bankNames );
        langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,bankNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mEditSpinner.setAdapter(adapter);
        back_text=view.findViewById(R.id.back_txt);
        back_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),SearchClinicActivity.class);
                startActivity(intent);
            }
        });
        frg_clinicname=view.findViewById(R.id.frag_clinicname);
        frg_cliniccount=view.findViewById(R.id.frag_cliniccount);
        frg_clinicname.setText(depart_name);
        editor = sharedPreferences.edit();
        linear_circle =view.findViewById(R.id.linear_circle);
        linear_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
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


    private void hospitalMethod() {


        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("department_id", depart_id);
            jsonObject.put("department_name", depart_name);
            jsonObject.put("address", address_field);
            jsonObject.put("distance", "1");
            jsonObject.put("lat", st_lang);
            jsonObject.put("lng", st_longt);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, HOSPITALS_URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            String status = response.optString("msg");
                            String error = response.optString("err");
                            if (status.equals("success")) {
                                JSONArray result = response.getJSONArray("data");
                                for (int i = 0; i < result.length(); i++) {
                                    JSONObject result1 = result.getJSONObject(i);
                                    ClinicListModel f = new ClinicListModel();
                                    f.setID(result1.getString("ID"));
                                    f.setHospital_name(result1.getString("hospital_name"));
                                    f.setAddress(result1.getString("address"));
                                    f.setRating(result1.getString("rating"));
                                    f.setDistance_from(result1.getString("distance_from"));
                                    f.setTotal_reviews(result1.getString("total_reviews"));
                                    JSONArray st = result1.getJSONArray("services");
                                    serviceList =new ArrayList<>();
                                    try {
                                        // jsonString is a string variable that holds the JSON
                                        for (int i2 = 0; i2 < st.length(); i2++) {
                                            String value=st.getString(i2);
                                            Log.e("json", i2+"="+value);
                                            serviceList.add(value);
                                        }
                                    } catch (JSONException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }


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

                Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
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
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        jsonObjReq.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjReq);
    }


    private void deptData() {
        ClinicListAdapter clinicListAdapter = new ClinicListAdapter(getActivity(), clinicListModelList,serviceList);
        recyclerView.setAdapter(clinicListAdapter);
        count = ""+recyclerView.getAdapter().getItemCount();
        frg_cliniccount.setText(count);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


   /*  StringRequest stringRequest = new StringRequest(Request.Method.POST, HOSPITALS_URL, new Response.Listener<String>() {
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
                            f.setID(result1.getString("ID"));
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
        dialog.show();*/



}