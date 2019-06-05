package com.sdk.wisetracker.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.sdk.wisetracker.dot.open.api.DOT;
import com.sdk.wisetracker.dot.open.model.Click;
import com.sdk.wisetracker.dot.open.model.Conversion;
import com.sdk.wisetracker.dot.open.model.CustomValue;
import com.sdk.wisetracker.dot.open.model.Page;
import com.sdk.wisetracker.dot.open.model.Product;
import com.sdk.wisetracker.dot.open.model.Purchase;
import com.sdk.wisetracker.dox.open.api.DOX;
import com.sdk.wisetracker.dox.open.model.XConversion;
import com.sdk.wisetracker.dox.open.model.XEvent;
import com.sdk.wisetracker.dox.open.model.XIdentify;
import com.sdk.wisetracker.dox.open.model.XProduct;
import com.sdk.wisetracker.dox.open.model.XProperties;
import com.sdk.wisetracker.dox.open.model.XRevenue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DOT.onStartPage(this);
        DOT.setPage(new Page.Builder()
                .setContentPath("main page")
                .setCustomValue(new CustomValue.Builder()
                        .setValue1("main page 1")
                        .build())
                .setIdentity("MAIN PAGE")
                .setKeyword("main keyword")
                .setProduct(new Product.Builder()
                        .setProductOrderNo("MAIN PRODUCT")
                        .setProductCode("PRC 001")
                        .setFirstCategory("category 1")
                        .setSecondCategory("category category 1")
                        .setDetailCategory("Sample 1")
                        .setAttr1("1")
                        .build())
                .build());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DOT.onStopPage(this);
    }

    private void setData() {

        DOT.initialization(this);

        TextView click = findViewById(R.id.dot_click);
        click.setOnClickListener(v -> {
            if (v.getTag().toString() == "0") {
                v.setTag("1");
                DOT.setClick(new Click.Builder()
                        .setClickData("click data")
                        .setClickEvent("click event")
                        .setClickType("type")
                        .setCustomValue(new CustomValue.Builder()
                                .setValue1("value1")
                                .setValue2("value2")
                                .build())
                        .addCartProduct(new Product.Builder()
                                .setDetailCategory("detail click")
                                .setProductCode("click prc code")
                                .build())
                        .build());
            } else {
                v.setTag("0");
                DOT.setClick(new Click.Builder()
                        .setPushClickEvent("push click")
                        .setCustomValue(new CustomValue.Builder()
                                .setValue9("value1")
                                .setValue10("value2")
                                .build())
                        .build());
            }
            Toast.makeText(this, "Click 발생", Toast.LENGTH_SHORT).show();
        });

        TextView conversion = findViewById(R.id.dot_conversion);
        conversion.setOnClickListener(v -> {
            DOT.setConversion(new Conversion.Builder()
                    .setKeyword("conversion keyword")
                    .setProduct(new Product.Builder()
                            .setAttr1("attr1")
                            .setAttr2("attr2")
                            .setAttr3("attr3")
                            .setOptionalAmount1(11d)
                            .setOptionalAmount2(12d)
                            .build())
                    .build());
            Toast.makeText(this, "Conversion 발생", Toast.LENGTH_SHORT).show();
        });

        TextView purchase = findViewById(R.id.dot_purchase);
        purchase.setOnClickListener(v -> {
            DOT.setPurchase(new Purchase.Builder()
                    .setProduct(new Product.Builder()
                            .setProductCode("product code")
                            .setOptionalAmount1(1d)
                            .setOptionalAmount2(2d)
                            .build())
                    .build());
            Toast.makeText(this, "Purchase 발생", Toast.LENGTH_SHORT).show();
        });

        TextView page = findViewById(R.id.dot_page);
        page.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        TextView xConversion = findViewById(R.id.dox_conversion);
        xConversion.setOnClickListener(v -> {

            List<String> list = new ArrayList<>();
            list.add("list 1");
            list.add("list 2");
            list.add("list 3");

            DOX.logConversion(
                    new XConversion.Builder()
                            .setEventName("Conversion")
                            .setProperties(
                                    new XProperties.Builder()
                                            .set("item1", "value")
                                            .set("item2", new XProperties.Builder()
                                                    .set("2 depth", 2)
                                                    .set("item3", new XProperties.Builder()
                                                            .set("3 depth", 3)
                                                            .set("3 depth list", list)
                                                            .set("3 depth value", 3)
                                                            .build()))
                                            .build())
                            .build());

            Toast.makeText(this, "X_Conversion 발생", Toast.LENGTH_SHORT).show();

        });

        TextView xRevenue = findViewById(R.id.dox_revenue);
        xRevenue.setOnClickListener(v -> {
            DOX.logRevenue(new XRevenue.Builder()
                    .setRevenueType("revenue")
                    .setCurrency("krw")
                    .setOrderNo("ordno")
                    .setProduct(new XProduct.Builder()
                            .setProductCode("prc code")
                            .setProductOrderNo("prc ord no")
                            .setDetailCategory("detail")
                            .setProperties(new XProperties.Builder()
                                    .set("pr1", "pr1")
                                    .set("pr2", new XProperties.Builder()
                                            .set("pr2 deptch", "1")
                                            .build())
                                    .build())
                            .build())
                    .setProperties(new XProperties.Builder()
                            .set("revenue", "1")
                            .set("revenue2", new XProperties.Builder()
                                    .set("key1", "key1")
                                    .build())
                            .build())
                    .build());
            Toast.makeText(this, "X_Revenue 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xEvent = findViewById(R.id.dox_event);
        xEvent.setOnClickListener(v -> {
            DOX.logEvent(new XEvent.Builder()
                    .setEventName("log event")
                    .setProperties(new XProperties.Builder()
                            .set("put1", "put value")
                            .build())
                    .build());
            Toast.makeText(this, "X_Event 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xUserIdentify = findViewById(R.id.dox_user_identify);
        xUserIdentify.setOnClickListener(v -> {
            DOX.userIdentify(new XIdentify.Builder()
                    .set("set", "\"\n1\"")
                    .set("set2", "2")
                    .set("add2", new XProperties.Builder()
                            .set("key1", "key1")
                            .set("key2", new XProperties.Builder()
                                    .set("key2", "key2")
                                    .build())
                            .build())
                    .unset("unset")
                    .unset("unset2")
                    .add("add1", 1)
                    .add("add2", 12)
                    .add("add3", 123)
                    .add("add4", 1234)
                    .build());
            Toast.makeText(this, "UserIdentify 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xGroupIdentify = findViewById(R.id.dox_group_identify);
        xGroupIdentify.setOnClickListener(v -> {
            DOX.groupIdentify("group", "company", (new XIdentify.Builder()
                    .set("group set", "1")
                    .set("group set2", "2")
                    .unset("group unset")
                    .build()));
            Toast.makeText(this, "GroupIdentify 발생", Toast.LENGTH_SHORT).show();
        });

        TextView webView = findViewById(R.id.web_view);
        webView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        });

    }

}

/*private String getAddress() {
        try {
            for (Enumeration enumeration = NetworkInterface.getNetworkInterfaces(); enumeration.hasMoreElements(); ) {
                NetworkInterface networkInterface = (NetworkInterface) enumeration.nextElement();
                for (Enumeration enumIpAddress = networkInterface.getInetAddresses(); enumIpAddress.hasMoreElements(); ) {
                    InetAddress inetAddress = (InetAddress) enumIpAddress.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        if (inetAddress instanceof Inet4Address) {
                            return inetAddress.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return "";
    }*/