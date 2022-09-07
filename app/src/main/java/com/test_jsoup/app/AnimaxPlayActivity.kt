package com.test_jsoup.app

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.jsoup.Jsoup

class AnimaxPlayActivity : AppCompatActivity() {
    val TAG ="AnimaxPlayActivityTAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animax_play)
        ParsePageTask().execute("https://animenana.com/view/3850677550")

    }

    inner class ParsePageTask :
        AsyncTask<String?, Void?, String>() {
        override fun doInBackground(vararg urls: String?): String? {
            try {
                val doc = Jsoup.connect(urls[0]).get()
                val link = doc.getElementsByTag("body")
                return link.toString()
            } catch (ignored: Exception) {
            }
            return ""
        }

        override fun onPostExecute(result: String) {
            try {
                val document = Jsoup.parse(result)

                val script = document.select("script")
                for (i in 0 until script.size ){

                }


                val list = script[11].toString().split("src=\"")

                for (l in list){
                   if (l.startsWith("/redirect")) {
                        Log.d(TAG, "onPostExecute: "+l)

                    }
                }

            }catch (n :NullPointerException){
                 Log.d(TAG, "onPostExecute: "+n.message)
            }

        }
    }

}