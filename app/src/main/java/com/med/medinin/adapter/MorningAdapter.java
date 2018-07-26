package com.med.medinin.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.android.volley.toolbox.Volley;
import com.med.medinin.R;
import com.med.medinin.activities.ConfirmAppointActivity;
import com.med.medinin.activities.SearchClinicActivity;
import com.med.medinin.model.AppointListModel;
import com.med.medinin.model.MorningTimeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.android.volley.VolleyLog.TAG;
import static com.med.medinin.utils.Apis.CREATE_APPOINTMENT_URL;
import static com.med.medinin.utils.CommonMethods.HOSPITAL_ID_FIELD;
import static com.med.medinin.utils.CommonMethods.TIME_SLOT_FIELD;
import static com.med.medinin.utils.CommonMethods.editor;
import static com.med.medinin.utils.CommonMethods.myPref;
import static com.med.medinin.utils.CommonMethods.sharedPreferences;

/**
 * Created by NEHA on 5/3/2018.
 */

public class MorningAdapter extends RecyclerView.Adapter<MorningAdapter.CustomViewHolder> {

    Context context;
    List<MorningTimeModel> morningTimeModelList;
    String hospitalId, timeSlot;
    String time;

    public MorningAdapter(Context context, List<MorningTimeModel> morningTimeModelList) {
        this.context = context;
        this.morningTimeModelList = morningTimeModelList;


    }

    @Override
    public MorningAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist_morning, parent, false);
        return new MorningAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MorningAdapter.CustomViewHolder holder, final int position) {
        holder.tv_morning.setText(morningTimeModelList.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences =context.getSharedPreferences(myPref, Context.MODE_PRIVATE);
                hospitalId = sharedPreferences.getString(HOSPITAL_ID_FIELD, null);
                editor = sharedPreferences.edit();
                time = morningTimeModelList.get(position).getName();
                departmentMethod();

            }
        });


    }

    @Override
    public int getItemCount() {
        return morningTimeModelList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView  tv_morning;

        public CustomViewHolder(View itemView) {
            super(itemView);
         /*   sharedPreferences =context.getSharedPreferences(myPref, Context.MODE_PRIVATE);
            hospitalId = sharedPreferences.getString(HOSPITAL_ID_FIELD, null);
            timeSlot = sharedPreferences.getString(TIME_SLOT_FIELD, null);
            editor = sharedPreferences.edit();*/
            tv_morning = itemView.findViewById(R.id.tv_morning);

        }

    }


    private void departmentMethod() {
       /*  StringRequest stringRequest = new StringRequest(Request.Method.GET, CREATE_APPOINTMENT_URL, new Response.Listener<String>() {
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
                            DataModel f = new DataModel();
                            f.setName(result1.getString("Name"));
                            //dataModelList.add(f);
                        }
                       // deptData();

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

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("hospital_id", hospitalId);
            jsonObject.put("time", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST, CREATE_APPOINTMENT_URL,
                jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {

                            String errror = response.getString("err");
                            String status = response.getString("msg");
                            if (status.equals("success")) {
                                JSONArray result = response.getJSONArray("data");
                                Intent i = new Intent(context, ConfirmAppointActivity.class);
                                context.startActivity(i);

                            } else if (status.equals("false")) {
                                Toast.makeText(context, errror, Toast.LENGTH_SHORT).show();
                            }




                        } catch (JSONException e) {
                            e.printStackTrace();

                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        Toast.makeText(context, res, Toast.LENGTH_SHORT).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        jsonObjReq.setRetryPolicy(new
                DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonObjReq);
    }

}

