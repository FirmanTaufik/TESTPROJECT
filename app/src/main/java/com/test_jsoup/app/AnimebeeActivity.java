package com.test_jsoup.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test_jsoup.app.ModelData.ResponseParseAnimebee;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class AnimebeeActivity extends AppCompatActivity {
    String TAG ="AnimebeeActivityTAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animebee);

        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "109156");
        map.put("action", "tooltip_action");
        HttpPostAsyncTask httpPostAsyncTask = new HttpPostAsyncTask(map);
//        httpPostAsyncTask.execute("https://erzanime.com/wp-admin/admin-ajax.php");
//        httpPostAsyncTask.setCallBack(new HttpPostAsyncTask.CallBack() {
//            @Override
//            public void OnChange(String position) {
//                Log.d(TAG, "OnChange: "+position);
//            }
//        });


       // new ParsePageTask().execute ("https://animebee.to/watch/mewkledreamy-mix-go2d/episode-1.html");
        getData();
    }

    private Retrofit retrofit;
    void getData( ){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder().baseUrl("https://erzanime.com/wp-admin/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build();
        HashMap<String, String> params = new HashMap<>();
        params.put("id", "109156");
        params.put("action","tooltip_action");
        retrofit.create(GetService.class).get( params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                if (response.code()==200){
                    Log.d(TAG, "onResponse: "+response.body());
                }
                Log.d(TAG, "onResponse: "+response.code());

            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    interface GetService {
        @POST("admin-ajax.php")
        @FormUrlEncoded
        Call<String> get(@FieldMap HashMap<String, String> params);
    }

    class ParsePageTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementsByTag("body")
                        .select("div#wrapper");
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Document document = Jsoup.parse(result);
            Element scriptElements = document.getElementsByTag("script").get(1);
                for (DataNode node : scriptElements.dataNodes()) {
                    Log.d(TAG, "onPostExecute: "+node.getWholeData());
                    ResponseParseAnimebee data = new Gson().fromJson(node.getWholeData(), ResponseParseAnimebee.class);
                    for (int i = 0; i < data.getGraph().size(); i++) {

                        Log.d(TAG, "onPostExecuteData: "+data.getGraph().get(i).getVideo().getEmbedUrl());
                    }
                }


        }
    }
}