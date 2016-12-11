package com.example.lenovo.available;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getDataTest extends AsyncTask<String, String, String> {

    //    private static final String BASE_URL = "http://173.255.238.139:3030/api/1.0/artists";
    HttpURLConnection urlConnection;
    JsonCallBack jsonCallBack;

    public getDataTest(JsonCallBack jsonCallBack) {
        this.jsonCallBack =  jsonCallBack;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... args) {

        StringBuilder result = new StringBuilder();

        try {
            String urlstr = args[0];
            URL url = new URL(urlstr);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


        return result.toString();
    }

    @Override
    protected void onPostExecute(String result) {


        if (result != null && !result.equalsIgnoreCase("")) {
            jsonCallBack.success(result);
        } else {
            jsonCallBack.fail();
        }


    }

}
