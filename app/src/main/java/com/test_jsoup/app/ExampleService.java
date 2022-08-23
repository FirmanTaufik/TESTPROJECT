package com.test_jsoup.app;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.test_jsoup.app.App.CHANNEL_ID;

public class ExampleService extends Service {
    private static final String TAG = "ExampleServiceTAG";

    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        if (input.equals("stop")) {
            stopForeground(true);
            stopSelf();
        }

      //  testmet();
        checkApp();
        //do heavy work on a background thread
        //stopSelf();
        return START_NOT_STICKY;
    }

    private void testmet() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                testmet();
                for (ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                    //                        String packageName = recentTask..getComponent().getPackageName();
//                        ApplicationInfo appInfo = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
//                        String label = getPackageManager().getApplicationLabel(appInfo).toString();
                    Log.i("APPSSSSSSSSSS", processInfo.processName);

                }
            }
        }, 1500);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void checkApp(){
        UsageStatsManager usm = (UsageStatsManager) this.getSystemService(USAGE_STATS_SERVICE);
        List<UsageStats> appList = usm.queryUsageStats(UsageStatsManager.INTERVAL_DAILY,  System.currentTimeMillis() - 1000*3600*24,  System.currentTimeMillis());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            appList = appList.stream().filter(app -> app.getTotalTimeInForeground() > 0).collect(Collectors.toList());
        }

        // Group the usageStats by application and sort them by total time in foreground
        if (appList.size() > 0) {
            Map<String, UsageStats> mySortedMap = new TreeMap<>();
            for (UsageStats usageStats : appList) {
                mySortedMap.put(usageStats.getPackageName(), usageStats);
            }
            showAppsUsage(mySortedMap);
        }
    }


    public void showAppsUsage(Map<String, UsageStats> mySortedMap) {
        //public void showAppsUsage(List<UsageStats> usageStatsList) {
        //ArrayList<App> appsList = new ArrayList<>();
        List<UsageStats> usageStatsList = new ArrayList<>(mySortedMap.values());

        // sort the applications by time spent in foreground
        Collections.sort(usageStatsList, (z1, z2) -> Long.compare(z1.getTotalTimeInForeground(), z2.getTotalTimeInForeground()));

        // get total time of apps usage to calculate the usagePercentage for each app
        long totalTime = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            totalTime = usageStatsList.stream().map(UsageStats::getTotalTimeInForeground).mapToLong(Long::longValue).sum();
        }

        //fill the appsList
        for (UsageStats usageStats : usageStatsList) {
            try {
                String packageName = usageStats.getPackageName();
              //  Drawable icon = getDrawable(R.drawable.no_image);
                Drawable icon = null;
                        String[] packageNames = packageName.split("\\.");
                String appName = packageNames[packageNames.length-1].trim();


                if(isAppInfoAvailable(usageStats)){
                    ApplicationInfo ai = getApplicationContext().getPackageManager().getApplicationInfo(packageName, 0);
                    icon = getApplicationContext().getPackageManager().getApplicationIcon(ai);
                    appName = getApplicationContext().getPackageManager().getApplicationLabel(ai).toString();
                }

                String usageDuration = getDurationBreakdown(usageStats.getTotalTimeInForeground());
                int usagePercentage = (int) (usageStats.getTotalTimeInForeground() * 100 / totalTime);
                Log.d(TAG, "showAppsUsage: "+appName +" "+usagePercentage +" "+usageDuration);
               // App usageStatDTO = new App(icon, appName, usagePercentage, usageDuration);
                //appsList.add(usageStatDTO);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }


        // reverse the list to get most usage first
       // Collections.reverse(appsList);
        // build the adapter
//        AppsAdapter adapter = new AppsAdapter(this, appsList);
//
//        // attach the adapter to a ListView
//        ListView listView = findViewById(R.id.apps_list);
//        listView.setAdapter(adapter);

       // showHideItemsWhenShowApps();
    }
    private boolean isAppInfoAvailable(UsageStats usageStats) {
        try {
            getApplicationContext().getPackageManager().getApplicationInfo(usageStats.getPackageName(), 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
    private String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        millis -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        return (hours + " h " +  minutes + " m " + seconds + " s");
    }

}
