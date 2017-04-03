package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class RecordingModel
{  
    @SerializedName("artists")
    public List<ArtistModel> artists = new ArrayList<ArtistModel>();
    
    @SerializedName("duration")
    public long duration;
    
    @SerializedName("releasegroups")
    public List<ReleaseGroupModel> releasegroups = new ArrayList<ReleaseGroupModel>();
    
    @SerializedName("title")
    public String title;
    
    @SerializedName("id")
    public String id;

    public RecordingModel(){}
}