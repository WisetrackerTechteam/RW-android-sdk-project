package com.sdk.wisetracker.app;

import android.app.Activity;
import android.os.Bundle;

import com.sdk.wisetracker.new_dot.open.DOT;

import java.util.HashMap;
import java.util.Map;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DOT.onStartPage(this);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("pi", "THIRD PAGE");
        DOT.logScreen(pageMap);
    }

}
