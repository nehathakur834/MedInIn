package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.med.medinin.MainActivity;
import com.med.medinin.R;

public class ActivityRegister extends AppCompatActivity {
        Button btnRegister;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
            btnRegister=findViewById(R.id.btn_register);
            btnRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ActivityRegister.this, MainActivity.class);
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
