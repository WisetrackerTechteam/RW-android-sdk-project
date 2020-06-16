package com.sdk.wisetracker.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.sdk.wisetracker.new_dot.open.DOT;

import java.util.Set;

public class LinkActivity extends Activity {

    private final String TAG = "LinkActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        params.dimAmount = 0.5f;
        getWindow().setAttributes(params);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.link_activity);
        checkLink();
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void checkLink() {
        Uri data = this.getIntent().getData();
        if (data != null && data.isHierarchical()) {
            String uri = this.getIntent().getDataString();
            Log.i("wisetracker", "intent data : " + uri);
        }
        Uri referrer = getReferrer();
        if (referrer != null) {
            String referrerString = getReferrer().toString();
            Log.i("wisetracker", "intent extra data (REFERRER) : " + referrer);
        }
        DOT.setDeepLink(getIntent());
        printAllExtraIntent();
    }

    @Override
    public Uri getReferrer() {

        // There is a built in function available from SDK>=22
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return super.getReferrer();
        }

        Intent intent = getIntent();
        Uri referrer = (Uri) intent.getExtras().get("android.intent.extra.REFERRER");
        if (referrer != null) {
            return referrer;
        }

        String referrerName = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (referrerName != null) {
            try {
                return Uri.parse(referrerName);
            } catch (Exception e) {
                // ...
            }
        }

        return null;

    }

    private void printAllExtraIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            return;
        }
        Set<String> keys = bundle.keySet();
        if (keys == null) {
            return;
        }
        for (String key : keys) {
            Log.i("wisetracker", "그 외의 intent extra data 의 key 값 :" + key);
            try {
                Log.i("wisetracker", "그 외의 intent extra data 의 value 값 :" + bundle.get(key));
            } catch (Exception e) {
                Log.i("wisetracker", "exception !!", e);
            }
        }
    }

}
