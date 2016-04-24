package com.spaceapps.aircheck;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Daniel on 23/04/2016.
 */

public class AccessREST extends AsyncTask<HashMap<String,String>, Void, String> {

    final String URL="http://40.68.44.128:8080/insert_syntom";
    AsyncTaskListener asyncListener;
    private Exception exception;

    public AccessREST(Context appContext, AsyncTaskListener<String> asyncTaskListener) {
        this.asyncListener=asyncTaskListener;

    }

    protected String doInBackground(HashMap<String,String>... urls) {
        URL url;
        String response = "";
        try {
            url = new URL(URL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            String postData = getPostDataString(urls[0]);

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(postData);

            writer.flush();
            writer.close();
            os.close();
            int responseCode=conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                String line;
                BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line=br.readLine()) != null) {
                    response+=line;
                }
            }
            else {
                response="";

            }
            Log.d("DBG","response: "+response);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    protected void onPostExecute(String feed) {
        asyncListener.onFinish(feed);
    }


    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        Log.d("DBG","DBG");
        StringBuilder result = new StringBuilder();
        Log.d("DBG","asda");
        boolean first = true;
        Log.d("DBG",""+params.get("eyes"));
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (first)
                first = false;
            else result.append("&");

            Log.d("dasdsa","asdasd");
            Log.d("DBG",""+pair.getKey());
            result.append(URLEncoder.encode((String) pair.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode((String) pair.getValue(), "UTF-8"));
            it.remove(); // avoids a ConcurrentModificationException
        }

        Log.d("DBG",result.toString());

        return result.toString();
    }
}