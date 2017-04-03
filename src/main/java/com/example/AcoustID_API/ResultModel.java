package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ResultModel
{   
    @SerializedName("recordings")
    public List<RecordingModel> recordings = new ArrayList<RecordingModel>();
    
    @SerializedName("score")
    public String score;
    
    @SerializedName("id")
    public String id;
    
    public ResultModel(){}
}