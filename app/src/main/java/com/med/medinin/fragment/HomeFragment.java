package com.med.medinin.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;
import com.med.medinin.BuildConfig;
import com.med.medinin.MainActivity;
import com.med.medinin.R;

import com.med.medinin.activities.MapsActivity2;

import com.med.medinin.activities.SearchClinicActivity;
import com.med.medinin.adapter.ClinicListAdapter;
import com.med.medinin.adapter.HomeAdapter;
import com.med.medinin.model.AppointListModel;
import com.med.medinin.model.ClinicListModel;
import com.med.medinin.model.DataModel;
import com.reginald.editspinner.EditSpinner;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by NEHA on 1/10/2018.
 */

public class HomeFragment extends Fragment {
    private View view;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    private List<DataModel> dataModelList = null;
    private LocationManager locationManager;
    private String provider;
    Fragment fragment;
    private FragmentManager fragmentManager;
    private static final String TAG = "bmn";


    // location last updated time
    private String mLastUpdateTime;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;


    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    // boolean flag to toggle the ui
    private Boolean mRequestingLocationUpdates;
    Button btnSearch;
    EditText tvAddress;
    ImageView imageView;
    public MainActivity activity;
    Context context;
    Spinner spin;
    EditSpinner mEditSpinner;
    ImageView imgee;
    String[] bankNames = {"1705,Lake streat,Uk", "1207,Dipord ,US", "1225,Walk Streat,Us", "1225,Walk Streat,Us", "1225,Walk Streat,Us"};

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
        //fragmentManager = getFragmentManager();
//        if(savedInstanceState==null){
//            fragment = new SearchClinicFragment();
//            final FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.frame_frag, fragment).commit();
//        }
        context = getActivity();
        lendingTableItemList();
        recyclerView = (RecyclerView) view.findViewById(R.id.horizontal_recycler_view);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        HomeAdapter clinicListAdapter = new HomeAdapter(getActivity(), dataModelList);
        recyclerView.setAdapter(clinicListAdapter);
        mEditSpinner = (EditSpinner) view.findViewById(R.id.edit_spinner);
        init();
        imageView = view.findViewById(R.id.img_loctn);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MapsActivity2.class);
                startActivity(i);
            }
        });
     /*   tvAddress = view.findViewById(R.id.edit_txt);

        tvAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(getActivity(), MapsFragment.class);
//                startActivity(i);

            }
        });*/
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        mRequestingLocationUpdates = true;
                        startLocationUpdates();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // open device settings when the permission is
                            // denied permanently
                            openSettings();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).check();


        btnSearch = view.findViewById(R.id.btn_search);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SearchClinicActivity.class);
                startActivity(i);

//               fragment = new SearchClinicFragment();
//                final FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.frame_frag, fragment); // f2_container is your FrameLayout container
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.addToBackStack(null);
//                transaction.commit();
            }
        });
     /*   spin = view.findViewById(R.id.clinic_list);
        imgee = view.findViewById(R.id.imge);
        imgee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SppinerView(view);
            }
        });*/

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,
                bankNames);
        mEditSpinner.setAdapter(adapter);
        return view;
    }

    /*    public void SppinerView(View view){
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text, bankNames);
            dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
            spin.setAdapter(dataAdapter);
            spin.setVisibility(View.VISIBLE);
            spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if(position!=0) {
                       *//* button1.setText(countriesList[position]);*//*
                    spin.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }*/
    private void updateLocationUI() {
        if (mCurrentLocation != null) {

            Geocoder geocoder = null;
            List<Address> addresses = null;

            try {
                geocoder = new Geocoder(context, Locale.getDefault());
                addresses = geocoder.getFromLocation(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(addresses!=null) {
                String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                String city = addresses.get(0).getLocality();
                String state = addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                String postalCode = addresses.get(0).getPostalCode();
                String knownName = addresses.get(0).getFeatureName();

                mEditSpinner.setText(address);
                mEditSpinner.setSelection(mEditSpinner.getText().length());
                mEditSpinner.requestFocus();
            }
            //Toast.makeText(getActivity(), address+"-"+city,Toast.LENGTH_SHORT).show();

          /*  txtLocationResult.setText(
                    "Lat: " + mCurrentLocation.getLatitude() + ", " +
                            "Lng: " + mCurrentLocation.getLongitude()
            );

            // giving a blink animation on TextView
            txtLocationResult.setAlpha(0);
            txtLocationResult.animate().alpha(1).setDuration(300);

            // location last updated time
            txtUpdatedOn.setText("Last updated on: " + mLastUpdateTime);*/
        }


    }

    private void init() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());
        mSettingsClient = LocationServices.getSettingsClient(getActivity());

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                // location is received
                mCurrentLocation = locationResult.getLastLocation();
                mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());

                updateLocationUI();
            }
        };

        mRequestingLocationUpdates = false;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        mLocationSettingsRequest = builder.build();
    }

    private void startLocationUpdates() {
        mSettingsClient
                .checkLocationSettings(mLocationSettingsRequest)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<LocationSettingsResponse>() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                        Log.i(TAG, "All location settings are satisfied.");

                        // Toast.makeText(getActivity(), "Started location updates!", Toast.LENGTH_SHORT).show();

                        //noinspection MissingPermission
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest,
                                mLocationCallback, Looper.myLooper());

                        updateLocationUI();
                    }
                })
                .addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                Log.i(TAG, "Location settings are not satisfied. Attempting to upgrade " +
                                        "location settings ");
                                try {
                                    // Show the dialog by calling startResolutionForResult(), and check the
                                    // result in onActivityResult().
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.i(TAG, "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be " +
                                        "fixed here. Fix in Settings.";
                                Log.e(TAG, errorMessage);

                                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                        }

                        updateLocationUI();
                    }
                });
    }

    private void openSettings() {
        Intent intent = new Intent();
        intent.setAction(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",
                BuildConfig.APPLICATION_ID, null);
        intent.setData(uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /* Initialise car items in list. */
    private void lendingTableItemList() {
        if (dataModelList == null) {
            dataModelList = new ArrayList<DataModel>();
            dataModelList.add(new DataModel(R.drawable.icon_general, "General"));
            dataModelList.add(new DataModel(R.drawable.icon_respiratory, "Respiratory"));
            dataModelList.add(new DataModel(R.drawable.icon_heart, "Cardiology"));
            dataModelList.add(new DataModel(R.drawable.icon_general, "General"));
            dataModelList.add(new DataModel(R.drawable.icon_respiratory, "Respiratory"));

        }
    }
   /* @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }*/

}