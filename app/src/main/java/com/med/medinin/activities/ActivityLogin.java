package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.med.medinin.R;

public class ActivityLogin extends AppCompatActivity {
    Button btnLogin;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            btnLogin=findViewById(R.id.btn_login);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ActivityLogin.this, ActivityRegister.class);
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
