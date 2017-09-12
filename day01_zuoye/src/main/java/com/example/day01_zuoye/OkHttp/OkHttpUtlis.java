package com.example.day01_zuoye.OkHttp;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 妞妞 on 2017/9/11.
 */
public class OkHttpUtlis {
    private static OkHttpUtlis utlis;
    private final OkHttpClient client;

    private OkHttpUtlis(){
        client = new OkHttpClient.Builder().build();
    }
    public static synchronized OkHttpUtlis getInstance(){
        if (utlis == null) {
            utlis=new OkHttpUtlis();
        }
        return utlis;
    }
    public void sendGet(String url, Callback callback){
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
