package com.test_jsoup.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    String TAG = "MainActivity2TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        startService();
        subscribeToPushService();
    }



    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic(getPackageName());

        Log.d("AndroidBash", "Subscribed");
        //  Toast.makeText(MainActivity.this, "Subscribed", Toast.LENGTH_SHORT).show();

//        String token = FirebaseInstanceId.getInstance().getToken();
//
//        // Log and toast
//        if (token != null) {
//            Log.d("AndroidBash", token);
//            //   Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
//        }
    }
    public void startService( ) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", "test");
        ContextCompat.startForegroundService(this, serviceIntent);
    }
    public void stopService( ) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        stopService(serviceIntent);
    }

}