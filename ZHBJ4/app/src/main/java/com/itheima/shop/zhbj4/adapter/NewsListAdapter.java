package com.itheima.shop.zhbj4.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.shop.zhbj4.R;
import com.itheima.shop.zhbj4.activity.NewsDetailActivity;
import com.itheima.shop.zhbj4.bean.NewsCenterTabBean;
import com.itheima.shop.zhbj4.utils.Constant;
import com.itheima.shop.zhbj4.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Apple on 2016/9/28.
 */
public class NewsListAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<NewsCenterTabBean.NewsBean> newsBeanList;

    public NewsListAdapter(Context context, List<NewsCenterTabBean.NewsBean> newsBeanList) {
        this.context = context;
        this.newsBeanList = newsBeanList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewsCenterTabBean.NewsBean newsBean = newsBeanList.get(position);
        final ViewHolder viewHolder = (ViewHolder) holder;
        Picasso.with(context).load(newsBean.listimage).into(viewHolder.ivIcon);
        viewHolder.tvTitle.setText(newsBean.title);
        viewHolder.tvTime.setText(newsBean.pubdate);

        //判断每条新闻是否已经被查看过，如果查看，修改字体样式为灰色
        String readNewsContent = SPUtils.getString(context, Constant.KEY_HAS_READ,"");
        if(readNewsContent.contains(newsBean.id)){
            viewHolder.tvTitle.setTextColor(Color.GRAY);
        }else{
            viewHolder.tvTitle.setTextColor(Color.BLACK);
        }
        //条目点击事件，跳转到内容web
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到新闻详情界面
                Intent intent = new Intent(context,NewsDetailActivity.class);
                intent.putExtra("url",newsBean.url);
                context.startActivity(intent);

                //存储该条新闻的唯一标识：
                String id = newsBean.id;
                //存储在哪里？Sp   File   DB（）
                String readNews = SPUtils.getString(context, Constant.KEY_HAS_READ,"");
                if(!readNews.contains(id)){
                    String value = readNews+ "," + id;
                    //存储
                    SPUtils.saveString(context,Constant.KEY_HAS_READ,value);
                    //读过的设置灰色
                    viewHolder.tvTitle.setTextColor(Color.GRAY);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsBeanList != null ? newsBeanList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_icon)
        ImageView ivIcon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
