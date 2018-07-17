package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.med.medinin.R;

public class SearchClinicActivity extends AppCompatActivity {

LinearLayout lytSearchClinic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchclinic);
lytSearchClinic=findViewById(R.id.lyt_search);
lytSearchClinic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i = new Intent(SearchClinicActivity.this, ClinicListActivity.class);
        startActivity(i);
    }
});
    }
}