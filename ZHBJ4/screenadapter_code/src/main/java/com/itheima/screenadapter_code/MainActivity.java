package com.itheima.screenadapter_code;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取手机屏幕的宽度
        int widthPixels = getResources().getDisplayMetrics().widthPixels;//宽度
        int heightPixels = getResources().getDisplayMetrics().heightPixels;//高度

        float density = getResources().getDisplayMetrics().density;//密度
        int densityDpi = getResources().getDisplayMetrics().densityDpi;//像素密度

        Log.i(TAG,"widthPixels:"+widthPixels);
        Log.i(TAG,"heightPixels:"+heightPixels);
        Log.i(TAG,"density:"+density);
        Log.i(TAG,"densityDpi:"+densityDpi);


        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) ll.getLayoutParams();
        params.width = widthPixels/4;
        params.height = heightPixels;
        //设置布局参数
        ll.setLayoutParams(params);
    }
}
