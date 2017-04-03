package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;

public class DateModel
{
    @SerializedName("month")
    public int month;
    
    @SerializedName("day")
    public int day;
    
    @SerializedName("year")
    public int year;
    
    public DateModel(){}
}
