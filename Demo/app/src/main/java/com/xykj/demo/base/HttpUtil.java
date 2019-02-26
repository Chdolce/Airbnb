package com.xykj.demo.base;

import okhttp3.OkHttpClient;
import okhttp3.Request;
/*
*和服务器交互
 */
public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
