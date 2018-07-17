package com.med.medinin.model;

/**
 * Created by NEHA on 5/3/2018.
 */

public class ClinicListModel {
    private int image;
    private String clinicname;
    private String address;


    public ClinicListModel(Integer image, String clinicname, String address) {
        this.image = image;
        this.clinicname = clinicname;
        this.address = address;


    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
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

