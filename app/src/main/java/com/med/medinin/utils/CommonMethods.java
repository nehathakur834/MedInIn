package com.med.medinin.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

/**
 * Created by NEHA on 10/14/2017.
 */

public class CommonMethods {


    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static final String myPref = "mypref";
    /*SharedPreferences keys*/
    public static final  String DEPARTMENT_ID_FIELD = "deprtId";
    public static final  String DEPARTMENT_NAME_FIELD = "deprtName";
    public static final  String HOSPITAL_ID_FIELD = "hospitalId";
    public static final  String HOSPITAL_NAME_FIELD = "hosptName";
    public static final  String HOSPITAL_ADDRESS_FIELD = "hosptAddress";
    public static final  String TIME_SLOT_FIELD = "timeslot";

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


}
