package com.med.medinin.weather;



import com.med.medinin.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by yarolegovich on 08.03.2017.
 */

public class WeatherStation {


    public static WeatherStation get() {
        return new WeatherStation();
    }

    private WeatherStation() {
    }

    public List<Forecast> getForecasts() {
        return Arrays.asList(
                new Forecast("General", R.drawable.icon_stethoscope, "16", Weather.PARTLY_CLOUDY),
                new Forecast("Cardiology", R.drawable.icon_stethoscope, "14", Weather.CLEAR),
                new Forecast("Respiratory", R.drawable.icon_stethoscope, "9", Weather.MOSTLY_CLOUDY),
                new Forecast("General", R.drawable.icon_stethoscope, "18", Weather.PARTLY_CLOUDY),
                new Forecast("Cardiology", R.drawable.icon_stethoscope, "6", Weather.PERIODIC_CLOUDS),
                new Forecast("Respiratory", R.drawable.icon_stethoscope, "20", Weather.CLEAR));
    }
}
