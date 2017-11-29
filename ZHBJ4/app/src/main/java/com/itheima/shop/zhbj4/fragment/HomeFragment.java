package com.itheima.shop.zhbj4.fragment;

import android.view.View;

import com.itheima.shop.zhbj4.base.BaseFragment;
import com.itheima.shop.zhbj4.base.BaseLoadNetDataOperator;


/**
 * Created by Apple on 2016/9/24.
 */
public class HomeFragment extends BaseFragment implements BaseLoadNetDataOperator {

    @Override
    public void initTitle() {
        setIbMenuDisplayState(false);//左上角的菜单好像都没显示
        setIbPicTypeDisplayState(false);//右上角的排布方式，
        setTitle("首页");
    }

    @Override
    public View createContent() {
        return null;
    }

    @Override
    public void loadNetData() {

    }
}
