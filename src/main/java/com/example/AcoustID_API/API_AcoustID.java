package com.example.AcoustID_API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class API_AcoustID
{
    private static final String ENDPOINT_URL = "http://api.acoustid.org";
    private static final String ENDPOINT_LOOKUP_GET = "/v2/lookup";
    
    private final String szfpCalc;
    private final String szAPIkey;
    
    public API_AcoustID(String szfpCalc, String szAPIkey)
    {
        this.szfpCalc = szfpCalc;   // directory to fpcalc
        this.szAPIkey = szAPIkey;
    }
    
    public ChromaPrint chromaprint(String szFile)
    {
        try
        {
            ProcessBuilder processBuilder = new ProcessBuilder(this.szfpCalc, null);
            processBuilder.redirectErrorStream(true);
            processBuilder.command().set(1, szFile);
            Process fpcalcProc = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(fpcalcProc.getInputStream()));
            String line;
            String chromaprint = null;
            String duration = null;
            while ((line = br.readLine()) != null)
            {
                if (line.startsWith("FINGERPRINT="))
                {
                    chromaprint = line.substring("FINGERPRINT=".length());
                }
                else if (line.startsWith("DURATION="))
                {
                    duration = line.substring("DURATION=".length());
                }
            }
            System.out.println(chromaprint + "\n" + duration);
            return new ChromaPrint(chromaprint, duration);
        }
        catch(IOException ex)
        {
            Logger.getLogger(API_AcoustID.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private static ResultModel getBestResult(ResponseModel resp)
    {
        if (resp.results.size() > 0)
        {
            ResultModel bestResult = resp.results.get(0);
            double currentScore = Double.parseDouble(bestResult.score);
            for (final ResultModel result : resp.results)
            {
                final double score = Double.parseDouble(result.score);
                if (score > currentScore)
                {
                    bestResult = result;
                    currentScore = score;
                }
            }
            return bestResult;
        }
        else
        {
            return null;
        }
    }

    private static ResponseModel getResponse(String json)
    {
        final Gson gson = new Gson();
        final ResponseModel results = gson.fromJson(json, ResponseModel.class);
        return results;
    }

    public ResultModel lookup(ChromaPrint chromaprint)
    {
        try
        {
            if(this.szAPIkey == null || chromaprint == null)
                return null;
            URL url = new URL(ENDPOINT_URL + ENDPOINT_LOOKUP_GET + "?" +
                              "client=" + this.szAPIkey + "&" + 
                              "meta=" + "recordings+releases+releasegroups+tracks" + "&" + 
                              "fingerprint=" + chromaprint.getChromaprint() + "&" +
                              "duration=" + chromaprint.getDuration());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if(conn.getResponseCode() != HttpURLConnection.HTTP_OK)
                return null;
            BufferedReader rr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = rr.read(chars)) != -1)
                buffer.append(chars, 0, read);
            String szJSON = buffer.toString();
            System.out.println(szJSON);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            JsonObject jo = new JsonParser().parse(szJSON).getAsJsonObject();
            final ResponseModel resp = gson.fromJson(jo, ResponseModel.class);
            if (resp.status.compareTo("ok") == 0)
            {
                ResultModel bestResult = getBestResult(resp);
                if (bestResult != null && bestResult.recordings.size() > 0)
                    return bestResult;
            }
            return null;
        }
        catch(IOException ex)
        {
            Logger.getLogger(API_AcoustID.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
