package com.sdk.wisetracker.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.sdk.wisetracker.dox.open.api.DOX;
import com.sdk.wisetracker.dox.open.model.XConversion;
import com.sdk.wisetracker.dox.open.model.XEvent;
import com.sdk.wisetracker.dox.open.model.XIdentify;
import com.sdk.wisetracker.dox.open.model.XProduct;
import com.sdk.wisetracker.dox.open.model.XProperties;
import com.sdk.wisetracker.dox.open.model.XPurchase;
import com.sdk.wisetracker.new_dot.open.DOT;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        logScreen();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DOT.onStopPage(this);
    }

    private void setData() {

        TextView click = findViewById(R.id.dot_click);
        click.setOnClickListener(v -> {
            logClick();
            Toast.makeText(this, "Click 발생", Toast.LENGTH_SHORT).show();
        });

        TextView conversion = findViewById(R.id.dot_conversion);
        conversion.setOnClickListener(v -> {
            logEvent();
            Toast.makeText(this, "Conversion 발생", Toast.LENGTH_SHORT).show();
        });

        TextView purchase = findViewById(R.id.dot_purchase);
        purchase.setOnClickListener(v -> {
            logPurchase();
            Toast.makeText(this, "Purchase 발생", Toast.LENGTH_SHORT).show();
        });

        TextView page = findViewById(R.id.dot_page);
        page.setOnClickListener(v -> {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        TextView xConversion = findViewById(R.id.dox_conversion);
        xConversion.setOnClickListener(v -> {
            logXConversion();
            Toast.makeText(this, "X_Conversion 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xRevenue = findViewById(R.id.dox_revenue);
        xRevenue.setOnClickListener(v -> {
            logXPurchase();
            Toast.makeText(this, "X_Revenue 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xEvent = findViewById(R.id.dox_event);
        xEvent.setOnClickListener(v -> {
            logXEvent();
            Toast.makeText(this, "X_Event 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xUserIdentify = findViewById(R.id.dox_user_identify);
        xUserIdentify.setOnClickListener(v -> {
            logUserIdentify();
            Toast.makeText(this, "UserIdentify 발생", Toast.LENGTH_SHORT).show();
        });

        TextView xGroupIdentify = findViewById(R.id.dox_group_identify);
        xGroupIdentify.setOnClickListener(v -> {
            logGroupIdentiy();
        });

        TextView webView = findViewById(R.id.web_view);
        webView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
            startActivity(intent);
        });

    }

    private void logEvent() {
        Map<String, Object> map = new HashMap<>();
        map.put("g1", "goal 1");
        map.put("g2", "goal 2");
        map.put("g3", "goal 3");
        map.put("g4", "goal 4");
        map.put("g5", "goal 5");
        map.put("g6", "goal 6");
        map.put("g7", "goal 7");
        map.put("g8", "goal 8");
        map.put("g9", "goal 9");
        map.put("910", "goal 10");
        DOT.logEvent(map);
    }

    private void logClick() {
        Map<String, Object> map = new HashMap<>();
        map.put("ckTp", "click type");
        map.put("ckData", "click data");
        map.put("mvt1", "event mvt 1");
        map.put("mvt2", "event mvt 2");
        map.put("mvt3", "event mvt 3");
        map.put("mvt4", "event mvt 4");
        map.put("mvt5", "event mvt 5");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderNo", "ORD001");
        jsonObject.addProperty("currency", "KRW");
        jsonObject.addProperty("pg1", "sports");
        jsonObject.addProperty("pg2", "fashion");
        jsonObject.addProperty("pg3", "cloth");
        jsonObject.addProperty("pnc", "EVENT001");
        jsonObject.addProperty("ordPno", "POPULARABC");
        jsonObject.addProperty("amt", "300,000");
        jsonObject.addProperty("ea", "3");
        jsonObject.addProperty("mvt1", "mvt1");
        jsonObject.addProperty("pncAtr1", "attribute 1");
        map.put("product", jsonObject.toString());
        DOT.logClick(map);
    }

    private void logPurchase() {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", "purchase");
        map.put("keywordCategory", "CAMPAIGN");
        JSONArray jsonArray = new JSONArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("orderNo", "ORD001");
        jsonObject.addProperty("currency", "KRW");
        jsonObject.addProperty("pg1", "sports");
        jsonObject.addProperty("pg2", "fashion");
        jsonObject.addProperty("pg3", "cloth");
        jsonObject.addProperty("pnc", "PNC001");
        jsonObject.addProperty("ordPno", "BESTABC");
        jsonObject.addProperty("amt", "100,000");
        jsonObject.addProperty("ea", "1");
        jsonObject.addProperty("mvt1", "mvt1");
        jsonArray.put(jsonObject);
        map.put("products", jsonArray.toString());
        DOT.logPurchase(map);
    }

    private void logScreen() {
        Map<String, Object> map = new HashMap<>();
        map.put("pi", "main.page");
        map.put("skwd", "MEMBERSHIP");
        map.put("scart", "SPORTS");
        map.put("cp", "path");
        map.put("sresult", "10");
        map.put("mvt1", "product mvt");
        DOT.logScreen(map);
    }

    private void logXConversion() {
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
    }

    private void logXEvent() {
        DOX.logEvent(new XEvent.Builder()
                .setEventName("log event")
                .setProperties(new XProperties.Builder()
                        .set("put1", "put value")
                        .build())
                .build());
    }

    private void logXPurchase() {
        DOX.logPurchase(new XPurchase.Builder()
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
    }


    private void logGroupIdentiy() {
        DOX.groupIdentify("group", "company", (new XIdentify.Builder()
                .set("group set", "1")
                .set("group set2", "2")
                .unset("group unset")
                .build()));
        Toast.makeText(this, "GroupIdentify 발생", Toast.LENGTH_SHORT).show();
    }


    private void logUserIdentify() {
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

    }

}

/*

    private void setMap() {
        try {
            Map<String, Object> map = new HashMap<>();
            JSONArray jsonArray = new JSONArray();
            JSONObject object = new JSONObject();
            object.put("data", "value");
            jsonArray.put(object);
            String a = jsonArray.toString();
            map.put("a", "data");
            map.put("product", a);
            String json = new Gson().toJson(map);

            JsonElement jsonElement = new JsonParser().parse(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            Log.d("", json);
            Log.d("", jsonObject.getAsString());
        } catch (Exception e) {

        }
    }

  DOT.onStartPage(this);
  DOT.onStopPage(this);

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

DOT.setPurchase(new Purchase.Builder()
                    .setProduct(new Product.Builder()
                            .setProductCode("product code")
                            .setFirstCategory("first category")
                            .setSecondCategory("second category")
                            .setThirdCategory("third category")
                            .setOptionalAmount1(1d)
                            .setOptionalAmount2(2d)
                            .setOrderAmount(2000)
                            .setOrderQuantity(10)
                            .build())
                    .build());

 */

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