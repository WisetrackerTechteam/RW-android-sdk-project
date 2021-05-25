package com.sdk.wisetracker.app.service;

import com.google.gson.JsonObject;

public class BasicDataTest {

    public boolean a = true; // isFirstOpenInSession
    private String b = "Y"; // isFirstAppOpen
    private long c = 0; // firstAppOpenTime

    // event time
    private long d = 0; // lastSessionTime
    private long e = 0; // lastAppEventTime
    private long f = 0; // lastPurchaseTime
    private long g = 0; // lastFingerPrintTime
    private long h = 0; // lastRetentionTime

    // token
    private String i = null; // token
    private long j = 0; // tokenExpireTime

    // ntp.device time difference
    private long k = 0; // timeDiff

    public void loadPreviousDataFormat(JsonObject beforeSchema){
        if(beforeSchema.has("a")){  this.b = beforeSchema.get("a").getAsString();   }
        if(beforeSchema.has("b")){  this.c = beforeSchema.get("b").getAsLong();     }
        if(beforeSchema.has("c")){  this.d = beforeSchema.get("c").getAsLong();     }
        if(beforeSchema.has("d")){  this.e = beforeSchema.get("d").getAsLong();     }
        if(beforeSchema.has("e")){  this.f = beforeSchema.get("e").getAsLong();     }
        if(beforeSchema.has("f")){  this.g = beforeSchema.get("f").getAsLong();     }
        if(beforeSchema.has("g")){  this.h = beforeSchema.get("g").getAsLong();     }
        if(beforeSchema.has("h")){  this.i = beforeSchema.get("h").getAsString();   }
        if(beforeSchema.has("i")){  this.j = beforeSchema.get("i").getAsLong();     }
        if(beforeSchema.has("j")){  this.k = beforeSchema.get("j").getAsLong();     }
    }
}
