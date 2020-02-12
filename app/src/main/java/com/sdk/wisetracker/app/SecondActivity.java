package com.sdk.wisetracker.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sdk.wisetracker.new_dot.open.DOT;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DOT.onStartPage(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DOT.onStopPage(this);
    }

}
