package com.example.Sample;

import com.example.AcoustID_API.*;

public class Sample
{
    public static void main(String[] args)
    {
        String fpcalc_f = "<Absolute path to fpcalc executable>";
        String szKey = "<API Key>";
        String szSongFile = "<Song file location to lookup for>";
        API_AcoustID obj = new API_AcoustID(fpcalc_f, szKey);
        final ChromaPrint cm = obj.chromaprint(szSongFile);
        final ResultModel rm = obj.lookup(cm);
        if(rm != null)
        {
            System.out.println(
                                rm.recordings.get(0).title + "\n" +
                                "\t" + rm.recordings.get(0).releasegroups.get(0).title + "\n" +
                                "\t" + "\t" + rm.recordings.get(0).releasegroups.get(0).releases.get(0).title + "\n" +
                                "\t" + "\t" + "\t" + rm.recordings.get(0).releasegroups.get(0).releases.get(0).mediums.get(0).tracks.get(0).title + "\n" +
                                "\t" + "\t" + "\t" + "\t" + rm.recordings.get(0).releasegroups.get(0).releases.get(0).mediums.get(0).tracks.get(0).artists.get(0).name
                               );
        }
    }
}
