package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.med.medinin.R;
import com.med.medinin.fragment.ClinicListFragment;

public class SearchClinicActivity extends AppCompatActivity {

    LinearLayout lytSearchClinic;
    Fragment fragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchclinic);
//        fragmentManager = getSupportFragmentManager();
//        if(savedInstanceState==null){
//            fragment = new ClinicListFragment();
//            final FragmentTransaction transaction = fragmentManager.beginTransaction();
//            transaction.replace(R.id.frame_fragment, fragment).commit();
//        }
        lytSearchClinic = findViewById(R.id.lyt_search);
        lytSearchClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                fragment=new ClinicListFragment();
//                final FragmentTransaction transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.frame_fragment, fragment); // f2_container is your FrameLayout container
//                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                transaction.addToBackStack(null);
//                transaction.commit();
                Intent i = new Intent(SearchClinicActivity.this, ClinicListActivity.class);
                startActivity(i);

            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}