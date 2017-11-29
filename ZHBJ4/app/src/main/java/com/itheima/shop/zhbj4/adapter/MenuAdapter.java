package com.itheima.shop.zhbj4.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.shop.zhbj4.R;
import com.itheima.shop.zhbj4.activity.MainActivity;
import com.itheima.shop.zhbj4.base.BaseFragment;
import com.itheima.shop.zhbj4.bean.NewsCenterBean;
import com.itheima.shop.zhbj4.fragment.NewsCenterFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by raynwang on 2017/6/13.
 */

public class MenuAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList;
    //默认选中的条目下标
    private int selectedPosition;

    public void setNewsCenterMenuBeanList(List<NewsCenterBean.NewsCenterMenuBean> newsCenterMenuBeanList) {
        this.newsCenterMenuBeanList = newsCenterMenuBeanList;
        //刷新显示
        notifyDataSetChanged();
    }

    //侧滑适配器，构造
    public MenuAdapter(Context context) {
        this.context = context;
    }

    //创建ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
                                                        //直接在这里generate黄油刀，右下角选择ViewHolder，下面哪个ViewHolder就有了
        return new MyViewHolder(view);
    }

    //对ViewHolder里面的控件进行赋值，onBind是这个意思啊
    @Override//ViewHolder可以给里面的title赋值
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        /**
         * 下面点击事件里用了newsCenterMenuBean，所以这里要final
         */
        final NewsCenterBean.NewsCenterMenuBean newsCenterMenuBean = newsCenterMenuBeanList.get(position);
        MyViewHolder viewHolder = (MyViewHolder) holder;
        viewHolder.tvMenuTitle.setText(newsCenterMenuBean.title);

        //然后就把选中的变成红色，没选中的白色
        if (selectedPosition == position) {
            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_select);
            viewHolder.tvMenuTitle.setTextColor(Color.RED);
        } else {//未选中
            viewHolder.ivArrow.setImageResource(R.drawable.menu_arr_normal);
            viewHolder.tvMenuTitle.setTextColor(Color.WHITE);
        }
        //处理条目点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //如果点击的不是选中条目，那么就让选中条目变成点击的条目
                if (selectedPosition != position) {
                    selectedPosition = position;
                    //刷新界面
                    notifyDataSetChanged();//刷新，会重新调用onBindViewHolder方法
                    //修改对应tab页面的标题
                    BaseFragment baseFragment = ((MainActivity) context).getCurrentTabFragment();
                    baseFragment.setTitle(newsCenterMenuBean.title);
                    if(baseFragment instanceof NewsCenterFragment){
                        NewsCenterFragment newsCenterFragment = (NewsCenterFragment) baseFragment;
                        //切换内容
                        newsCenterFragment.switchContent(position);
                    }
                }
                //关闭侧滑菜单
                ((MainActivity) context).slidingMenu.toggle();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsCenterMenuBeanList == null ? 0 : newsCenterMenuBeanList.size();
    }


    //这里先写一个我的ViewHolder
    static class MyViewHolder extends RecyclerView.ViewHolder {
        //        ImageView ivArrow;
//        TextView tvMenuTitle;
//
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            ivArrow = itemView.findViewById(R.id.iv_arrow);
//            tvMenuTitle = itemView.findViewById(R.id.tv_menu_title);
//        }
        //手写黄油刀试一下，上面的待会试试
        @BindView(R.id.iv_arrow)
        ImageView ivArrow;
        @BindView(R.id.tv_menu_title)
        TextView tvMenuTitle;

        MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
