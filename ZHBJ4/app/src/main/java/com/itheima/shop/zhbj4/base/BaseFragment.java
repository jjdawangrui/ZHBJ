package com.itheima.shop.zhbj4.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.itheima.shop.zhbj4.R;
import com.itheima.shop.zhbj4.activity.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
    模板方法设计模式：
    模板方法：多个类，实现的相同行为或者属性，通过模板方法进行封装，处理并计算核心的实现。

    抽象方法：在多个类中，不同行为的实现，通过抽象方法声明，并有实现类实现该方法，执行相应的行为处理
    事项方法以及未实现的方法，抽象方法：
    实现方法：在多个类中，实现相应的行为，进行封装处理

    (本项目没有)未实现方法：
 */
//5个子类都继承这个抽象类，实现里面的方法，重写initTitle方法，因为title都不一样
public abstract class BaseFragment extends Fragment {

    @BindView(R.id.ib_menu)
    public ImageButton ibMenu;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.ib_pic_type)
    public ImageButton ibPicType;
    @BindView(R.id.container)
    public FrameLayout container;

    //是否加载数据
    public boolean hasLoadData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_base, container, false);
                                        //基本的fragment，上面一个红色的标题，下面是空的framelayout
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTitle();
    }

    //初始化标题   让每个子类去进行实现
    public abstract void initTitle();

    //设置Menu的显示状态
    public void setIbMenuDisplayState(boolean isShow){
        ibMenu.setVisibility(isShow?View.VISIBLE:View.GONE);
    }
    //设置PicType的显示状态
    public void setIbPicTypeDisplayState(boolean isShow){
        ibPicType.setVisibility(isShow?View.VISIBLE:View.GONE);
    }

    //设置标题内容
    public void setTitle(String title){
        tvTitle.setText(title);
    }

    //创建内容，这也是抽象的，等着子类去实现
    public abstract View createContent();

    //向容器里面添加内容
    public void addView(View view){
        //清空原来的内容，相当于刷新
        container.removeAllViews();
        //添加内容
        container.addView(view);//这个container是一个FrameLayout
    }


    @OnClick(R.id.ib_menu)
    public void onClick() {
        //对于侧滑菜单进行切换
        //目标：获取SlidingMenu -->MainActivity
        ((MainActivity)getActivity()).slidingMenu.toggle();//这个方法，进行一次滑动
    }
}
