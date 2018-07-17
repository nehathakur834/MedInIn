package com.med.medinin;

import android.Manifest;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.med.medinin.fragment.AppointmentsFragment;
import com.med.medinin.fragment.SettingFragment;
import com.med.medinin.weather.WeatherFragment;

import static com.med.medinin.utils.CommonMethods.hasPermissions;

public class MainActivity extends AppCompatActivity {
    private final int PERMISSION_ALL = 1;
    private final String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.READ_PHONE_STATE, android.Manifest.permission.READ_SMS, android.Manifest.permission.RECEIVE_SMS,Manifest.permission.CALL_PHONE};
    Fragment fragment;
    private FragmentManager fragmentManager;
    RelativeLayout lineHome,linearAppoint,linearSetting;
    ImageView imageHome,imgAppoint,imgsetting;
    View homeview,appointview,settingview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState==null){
            fragment = new WeatherFragment();
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_fragment_containers, fragment).commit();
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
                fragment = new WeatherFragment();
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_fragment_containers, fragment); // f2_container is your FrameLayout container
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
                transaction.replace(R.id.frame_fragment_containers, fragment); // f2_container is your FrameLayout container
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
                transaction.replace(R.id.frame_fragment_containers, fragment); // f2_container is your FrameLayout container
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
      /*  BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        //  bottomNavigationView.setSelectedItemId(R.id.action_account);
        bottomNavigationView.setBackgroundColor(getResources().getColor(R.color.color_lightGray));
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.menu_home:
                        fragment = new WeatherFragment();
                        break;
                    case R.id.menu_search:
                        fragment = new AppointmentsFragment();
                        break;
                    case R.id.menu_notifications:
                        fragment = new SettingFragment();
                        break;

                }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.frame_fragment_containers, fragment).commit();
                return true;
            }
        });*/

    }
}
