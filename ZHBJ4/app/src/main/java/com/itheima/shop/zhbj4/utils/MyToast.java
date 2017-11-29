package com.itheima.shop.zhbj4.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by raynwang on 2017/6/14.
 */
//没必要这个类吧，讲道理，吐司直接自己写
public final class MyToast {
    public static void show(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
