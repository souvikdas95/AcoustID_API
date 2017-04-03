package com.example.AcoustID_API;

public class ChromaPrint
{
    private final String chromaprint;
    private final String duration;

    public ChromaPrint(String chromaprint, String duration)
    {
        this.duration = duration;
        this.chromaprint = chromaprint;
    }

    public String getChromaprint()
    {
        return chromaprint;
    }

    public String getDuration()
    {
        return duration;
    }
}