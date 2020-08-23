package com.sdk.wisetracker.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.applinks.AppLinkData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdk.wisetracker.base.open.model.InternalCampaign;
import com.sdk.wisetracker.base.tracker.common.log.WiseLog;
import com.sdk.wisetracker.base.tracker.data.model.Const;
import com.sdk.wisetracker.dox.open.api.DOX;
import com.sdk.wisetracker.dox.open.model.XConversion;
import com.sdk.wisetracker.dox.open.model.XEvent;
import com.sdk.wisetracker.dox.open.model.XIdentify;
import com.sdk.wisetracker.dox.open.model.XProduct;
import com.sdk.wisetracker.dox.open.model.XProperties;
import com.sdk.wisetracker.dox.open.model.XPurchase;
import com.sdk.wisetracker.base.open.model.User;
import com.sdk.wisetracker.new_dot.open.DOT;
import com.sdk.wisetracker.new_dot.tracker.init.ActivityDetector;
import com.sdk.wisetracker.new_dot.tracker.manager.DotManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         AppLinkData.fetchDeferredAppLinkData(getApplicationContext(), new AppLinkData.CompletionHandler() {
            @Override
            public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                // SDK 호출
                 if (appLinkData == null) {
                     DOT.receiveFailFacebookReferrer(1);
                    return ;
                }

                Bundle bundle = appLinkData.getArgumentBundle();
                if (bundle == null) {
                    DOT.receiveFailFacebookReferrer(2);
                    return;
                }
                DOT.setFacebookReferrer(bundle);
            }
        });
        setContentView(R.layout.activity_main);
        setData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        DOT.onStartPage(this);
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("pi", "pageId");
        DOT.logScreen(pageMap);
        //logScreen();
    }

    private void setData() {
        TextView click = findViewById(R.id.dot_click);
        click.setOnClickListener(v -> {
//            Map<String, Object> clickMap = new HashMap<>();
//            clickMap.put("ckTp", "click type");
//            clickMap.put("ckData", "click data");
//            DOT.logClick(clickMap);



            DOT.setUser(new User.Builder()
                    .setGender("M")
                    .setAge("A")
                    .setAttr1("attr1")
                    .setLoginTp("facebook")
                    .setSignupTp("email")
                    .setMemberId("fornew21c")
                    .build()
            );


            Map<String, Object> eventMap = new HashMap<>();
            eventMap.put("event", "event");
            eventMap.put("pi","evtlist");
            DOT.logEvent(eventMap);
            //logClick();
            Toast.makeText(this, "Click 발생", Toast.LENGTH_SHORT).show();
        });

        TextView conversion = findViewById(R.id.dot_conversion);
        conversion.setOnClickListener(v -> {
            //logEvent();
//            eventMap.put("event", "login");
//            eventMap.put("loginTp", "facebook");
            Map<String, Object> eventMap = new HashMap<>();
            eventMap.put("event", "event");
            eventMap.put("pi","evtlist");
            DOT.logEvent(eventMap);
            Toast.makeText(this, "Conversion 발생", Toast.LENGTH_SHORT).show();
        });

        TextView purchase = findViewById(R.id.dot_purchase);
        purchase.setOnClickListener(v -> {
            Map<String, Object> purchaseMap = new HashMap<>();
            purchaseMap.put("ordNo", "주문번호");
            purchaseMap.put("curcy", "화폐단위");
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("pg1", "상품카테고리(대)");
            productMap.put("pnc", "상품코드1");
            productMap.put("pnAtr1", "상품속성#1");
            List<Map<String, Object>> productList = new ArrayList<>();
            productList.add(productMap);
            purchaseMap.put("products", productList);
            DOT.logPurchase(purchaseMap);
            //logPurchase();
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


        TextView campaign = findViewById(R.id.dot_internalCampaign);
        campaign.setOnClickListener(v -> {

            InternalCampaign ic = new InternalCampaign.Builder()
                    .setInternalCampaign1("campaign1",3)
                    .setInternalCampaign2("campaign2",3)
                    .setInternalCampaign3("campaign3",3)
                    .build();

            DOT.setInternalCampaign(ic);

            Toast.makeText(this, "Campaign 설정 발생", Toast.LENGTH_SHORT).show();
        });



    }

    private void logEvent() {
        Map<String, Object> event = new HashMap<>();
        event.put("event", "event");
        event.put("pi","evtlist");
//        eventMap.put("g1", "goal 1");
//        eventMap.put("g2", "goal 2");
//        eventMap.put("g3", "goal 3");
//        eventMap.put("g4", "goal 4");
//        eventMap.put("g5", "goal 5");
//        eventMap.put("g6", "goal 6");
//        eventMap.put("g7", "goal 7");
//        eventMap.put("g8", "goal 8");
//        eventMap.put("g9", "goal 9");
//        eventMap.put("g10", "goal 10");
        DOT.logEvent(event);
    }

    private void logClick() {
        Map<String, Object> clickMap = new HashMap<>();
        clickMap.put("ckTp", "click type");
        clickMap.put("ckData", "click data");
        clickMap.put("mvt1", "event mvt 1");
        clickMap.put("mvt2", "event mvt 2");
        clickMap.put("mvt3", "event mvt 3");
        clickMap.put("mvt4", "event mvt 4");
        clickMap.put("mvt5", "event mvt 5");
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("orderNo", "ORD001");
        productMap.put("currency", "KRW");
        productMap.put("pg1", "sports");
        productMap.put("pg2", "fashion");
        productMap.put("pg3", "cloth");
        productMap.put("pnc", "EVENT001");
        productMap.put("ordPno", "POPULARABC");
        productMap.put("amt", "300,000");
        productMap.put("ea", "3");
        productMap.put("mvt1", "mvt1");
        productMap.put("pncAtr1", "attribute 1");
        clickMap.put("product", productMap);
        String json = new Gson().toJson(productMap);
        DOT.logClick(clickMap);
    }

    private void logPurchase() {
        Map<String, Object> purchaseMap = new HashMap<>();
        purchaseMap.put("ordNo", "PORD0070");
        purchaseMap.put("keyword", "purchase");
        purchaseMap.put("keywordCategory", "CAMPAIGN");
        Map<String, Object> productMap = new HashMap<>();
        productMap.put("currency", "KRW");
        productMap.put("pg1", "sports");
        productMap.put("pg2", "fashion");
        productMap.put("pg3", "cloth");
        productMap.put("pnc", "PNC001");
        productMap.put("ordPno", "BESTABC");
        productMap.put("amt", "100,000");
        productMap.put("ea", "1");
        productMap.put("mvt1", "mvt1");
        List<Map<String, Object>> productList = new ArrayList<>();
        productList.add(productMap);
        purchaseMap.put("products", productList);
        String json = new Gson().toJson(purchaseMap);
        DOT.logPurchase(purchaseMap);
    }

    private void logScreen() {
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("pi", "main.page");
        pageMap.put("skwd", "MEMBERSHIP");
        pageMap.put("scart", "SPORTS");
        pageMap.put("cp", "path");
        pageMap.put("sresult", "10");
        pageMap.put("mvt1", "product mvt");
        DOT.logScreen(pageMap);
    }

    private void logXConversion() {
        List<String> list = new ArrayList<>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");

        DOX.logXConversion(
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
        DOX.logXEvent(new XEvent.Builder()
                .setEventName("log event")
                .setProperties(new XProperties.Builder()
                        .set("put1", "put value")
                        .build())
                .build());
    }

    private void logXPurchase() {
        DOX.logXPurchase(new XPurchase.Builder()
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