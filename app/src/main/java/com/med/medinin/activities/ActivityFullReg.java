package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.med.medinin.MainActivity;
import com.med.medinin.R;
import com.med.medinin.fragment.BePartnerStepOneFragment;
import com.med.medinin.fragment.BePartnerStepThreeFragment;
import com.med.medinin.fragment.BePartnerStepTwoFragment;

public class ActivityFullReg  extends AppCompatActivity implements  BePartnerStepOneFragment.OnStepOneListener, BePartnerStepTwoFragment.OnStepTwoListener, BePartnerStepThreeFragment.OnStepThreeListener{

    private SectionsPagerAdapterSeller mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private NonSwipeableViewPager mViewPager;

    private StepperIndicator stepperIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullreg);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapterSeller(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.container_seller);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        stepperIndicator = (StepperIndicator) findViewById(R.id.stepper_indicator_seller);


        stepperIndicator.showLabels(false);
        stepperIndicator.setViewPager(mViewPager);
        // or keep last page as "end page"
        stepperIndicator.setViewPager(mViewPager, mViewPager.getAdapter().getCount() - 1); //


    }
    public class SectionsPagerAdapterSeller extends android.support.v4.app.FragmentPagerAdapter {

        public SectionsPagerAdapterSeller(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return BePartnerStepOneFragment.newInstance("", "");
                case 1:
                    return BePartnerStepTwoFragment.newInstance("", "");
                case 2:
                    return BePartnerStepThreeFragment.newInstance("", "");
            }

            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "First Level";
                case 1:
                    return "Second Level";
                case 2:
                    return "Finish";
            }
            return null;
        }
    }



    @Override
    public void onNextPressed(Fragment fragment) {
        if (fragment instanceof BePartnerStepOneFragment) {
            mViewPager.setCurrentItem(1, true);
        } else if (fragment instanceof BePartnerStepTwoFragment) {
            mViewPager.setCurrentItem(2, true);
        } else if (fragment instanceof BePartnerStepThreeFragment) {
            Toast.makeText(this, "Thanks For Registering", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(ActivityFullReg.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed(Fragment fragment) {
        if (fragment instanceof BePartnerStepOneFragment) {
            mViewPager.setCurrentItem(0, true);
        } else if (fragment instanceof BePartnerStepTwoFragment) {
            mViewPager.setCurrentItem(1, true);
        }
    }

}