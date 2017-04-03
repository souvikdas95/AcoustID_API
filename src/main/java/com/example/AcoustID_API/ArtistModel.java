package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;

public class ArtistModel
{
    @SerializedName("joinphrase")
    public String joinphrase;
    
    @SerializedName("id")
    public String id;
    
    @SerializedName("name")
    public String name;
    
    public ArtistModel(){}
}
