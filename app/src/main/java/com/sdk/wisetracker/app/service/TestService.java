package com.sdk.wisetracker.app.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.sdk.wisetracker.app.R;

public class TestService extends Service {

    private final String TAG = "[TestService]";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String thread = Thread.currentThread().getName();
        Log.d(TAG, thread);
        Log.i(TAG, "service create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String thread = Thread.currentThread().getName();
        Log.d(TAG, thread);
        Log.i(TAG, "on start command");
        Log.i(TAG, "action : " + intent.getAction());
        setNotification();
        serviceRun();
        return START_STICKY;
    }

    private void setNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1", "name", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("desc");
            notificationChannel.setSound(null, null);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
            Notification.Builder builder = new Notification.Builder(this, "1")
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText("백그라운드 동작중")
                    .setAutoCancel(true);
            Notification notification = builder.build();
            startForeground(1, notification);
        }
    }

    private int i = 0;
    private boolean flag = true;

    private void serviceRun() {
        try {
            String name = Thread.currentThread().getName();
            Log.d(TAG, "service thread name : " + name);
            Thread thread = new Thread() {
                @Override
                public void run() {
                    String name = Thread.currentThread().getName();
                    Log.d(TAG, "run thread name : " + name);
                    while (flag) {
                        try {
                            i++;
                            Log.i(TAG, "current thread name : " + Thread.currentThread().getName());
                            if ((i % 10) == 0) {
                                Log.i(TAG, "run activity : " + i);
                                Intent intent = new Intent(TestService.this, ServiceActivity.class);
                                intent.putExtra(ServiceActivity.class.getSimpleName(), "count : " + i);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            Log.i(TAG, "i : " + i);
                            Thread.sleep(1000);
                        } catch (Exception e) {
                            Log.e(TAG, "thread error !!", e);
                        }
                    }
                }
            };
            thread.start();
        } catch (Exception e) {
            Log.e(TAG, "test service error !!", e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "service destroy");
        flag = false;
        Intent broadcastIntent = new Intent(this, NewServiceBroadCastReceiver.class); // send broad cast
        broadcastIntent.setAction("android.intent.action.NEW_SERVICE_CREATE");
        sendBroadcast(broadcastIntent);
    }

}
