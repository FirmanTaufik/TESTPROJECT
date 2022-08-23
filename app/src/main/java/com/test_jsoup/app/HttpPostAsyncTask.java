package com.test_jsoup.app;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpPostAsyncTask extends AsyncTask<String, Void, String> {
    String TAG ="HttpPostAsyncTaskTAG";
    private CallBack callBack;
    public interface CallBack {
        void OnChange(String position);
    }

    public void setCallBack(CallBack callBack) {
        this.callBack = callBack;
    }

    // This is the JSON body of the post
    JSONObject postData;
    // This is a constructor that allows you to pass in the JSON body
    public HttpPostAsyncTask(Map<String, String> postData) {
        if (postData != null) {
            this.postData = new JSONObject(postData);
        }

    }

    // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
    @Override
    protected String doInBackground(String... params) {

        try {
            // This is getting the url from the string we passed in
            URL url = new URL(params[0]);

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
          //  urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestMethod("POST");


            // OPTIONAL - Sets an authorization header
          //  urlConnection.setRequestProperty("Authorization", "someAuthString");

            // Send the post body
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
                writer.close();
            }
            Log.d(TAG, "doInBackground: "+postData.toString());
            int statusCode = urlConnection.getResponseCode();

            if (statusCode ==  200) {

                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

                return convertInputStreamToString(inputStream);

                // From here you can convert the string to JSON with whatever JSON parser you like to use
                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method
            }

        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.d(TAG, "onPostExecute: "+response);
                if (callBack!=null){
                    callBack.OnChange(response);
                }
    }

    private String convertInputStreamToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}