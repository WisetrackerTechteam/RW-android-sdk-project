package com.sdk.wisetracker.app.service;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.sdk.wisetracker.app.R;

public class ServiceActivity extends Activity {

    private Intent serviceIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity);
        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.i("[TestService]", "main destroy");
        if (serviceIntent != null) {
            stopService(serviceIntent);
        }
        super.onDestroy();
    }

    private void startService() {
        try {
            Intent serviceIntent = new Intent(this, TestService.class);
//            if (isServiceRunning(TestService.class)) {
//                return;
//            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serviceIntent.setAction("TEST");
                startForegroundService(serviceIntent);
            } else {
                serviceIntent.setAction("TEST");
                startService(serviceIntent);
            }
            Log.i("[TestService]", "create service call !!");
        } catch (Exception e) {
            Log.e("[TestService]", "set service error !!", e);
        }
    }

    private boolean isServiceRunning(Class<?> serviceClass) {
        try {
            /* 동작중인 서비스가 있으면 return 중복 서비스 등록 방지 위해서 */
            ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            if (activityManager == null) {
                return false;
            }
            for (ActivityManager.RunningServiceInfo service : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                String a = serviceClass.getName();
                Log.i("", "target service name : " + a);
                String b = service.service.getClassName();
                Log.i("", "running service name : " + b);
                if (serviceClass.getName().equals(service.service.getClassName())) {
                    Log.i("[TestService]", "service is running");
                    return true;
                }
            }
        } catch (Exception e) {
            Log.e("[TestService]", "is service running error !!", e);
        }
        return false;
    }


}
