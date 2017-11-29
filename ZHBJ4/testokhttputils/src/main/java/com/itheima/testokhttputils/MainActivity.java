package com.itheima.testokhttputils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.itheima.testokhttputils.bean.CategoryBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //http://10.0.2.2:8080/zhbj/categories.json
        OkHttpUtils//联网工具类
                .get()//get请求方式
                .url("http://10.0.2.2:8080/zhbj/categories.json")//请求路径
                .build()//构建
                .execute(new StringCallback() {//执行请求    返回的是String类型的数据
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.i(TAG,response);
                        Gson gson = new Gson();
                        CategoryBean categoryBean = gson.fromJson(response, CategoryBean.class);
                    }
                });
    }
}
