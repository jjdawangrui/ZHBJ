package com.itheima.shop.zhbj4.utils;

import android.content.Context;

/**
 * Created by Apple on 2016/10/7.
 * dp转像素px的工具类
 */
public class Dp2PxUtils {

    //dp转换成px
    public static int dp2px(Context context,int dp){
        return (int)(dp * context.getResources().getDisplayMetrics().density + 0.5);//+0.5为了减小误差
    }
}
