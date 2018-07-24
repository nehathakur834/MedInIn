package com.med.medinin.model;

public class DataModel {
/*    private int image;
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
    }*/

    private String Name;

    private String ID;

    public String getName ()
    {
        return Name;
    }

    public void setName (String Name)
    {
        this.Name = Name;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Name = "+Name+", ID = "+ID+"]";
    }
}
