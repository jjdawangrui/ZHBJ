package com.itheima.bottomtabdemo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 5个TabFragment页面的基类
 * 相同的行为：
 *     1 加载布局 -实现是相同
 *     2 设置内容 -实现不同
 * Created by Apple on 2016/9/20.
 */
public abstract class BaseFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tv = new TextView(getContext());
        tv.setTextSize(20);
        tv.setTextColor(Color.RED);
        tv.setGravity(Gravity.CENTER);
        return tv;
    }

    //布局创建完成
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置内容显示
        setContent();
    }

    //该方法应该让子类实现
    public abstract void setContent();
}
