package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ReleaseModel
{
    @SerializedName("country")
    public String country;
    
    @SerializedName("title")
    public String title;
    
    @SerializedName("date")
    public DateModel date;
    
    @SerializedName("mediums")
    public List<MediumModel> mediums = new ArrayList<MediumModel>();
    
    @SerializedName("id")
    public String id;
    
    public ReleaseModel(){}
}
