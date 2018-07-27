package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.med.medinin.R;

public class ActivityOtp extends AppCompatActivity {
        Button btnOtp;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_otp);
            btnOtp=findViewById(R.id.btn_otp);
            btnOtp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ActivityOtp.this, ActivityLogin.class);
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
