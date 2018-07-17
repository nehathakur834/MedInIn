package com.med.medinin.model;

public class DataModel {
    private int image;
    private String clinicname;



    public DataModel(Integer image, String clinicname) {
        this.image = image;
        this.clinicname = clinicname;



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
}
