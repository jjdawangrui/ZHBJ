package com.itheima.test_recyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static com.itheima.test_recyclerview.R.layout.item;
/*
RecylerView封装了viewholder的回收复用，标准化了ViewHolder，
编写Adapter面向的是ViewHolder，而不再是View复用的，逻辑被封装了，写起来更加简单
RecylerView可以通过LayoutMananger很方便的实现列表，网格，瀑布流形式的布局结构
 */
public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();//这个handler都没用上。。。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        //RecyclerView相关联的类：布局管理器(XXXLayoutManger 设置RecyclerView的显示风格：列表、网格瀑布流)、适配器（Adapter）、ViewHolder
        //1 设置布局管理器
        //LinearLayoutManager 线性布局管理器 方向：默认是垂直方向
//        LinearLayoutManager lm = new LinearLayoutManager(this);
//        lm.setOrientation(LinearLayoutManager.VERTICAL);

        //网格
//        GridLayoutManager lm = new GridLayoutManager(this,2);
//        lm.setOrientation(GridLayoutManager.HORIZONTAL);
//        rv.setLayoutManager(lm);

        //瀑布流                                                       列数                          竖直方向
        StaggeredGridLayoutManager  lm = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(lm);//recycleView设置布局管理器，传进去瀑布

        //2 设置适配器，一般是view来set  Adapter
        rv.setAdapter(new MyAdapter());

    }

    private class MyAdapter extends RecyclerView.Adapter{//这里继承的不是一般的适配器，是回收view的适配器

        //创建ViewHolder
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(item,parent,false);
            return new MyViewHolder(view);//这个构造方法可以，传进去view，然后又super给父类用
        }

        //对ViewHolder里面的控件进行赋值，onBind是这个意思啊
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            MyViewHolder viewHolder = (MyViewHolder) holder;
            viewHolder.setData(position);
        }

        //条目的数量
        @Override
        public int getItemCount() {
            return 60;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }

        public void setData(final int position) {
            tv.setText("第"+position+"条目的位置");

            //设置条目点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
