package com.test_jsoup.app;

import android.app.ActivityManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    Context context;
    String TAG ="MyFirebaseMessagingService";
    @Override
    public void onNewToken(String s) {
        Log.i("getToken", s);
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // remoteMessage object contains all you need ;-)
        String notificationTitle = remoteMessage.getNotification().getTitle();
        Log.i("notificationTitle",notificationTitle);
        String notificationContent = remoteMessage.getNotification().getBody();
        String notificationImage = String.valueOf(remoteMessage.getNotification().getImageUrl());
        // lets create a notification with these data
//        createNotification(notificationTitle, notificationContent, notificationImage);
//        checkApp();
        if (notificationTitle.equals("stop")) {
            stopService();

        }else {

            startService();
        }
    }

    public void stopService( ) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", "stop");
        stopService(serviceIntent);
    }

    public void startService( ) {
        Intent serviceIntent = new Intent(this, ExampleService.class);
        serviceIntent.putExtra("inputExtra", "test");
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
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
                String appName = packageNames[packageNames.length - 1].trim();


                if (isAppInfoAvailable(usageStats)) {
                    ApplicationInfo ai = getApplicationContext().getPackageManager().getApplicationInfo(packageName, 0);
                    icon = getApplicationContext().getPackageManager().getApplicationIcon(ai);
                    appName = getApplicationContext().getPackageManager().getApplicationLabel(ai).toString();
                }

                String usageDuration = getDurationBreakdown(usageStats.getTotalTimeInForeground());
                int usagePercentage = (int) (usageStats.getTotalTimeInForeground() * 100 / totalTime);
                Log.d(TAG, "showAppsUsage: " + appName + " " + usagePercentage + " " + usageDuration);
                // App usageStatDTO = new App(icon, appName, usagePercentage, usageDuration);
                //appsList.add(usageStatDTO);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
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


    private void createNotification(String notificationTitle, String notificationContent, String imageUrl){

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        notificationIntent.putExtra("from", "Notif");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0 /* Request code */, notificationIntent,
                PendingIntent.FLAG_ONE_SHOT);

        // Let's create a notification builder object
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getResources().getString(R.string.notification_channel_id));
        // Create a notificationManager object
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        // If android version is greater than 8.0 then create notification channel
        if (android.os.Build.VERSION.SDK_INT >=     android.os.Build.VERSION_CODES.O) {

            // Create a notification channel
            NotificationChannel notificationChannel = new NotificationChannel(getResources().getString(R.string.notification_channel_id),    getResources().getString(R.string.notification_channel_name),     NotificationManager.IMPORTANCE_DEFAULT);
            // Set properties to notification channel
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(getApplication().getColor(R.color.black));
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300});

            // Pass the notificationChannel object to notificationManager
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
// Set the notification parameters to the notification builder object
        builder.setContentTitle(notificationTitle)
                .setContentText(notificationContent)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setSound(defaultSoundUri)
                .setAutoCancel(true);
// Set the image for the notification
        if (imageUrl != null) {
            Bitmap bitmap = getBitmapfromUrl(imageUrl);
            builder.setStyle(
                    new NotificationCompat.BigPictureStyle()
                            .bigPicture(bitmap)
                            .bigLargeIcon(null)
            ).setLargeIcon(bitmap);
        }

        notificationManager.notify(1, builder.build());
    }
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            Log.e("awesome", "Error in getting notification image: " + e.getLocalizedMessage());
            return null;
        }
    }
}
