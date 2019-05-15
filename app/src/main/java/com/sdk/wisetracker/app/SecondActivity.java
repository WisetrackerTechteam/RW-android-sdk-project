package com.sdk.wisetracker.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sdk.wisetracker.dot.open.api.DOT;
import com.sdk.wisetracker.dot.open.model.CustomValue;
import com.sdk.wisetracker.dot.open.model.Page;
import com.sdk.wisetracker.dot.open.model.Product;
import com.sdk.wisetracker.dox.open.api.DOX;
import com.sdk.wisetracker.dox.open.model.XEvent;
import com.sdk.wisetracker.dox.open.model.XProperties;

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
        DOT.setPage(new Page.Builder()
                .setContentPath("second page")
                .setCustomValue(new CustomValue.Builder()
                        .setValue1("page 1")
                        .build())
                .setIdentity("SECOND PAGE")
                .setProduct(new Product.Builder()
                        .setProductOrderNo("SECOND PRODUCT")
                        .setProductCode("PRC 002")
                        .setFirstCategory("category 2")
                        .setSecondCategory("category category 2")
                        .setDetailCategory("Sample 2")
                        .build())
                .build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DOT.onStopPage(this);
    }

}
