package com.itheima.bottomtabdemo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itheima.bottomtabdemo.base.BaseFragment;

/**
 * Created by Apple on 2016/9/20.
 */
public class SettingTabFragment extends BaseFragment {

    @Override
    public void setContent() {
        //getView()方法获取的就是onCreateView()创建的view
        ((TextView)getView()).setText("设置内容");
    }
}
