package com.med.medinin.model;

/**
 * Created by NEHA on 5/3/2018.
 */

public class AppointListModel {

    private String clinicname;
    private String address;


    public AppointListModel( String clinicname, String address) {

        this.clinicname = clinicname;
        this.address = address;


    }


    public String getName() {
        return clinicname;
    }

    public void setName(String clinicname) {
        this.clinicname = clinicname;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

