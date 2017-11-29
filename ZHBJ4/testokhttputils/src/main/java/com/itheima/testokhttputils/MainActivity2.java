package com.itheima.testokhttputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client=new OkHttpClient();
        String url = "http://10.0.2.2:8080/zhbj/categories.json";
        Request post_request = new Request.Builder()
                .url(url)// 指定请求的地址
                .build();
        client.newCall(post_request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败的处理
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
// 请求成功的处理
                ResponseBody body = response.body();
                String string = body.string();// 把返回的结果转换为String类型
                // body.bytes();// 把返回的结果转换为byte数组
                // body.byteStream();// 把返回的结果转换为流
            }
        });


    }
}
