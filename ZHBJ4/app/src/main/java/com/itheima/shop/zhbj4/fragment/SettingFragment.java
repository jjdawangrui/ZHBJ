package com.itheima.shop.zhbj4.fragment;

import android.view.View;

import com.itheima.shop.zhbj4.base.BaseFragment;


/**
 * Created by Apple on 2016/9/24.
 */

/**
 * 只有 设置 没有实现网络加载接口
 */
public class SettingFragment extends BaseFragment {

    @Override
    public void initTitle() {
        setIbMenuDisplayState(false);
        setIbPicTypeDisplayState(false);
        setTitle("设置");
    }

    @Override
    public View createContent() {
        return null;
    }
}
