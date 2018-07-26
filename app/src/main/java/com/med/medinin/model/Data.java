package com.med.medinin.model;

import java.util.List;

public class Data {
    private String status;

    private String booking_date;

    private String ID;

    private String booking_time;

    private Hospital hospital;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getBooking_date ()
    {
        return booking_date;
    }

    public void setBooking_date (String booking_date)
    {
        this.booking_date = booking_date;
    }

    public String getID ()
    {
        return ID;
    }

    public void setID (String ID)
    {
        this.ID = ID;
    }

    public String getBooking_time ()
    {
        return booking_time;
    }

    public void setBooking_time (String booking_time)
    {
        this.booking_time = booking_time;
    }

    public Hospital getHospital ()
    {
        return hospital;
    }

    public void setHospital (Hospital hospital)
    {
        this.hospital = hospital;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", booking_date = "+booking_date+", ID = "+ID+", booking_time = "+booking_time+", hospital = "+hospital+"]";
    }
}
