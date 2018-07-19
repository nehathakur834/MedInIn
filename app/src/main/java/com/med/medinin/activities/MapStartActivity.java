package com.med.medinin.activities;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.med.medinin.R;
import com.med.medinin.fragment.AppointmentsFragment;
import com.med.medinin.fragment.ClinicListFragment;
import com.med.medinin.fragment.MapsFragment;
import com.med.medinin.fragment.SettingFragment;

public class MapStartActivity extends AppCompatActivity {
    Fragment fragment;
    private FragmentManager fragmentManager;
    RelativeLayout lineHome,linearAppoint,linearSetting;
    ImageView imageHome,imgAppoint,imgsetting;
    View homeview,appointview,settingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapstart);

        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState==null){
            fragment = new MapsFragment();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_fragment_map, fragment).commit();
        }

        lineHome=findViewById(R.id.llyt_home);
        linearAppoint=findViewById(R.id.llyt_appointment);
        linearSetting=findViewById(R.id.llyt_setting);
        imageHome=findViewById(R.id.img_home);
        imgAppoint=findViewById(R.id.img_appoint);
        imgsetting=findViewById(R.id.img_setting);
        homeview=findViewById(R.id.home_view);
        appointview=findViewById(R.id.home_appoitment);
        settingview=findViewById(R.id.home_setting);
/*        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageHome.setImageResource(R.drawable.ic_explore_blue);
            }
        });
        imgAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageHome.setImageResource(R.drawable.ic_explore_blue);
            }
        });
        imgsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageHome.setImageResource(R.drawable.ic_explore_blue);
            }
        });*/

        lineHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new MapsFragment();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_fragment_map, fragment); // f2_container is your FrameLayout container
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
                imageHome.getBackground().setColorFilter(Color.parseColor("#73f3ff"), PorterDuff.Mode.SRC_ATOP);
                imgAppoint.getBackground().setColorFilter(Color.parseColor("#FFD5D8DA"), PorterDuff.Mode.SRC_ATOP);
                imgsetting.getBackground().setColorFilter(Color.parseColor("#FFD5D8DA"), PorterDuff.Mode.SRC_ATOP);

                homeview.setBackgroundColor(getResources().getColor(R.color.color_blue));
                appointview.setBackgroundColor(getResources().getColor(R.color.gcolor));
                settingview.setBackgroundColor(getResources().getColor(R.color.gcolor));
            }
        });
        linearAppoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new AppointmentsFragment();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_fragment_map, fragment); // f2_container is your FrameLayout container
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();
                imgAppoint.getBackground().setColorFilter(Color.parseColor("#73f3ff"), PorterDuff.Mode.SRC_ATOP);
                imageHome.getBackground().setColorFilter(Color.parseColor("#FFD5D8DA"), PorterDuff.Mode.SRC_ATOP);
                imgsetting.getBackground().setColorFilter(Color.parseColor("#FFD5D8DA"), PorterDuff.Mode.SRC_ATOP);

                appointview.setBackgroundColor(getResources().getColor(R.color.color_blue));
                homeview.setBackgroundColor(getResources().getColor(R.color.gcolor));
                settingview.setBackgroundColor(getResources().getColor(R.color.gcolor));


            }
        });
        linearSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new SettingFragment();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_fragment_map, fragment); // f2_container is your FrameLayout container
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);
                transaction.commit();

                imgsetting.getBackground().setColorFilter(Color.parseColor("#73f3ff"), PorterDuff.Mode.SRC_ATOP);
                imgAppoint.getBackground().setColorFilter(Color.parseColor("#FFD5D8DA"), PorterDuff.Mode.SRC_ATOP);
                imageHome.getBackground().setColorFilter(Color.parseColor("#FFD5D8DA"), PorterDuff.Mode.SRC_ATOP);

                settingview.setBackgroundColor(getResources().getColor(R.color.color_blue));
                appointview.setBackgroundColor(getResources().getColor(R.color.gcolor));
                homeview.setBackgroundColor(getResources().getColor(R.color.gcolor));

            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
