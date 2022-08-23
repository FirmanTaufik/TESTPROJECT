package com.test_jsoup.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity5 extends AppCompatActivity {
    private String TAG ="MainActivity5TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        new  ParsePageTask2().execute("https://www.readlightnovel.me/madam-is-a-sensational-figure-in-the-city/chapter-721");

    }


    class ParsePageTask2 extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementsByClass("container--content") ;
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Document document = Jsoup.parse(result);
            Elements elements = document.getElementsByClass("col-lg-8 content2").select("div.chapter-content3").select("div.desc")  ;
            elements.select("div#pod-spot").remove();
            Log.d(TAG, "onPostExecute: "+ elements.toString());
        }
    }
}