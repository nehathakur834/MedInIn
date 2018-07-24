package com.med.medinin.model;

/**
 * Created by NEHA on 5/3/2018.
 */

public class ClinicListModel {
    private String hospital_name;

    private String[] time;

    private String[] services;

    private String distance_from;

    private String total_reviews;

    private String address;

    private String ID;

    private String rating;

    private String photo;

    public String getHospital_name ()
    {
        return hospital_name;
    }

    public void setHospital_name (String hospital_name)
    {
        this.hospital_name = hospital_name;
    }

    public String[] getTime ()
    {
        return time;
    }

    public void setTime (String[] time)
    {
        this.time = time;
    }

    public String[] getServices ()
    {
        return services;
    }

    public void setServices (String[] services)
    {
        this.services = services;
    }

    public String getDistance_from ()
    {
        return distance_from;
    }

    public void setDistance_from (String distance_from)
    {
        this.distance_from = distance_from;
    }

    public String getTotal_reviews ()
    {
        return total_reviews;
    }

    public void setTotal_reviews (String total_reviews)
    {
        this.total_reviews = total_reviews;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getRating ()
    {
        return rating;
    }

    public void setRating (String rating)
    {
        this.rating = rating;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hospital_name = "+hospital_name+", time = "+time+", services = "+services+", distance_from = "+distance_from+", total_reviews = "+total_reviews+", address = "+address+", ID = "+ID+", rating = "+rating+", photo = "+photo+"]";
    }

}

