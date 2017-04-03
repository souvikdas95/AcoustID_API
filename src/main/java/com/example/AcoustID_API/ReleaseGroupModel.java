package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ReleaseGroupModel
{
    @SerializedName("releases")
    public List<ReleaseModel> releases = new ArrayList<ReleaseModel>();
    
    @SerializedName("title")
    public String title;
    
    @SerializedName("type")
    public String type;
    
    @SerializedName("id")
    public String id;
    
    public ReleaseGroupModel(){}
}
