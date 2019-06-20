package com.example.adminpc.deliveryexecutiveapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.ProgressBar;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by adminpc on 18-06-2019.
 */

public class JsonLoadAndProcess {
    Activity activity;
    private String data = null;
    final OkHttpClient client = new OkHttpClient();

    JsonLoadAndProcess(Activity activity){
        this.activity = activity;
    }

    public static String loadJSONFromAsset(Activity activity, String filename){
        String json = null;
        try{
            InputStream is = activity.getAssets().open(filename);

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

        }catch (IOException ex){
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setData(String s){
        this.data = s;
    }

    public String returnData(){
        return data;
    }



    class JsonTaskGET extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(activity,  "Verifying", "Wait for some time");
        }

        protected String doInBackground(String... params) {
          try{
              Request request = new Request.Builder()
                      .url(params[0])
                      .build();
              Call call = client.newCall(request);
              Response response = call.execute();
              return response.body().string().toString();
          }catch (IOException e){
              return null;
          }
        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            setData(result);
        }
    }


    class JsonTaskPOST extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;

        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(activity, "Verifying", "Wait for some time");
        }

        protected String doInBackground(String... params) {
            try {


                RequestBody body = RequestBody.create(
                        MediaType.parse("application/json; charset=utf-8"), params[1]);

                Request request = new Request.Builder()
                        .url(params[0])
                        .post(body)
                        .build();

                Call call = client.newCall(request);
                Response response = call.execute();
                if (response.isSuccessful()) {
                    return response.body().string().toString();
                } else {
                    return null;
                }
            } catch (IOException e) {
                return null;
            }


        }

        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            setData(result);
        }
    }

//    HttpURLConnection connection = null;
//    BufferedReader reader = null;
//
//            try {
//        URL url = new URL(params[0]);
//        connection = (HttpURLConnection) url.openConnection();
//        connection.connect();
//
//
//        InputStream stream = connection.getInputStream();
//
//        reader = new BufferedReader(new InputStreamReader(stream));
//
//        StringBuffer buffer = new StringBuffer();
//        String line = "";
//
//        while ((line = reader.readLine()) != null) {
//            buffer.append(line + "\n");
//            Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
//
//        }
//
//        return buffer.toString();
//
//
//    } catch (MalformedURLException e) {
//        e.printStackTrace();
//    } catch (IOException e) {
//        e.printStackTrace();
//    } finally {
//        if (connection != null) {
//            connection.disconnect();
//        }
//        try {
//            if (reader != null) {
//                reader.close();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//            return null;

}
