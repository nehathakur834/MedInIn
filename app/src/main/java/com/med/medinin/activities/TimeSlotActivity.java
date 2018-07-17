package com.med.medinin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.med.medinin.R;

public class TimeSlotActivity extends AppCompatActivity {

    TextView tvMornOne,tvMornTwo,tvMornThree;
    TextView tvEvenOne,tvEvenTwo,tvEvenThree;
    TextView tvAftrOne,tvAftrTwo,tvAftrThree,tvAftrFour,tvAftrFive,tvAftrSix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeslot);
        tvMornOne=findViewById(R.id.id_morn_one);
        tvMornTwo=findViewById(R.id.id_morn_two);
        tvMornThree=findViewById(R.id.id_morn_three);
        tvMornOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvMornTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvMornThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvEvenOne=findViewById(R.id.id_even_one);
        tvEvenTwo=findViewById(R.id.id_two_one);
        tvEvenThree=findViewById(R.id.id_three_one);
        tvEvenOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvEvenTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvEvenThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrOne=findViewById(R.id.id_aftrn_one);
        tvAftrTwo=findViewById(R.id.id_aftrn_two);
        tvAftrThree=findViewById(R.id.id_aftrn_three);
        tvAftrFour=findViewById(R.id.id_aftrn_four);
        tvAftrFive=findViewById(R.id.id_aftrn_five);
        tvAftrSix=findViewById(R.id.id_aftrn_six);
        tvAftrOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvAftrTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvAftrFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TimeSlotActivity.this, ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
    }
}