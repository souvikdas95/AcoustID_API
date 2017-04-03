package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class TrackModel
{
    @SerializedName("artists")
    public List<ArtistModel> artists = new ArrayList<ArtistModel>();
    
    @SerializedName("id")
    public String id;
    
    @SerializedName("title")
    public String title;
    
    public TrackModel(){}
}
