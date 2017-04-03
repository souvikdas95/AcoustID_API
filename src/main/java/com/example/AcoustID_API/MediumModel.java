package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class MediumModel
{
    @SerializedName("tracks")
    public List<TrackModel> tracks = new ArrayList<TrackModel>();
    
    @SerializedName("format")
    public String format;
    
    public MediumModel(){}
}
