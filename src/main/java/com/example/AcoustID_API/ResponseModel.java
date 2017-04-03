package com.example.AcoustID_API;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class ResponseModel
{
    @SerializedName("status")
    public String status;
    
    @SerializedName("results")
    public List<ResultModel> results = new ArrayList<ResultModel>();
    
    public ResponseModel(){}
}