package com.test_jsoup.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    private String TAG="MainActivityTAG";
    private WebView webView;
    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          webView = findViewById(R.id.webView);
          webView.loadUrl("https://indbeasiswa.com/daftar-beasiswa-2018-2019-html");
        final WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.addJavascriptInterface(new JavascriptAccessor(), "javascriptAccessor");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.evaluateJavascript(

                        "(function() { return ('<html>'+document.getElementsByTagName('article')[0].innerHTML+'</html>'); })();",
                        new ValueCallback<String>() {
                            @Override
                            public void onReceiveValue(String html) {

                                settings.setJavaScriptEnabled(false);
                                String  s ="\\u003C";
                                String hasil =html.replaceAll(getString(R.string.ganti),"<");
                                webView.loadData(hasil, "text/html", "utf-8");

                                //   Log.d("HTML", html.replaceAll(getString(R.string.ganti),"<"));
//                                webView.loadDataWithBaseURL("",html.replaceAll(getString(R.string.ganti),"<"),
//                                        "text/html", "utf-8","");
                            }
                        });
                super.onPageFinished(view, url);
            }
        }); // wouldn't work without this!

      //  new ParsePageTask2().execute("view-source:https://indbeasiswa.com/daftar-beasiswa-2018-2019-html");
    }

    private class JavascriptAccessor {
        @SuppressWarnings("unused")
        public void getYerData(String data) {
            Log.v(TAG, data);
        }
    }

    public void chapter(View view) {
       // new ParsePageTask2().execute("https://mangabox.org/manga/my-death-flags-show-no-sign-of-ending/chapter-24/");
    }
    class ParsePageTask2 extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementsByTag("html");
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            StringBuilder stringBuilder = new StringBuilder();

            Document document = Jsoup.parse(result);
            Elements elements = document.getElementsByTag("table")
                    .select("tbody").select("tr");

            for (int i=0; i <elements.size(); i++){

                String elements1 = elements.select("td.line-content").get(i).text();
                if (elements1.startsWith("<article") ||
                        elements1.startsWith("</article")){
                    stringBuilder.append(elements1);
                }

            }

            webView.setWebViewClient(new WebViewClient());
            webView.loadData(stringBuilder.toString(), "text/html", "utf-8");
        }
    }

    class ParsePageTask extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect(urls[0]).get();
                Elements link = doc.getElementsByClass("site-content").select("div.container");
                return link.toString();
            } catch (Exception ignored) {
            }

            return "";
        }

        protected void onPostExecute(String result) {
            Log.d(TAG, "onPostExecute: "+result);
            Document document = Jsoup.parse(result);
            Elements elements = document.getElementsByClass("chapters");

            Log.d(TAG, "onPostExecute: "+elements.html());

        }
    }
}