package com.med.medinin.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.med.medinin.MainActivity;
import com.med.medinin.R;

import static com.med.medinin.utils.CommonMethods.HOSPITAL_ADDRESS_FIELD;
import static com.med.medinin.utils.CommonMethods.HOSPITAL_NAME_FIELD;
import static com.med.medinin.utils.CommonMethods.editor;
import static com.med.medinin.utils.CommonMethods.myPref;
import static com.med.medinin.utils.CommonMethods.sharedPreferences;

public class ConfirmAppointActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 10000;
    TextView confm_name,confm_address;
    String confirmName,confirmAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmapoint);
        sharedPreferences =getSharedPreferences(myPref, Context.MODE_PRIVATE);
        confirmName = sharedPreferences.getString(HOSPITAL_NAME_FIELD, null);
        confirmAddress = sharedPreferences.getString(HOSPITAL_ADDRESS_FIELD, null);
        editor = sharedPreferences.edit();
        confm_name=findViewById(R.id.confm_hospname);
        confm_address=findViewById(R.id.confm_hospaddress);
        confm_name.setText(confirmName);
        confm_address.setText(confirmAddress);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String confirm_id="confirm";
                Intent i = new Intent(ConfirmAppointActivity.this, MainActivity.class);
                i.putExtra("confirm",confirm_id);
                startActivity(i);
                finish();
                }
        }, SPLASH_TIME_OUT);
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}