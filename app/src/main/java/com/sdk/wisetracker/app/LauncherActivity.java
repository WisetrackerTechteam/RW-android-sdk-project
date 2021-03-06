package com.sdk.wisetracker.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.sdk.wisetracker.base.tracker.common.log.WiseLog;
import com.sdk.wisetracker.new_dot.open.DOT;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        // DOT.init(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                WiseLog.d("================= LauncherActivity 22 =========================");
                DOT.setPushClick(getApplicationContext(),getIntent());
                Intent intent = new Intent(LauncherActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                WiseLog.d("================= LauncherActivity 22 =========================");
            }
        }, 3000);
    }

}