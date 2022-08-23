package com.test_jsoup.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class KissAsianActivity extends AppCompatActivity {
    String BASE_URL ="https://kissasian.li";
    String TAG ="KissAsianActivityTAG";
    TextView textView;
    ImageView imageView;
    RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kiss_asian);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
//        new  ParsePageTask2().execute("https://kissasian.li/Drama/Love-All-Play/Episode-2?id=68898");
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new  ParsePageTask().execute(BASE_URL+"/Drama/Room-No-9");

    }
    class ParsePageTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementById("containerRoot")
                        .select("div#container");
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Document document = Jsoup.parse(result);
            Element rightside = document.getElementById("rightside") ;
            String cover = rightside.select("div.barContent").select("img").attr("src");
            Glide.with(KissAsianActivity.this).load(BASE_URL+cover)
                    .into(imageView);
            Element leftside = document.getElementById("leftside") ;
            Elements info = leftside.select("div.bigBarContainer").first().select("div.barContent");
            info.select("span#spanBookmark").remove();

            textView.setText(Html.fromHtml(info.toString()));
            Elements episode = leftside.select("div.bigBarContainer").get(1).
                    select("div.episodeList").select("table.listing").select("tbody")
                    .select("tr");
            ArrayList<Model> list = new ArrayList<>();
            for (int i = 0; i <episode.size() ; i++) {
                if (i>1){
                    String title = episode.get(i).select("td").first().text();
                    String link = episode.get(i).select("td").first().select("a").attr("href");
                    list.add(new Model(title,link));
                }
            }
            recyclerView.setAdapter(new rv_adapter(list));

            Log.d(TAG, "onPostExecute: "+ episode);
        }
    }

    class ParsePageTask2 extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementById("containerRoot")
                        .select("div#container");
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Document document = Jsoup.parse(result);
            Elements elements = document.getElementById("centerDivVideo")
                    .select("iframe");
            Log.d(TAG, "onPostExecute: "+ elements.attr("src"));
            String link =  elements.attr("src").replace("https://www.gaobook.review/v/","");
            progressDialog.show();
            getData(link);
        }
    }

    class rv_adapter extends RecyclerView.Adapter<rv_adapter.ViewHolder>{
        ArrayList<Model> list;
        public rv_adapter(ArrayList<Model> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public rv_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(KissAsianActivity.this).inflate(R.layout.list, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull rv_adapter.ViewHolder holder, int position) {
            holder.txtTitle.setText(list.get(position).title);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new  ParsePageTask2().execute(BASE_URL+list.get(position).link);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView txtTitle;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                txtTitle = itemView.findViewById(R.id.txtTitle);
            }
        }
    }

    private Retrofit retrofit;
    void getData(String keyword){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        retrofit = new Retrofit.Builder().baseUrl("https://www.gaobook.review/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build();

        retrofit.create(GetService.class).get(keyword).enqueue(new Callback<ResponseParse>() {
            @Override
            public void onResponse(Call<ResponseParse> call, Response<ResponseParse> response) {
                if (response.code()==200){
                    progressDialog.dismiss();
                    ResponseParse res = response.body();
                    Log.d(TAG, "onResponse: "+res.getData().get(0).getFile());
                    Intent intent = new Intent(KissAsianActivity.this, PlayActivity.class);
                    intent.putExtra("linkVideo",res.getData().get(0).getFile() );
                    startActivity(intent);
                }
                Log.d(TAG, "onResponse: "+response.code());

            }

            @Override
            public void onFailure(Call<ResponseParse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

    interface GetService {
        @POST("source/{keyword}")
        Call<ResponseParse> get( @Path("keyword") String keyword);
    }
}