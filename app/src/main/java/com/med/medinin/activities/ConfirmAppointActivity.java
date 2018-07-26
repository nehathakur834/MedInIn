package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.med.medinin.MainActivity;
import com.med.medinin.R;

public class ConfirmAppointActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmapoint);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String confirm_id="confirm";
                Intent i = new Intent(ConfirmAppointActivity.this, MainActivity.class);
                i.putExtra("confm",confirm_id);
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