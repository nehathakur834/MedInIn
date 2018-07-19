package com.med.medinin.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.med.medinin.R;
import com.med.medinin.activities.ConfirmAppointActivity;

/**
 * Created by NEHA on 1/10/2018.
 */

public class TimeSlotFragment extends Fragment {
    private View view;
    TextView tvMornOne,tvMornTwo,tvMornThree;
    TextView tvEvenOne,tvEvenTwo,tvEvenThree;
    TextView tvAftrOne,tvAftrTwo,tvAftrThree,tvAftrFour,tvAftrFive,tvAftrSix;
    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_timeslot, container, false);
        tvMornOne=view.findViewById(R.id.id_morn_one);
        tvMornTwo=view.findViewById(R.id.id_morn_two);
        tvMornThree=view.findViewById(R.id.id_morn_three);
        tvMornOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvMornTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvMornThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvEvenOne=view.findViewById(R.id.id_even_one);
        tvEvenTwo=view.findViewById(R.id.id_two_one);
        tvEvenThree=view.findViewById(R.id.id_three_one);
        tvEvenOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvEvenTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvEvenThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrOne=view.findViewById(R.id.id_aftrn_one);
        tvAftrTwo=view.findViewById(R.id.id_aftrn_two);
        tvAftrThree=view.findViewById(R.id.id_aftrn_three);
        tvAftrFour=view.findViewById(R.id.id_aftrn_four);
        tvAftrFive=view.findViewById(R.id.id_aftrn_five);
        tvAftrSix=view.findViewById(R.id.id_aftrn_six);
        tvAftrOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvAftrTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        tvAftrFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });
        tvAftrSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ConfirmAppointActivity.class);
                startActivity(i);
            }
        });

        return view;
    }




}