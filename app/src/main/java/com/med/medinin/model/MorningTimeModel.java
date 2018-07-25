package com.med.medinin.model;

/**
 * Created by NEHA on 5/3/2018.
 */

public class MorningTimeModel {

    private String mtimeslot;

    public MorningTimeModel(String mtimeslot) {
        this.mtimeslot = mtimeslot;
    }

    public String getName() {
        return mtimeslot;
    }

    public void setName(String mtimeslot) {
        this.mtimeslot = mtimeslot;
    }
}

