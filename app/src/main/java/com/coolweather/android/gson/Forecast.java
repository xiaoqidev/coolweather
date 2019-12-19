package com.coolweather.android.gson;

import com.google.gson.annotations.SerializedName;

public class Forecast {
    @SerializedName("date")
    public String date;

    @SerializedName("cond")
    public More more;

    public class More{
        @SerializedName("txt_d")
        public String info;
    }
    @SerializedName("tmp")
    public Temperature temperature;


    public class Temperature {
        public String max;
        public String min;
    }
}
