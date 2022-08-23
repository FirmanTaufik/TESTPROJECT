package com.test_jsoup.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainActivity3 extends AppCompatActivity {
    private String TAG = "MainActivity3TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        new ParsePageTask2().execute("https://mangalink.io/manga/terror-man/");

    }

    class ParsePageTask2 extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementsByClass("wrap")
                        .select("div.site-content");
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Document document = Jsoup.parse(result);
            Elements elements = document.getElementsByClass("c-page-content")
                    .select("div.main-col");
            Log.d(TAG, "onPostExecute: "+ elements.toString());
        }
    }
}