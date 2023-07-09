package com.example.adib_mohamed_safouane_exam_m1_iibdcc_23;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetIpInfoTask extends AsyncTask<String, Void, String[]> {

    private OnIpInfoResultListener listener;

    public interface OnIpInfoResultListener {
        void onResult(String[] ipInfo);
    }

    public GetIpInfoTask(OnIpInfoResultListener listener) {
        this.listener = listener;
    }

    @Override
    protected String[] doInBackground(String... params) {
        String ipAddress = params[0];
        String apiUrl = "https://ipinfo.io/" + ipAddress + "/geo";
        String[] ipInfo = null;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResponse = response.toString();
            ipInfo = parseIpInfo(jsonResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ipInfo;
    }

    @Override
    protected void onPostExecute(String[] ipInfo) {
        super.onPostExecute(ipInfo);
        if (listener != null) {
            listener.onResult(ipInfo);
        }
    }

    private String[] parseIpInfo(String jsonResponse) {


        String city = "";
        String region = "";
        String country = "";
        String loc="";


        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            city = jsonObject.getString("city");
            region = jsonObject.getString("region");
            country = jsonObject.getString("country");
            loc = jsonObject.getString("loc");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new String[]{city, region, country,loc};
    }
}
