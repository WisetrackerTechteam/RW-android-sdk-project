package com.sdk.wisetracker.app.service;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

public class NewServiceBroadCastReceiver extends BroadcastReceiver {

    private final String TAG = "[TestService]";
    private final String action = "android.intent.action.NEW_SERVICE_CREATE";

    @Override
    public void onReceive(Context context, Intent intent) {

        try {

            Log.i(TAG, "new service broadcast receiver received !!");

            if (intent == null || TextUtils.isEmpty(intent.getAction())) {
                Log.i(TAG, "intent or action is null !!");
                return;
            }

            if (!action.equals(intent.getAction())) {
                Log.i(TAG, "action is not equal !!");
                return;
            }

            if (isServiceRunning(context, TestService.class)) {
                return;
            }

            Intent serviceIntent = new Intent(context, TestService.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serviceIntent.setAction("TEST");
                context.startForegroundService(serviceIntent);
            } else {
                serviceIntent.setAction("TEST");
                context.startService(serviceIntent);
            }

        } catch (Exception e) {
            Log.e(TAG, "on receive error !!", e);
        }

    }

    private boolean isServiceRunning(Context context, Class<?> serviceClass) {

        try {

            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager == null) {
                return false;
            }

            for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                String a = serviceClass.getName();
                Log.i("", "target service name : " + a);
                String b = service.service.getClassName();
                Log.i("", "running service name : " + b);
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    Log.i(TAG, "service is running");
                    return true;
                }
            }

        } catch (Exception e) {
            Log.e(TAG, "service running check error !!", e);
        }

        return false;

    }

}
