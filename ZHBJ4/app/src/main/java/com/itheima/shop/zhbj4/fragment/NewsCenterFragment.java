package com.itheima.shop.zhbj4.fragment;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itheima.shop.zhbj4.R;
import com.itheima.shop.zhbj4.adapter.NewsCenterGroupImageViewAdapter;
import com.itheima.shop.zhbj4.adapter.NewsCenterTabVPAdapter;
import com.itheima.shop.zhbj4.base.NewsCenterContentTabPager;
import com.itheima.shop.zhbj4.bean.NewsCenterGroupImageViewBean;
import com.itheima.shop.zhbj4.utils.CacheUtils;
import com.itheima.shop.zhbj4.view.RecycleViewDivider;
import com.viewpagerindicator.TabPageIndicator;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import com.itheima.shop.zhbj4.activity.MainActivity;
import com.itheima.shop.zhbj4.base.BaseFragment;
import com.itheima.shop.zhbj4.base.BaseLoadNetDataOperator;
import com.itheima.shop.zhbj4.bean.NewsCenterBean;

import butterknife.OnClick;
import okhttp3.Call;

import com.itheima.shop.zhbj4.utils.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsCenterFragment extends BaseFragment implements BaseLoadNetDataOperator {

    private NewsCenterBean newsCenterBean;
    private TabPageIndicator tabPageIndicator;
    private ImageButton ibNext;
    private ViewPager vpNewsCenterContent;
    private ArrayList<NewsCenterContentTabPager> views;
    private Map<Integer, View> cacheViews = new HashMap<>();//缓存容器
    private RecyclerView rvGroupImageView;
    private LinearLayoutManager llm;
    private GridLayoutManager glm;

    @Override
    public void initTitle() {
        setIbMenuDisplayState(true);
        setIbPicTypeDisplayState(false);
        setTitle("新闻中心");
    }

    //在新闻內容的位置加载布局的方法，继承与BaseFragment
    @Override
    public View createContent() {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.newscenter_content, (ViewGroup) getView(), false);
                            //上面一个Indicat，下面整个是ViewPager
        /**
         这个新闻中心内容布局里，上面有TabPagerIndicator，下面的大面积，有ViewPager，
         前者的使用必须结合后者！！！
         */
        //初始化TabPageIndicator
        tabPageIndicator = view.findViewById(R.id.tabPagerIndicator);
        ibNext = view.findViewById(R.id.ib_next);
        vpNewsCenterContent = view.findViewById(R.id.vp_newscenter_content);

        ibNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取ViewPager当前显示页面的下标w
                int currentItem = vpNewsCenterContent.getCurrentItem();
                if (currentItem != newsCenterBean.data.get(0).children.size() - 1) {
                    //只要没有到最后
                    vpNewsCenterContent.setCurrentItem(currentItem + 1);
                }
            }
        });
        initViewPager();//初始化ViewPager
        return view;
    }


    //初始化下面的整个ViewPager
    private void initViewPager() {
        //保存ViewPager显示每个条目的集合
        views = new ArrayList<NewsCenterContentTabPager>();
        //获取每个item的数据
        for (NewsCenterBean.NewsCenterNewsTabBean tabBean : newsCenterBean.data.get(0).children) {
            NewsCenterContentTabPager tabPager = new NewsCenterContentTabPager(getContext());
            views.add(tabPager);//往集合里添加每个pager
        }
        //创建适配器，ViewPager的适配器
        /**
         * 通过构造方法给适配器传数据，传TabPager的集合，传TabBean的集合
         */
        NewsCenterTabVPAdapter adapter = new NewsCenterTabVPAdapter(views, newsCenterBean.data.get(0).children);
        //设置适配器
        vpNewsCenterContent.setAdapter(adapter);
        //让TabPagerIndicator和ViewPager进行联合
        tabPageIndicator.setViewPager(vpNewsCenterContent);

        //让新闻中心第一个子tab的轮播图开始切换
        views.get(0).startSwitch();
        //给ViewPager设置页面切换监听
        //注意：ViewPager和TabPagerIndicator配合使用，监听只能设置给TabPagerIndicator
        tabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //当前的开始切换，其他的tab停止切换
                for (int i = 0; i < views.size(); i++) {
                    NewsCenterContentTabPager tabPager = views.get(i);
                    if (position == i) {//选中页
                        tabPager.startSwitch();
                    } else {//未选中页
                        tabPager.stopSwitch();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    //...这个方法从网上下载到json字符串
    @Override
    public void loadNetData() {
        final String url = Constant.NEWSCENTER_URL;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getActivity(), "获取新闻中心数据失败", Toast.LENGTH_SHORT).show();
                        //加载数据失败，从缓存中读取数据
                        try {
                            String json = CacheUtils.readCache(getContext(), url);
                            if (!TextUtils.isEmpty(json)) {
                                processGroupImageViewData(json);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        //把response  == json 转换成对应的数据模型
                        processData(response);
                        //加载数据成功，保存数据到缓存中
                        try {
                            CacheUtils.saveCache(getContext(), url, response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //把Json格式的字符串转换成对应的模型对象
    public void processData(String json) {
        hasLoadData = true;//这个是父类BaseFragment的成员变量，可以直接拿来用？厉害了。表示已经加载数据
        Gson gson = new Gson();
        //把json字符串变成了新闻中心业务类
        newsCenterBean = gson.fromJson(json, NewsCenterBean.class);
        //因为MainActivity需要初始化侧滑菜单，所以需要把数据传递给MainActivity
        ((MainActivity) getActivity()).setNewsCenterMenuBeanList(newsCenterBean.data);//这里数据获取的没问题
        //创建内容的布局
        View view = createContent();//调用上面创建内容的方法
        //加载内容的布局
        addView(view);//这就是BaseFragment里面的方法，把view添加到碎片上
        //把布局添加到缓存的容器
        cacheViews.put(0, view);
    }

    //切换内容，点击侧滑menu的组图时，调用的方法
    public void switchContent(int position) {
        //标题右边的切换menu
        if (position == 2) {
            //显示
            ibPicType.setVisibility(View.VISIBLE);
        } else {
            //隐藏
            ibPicType.setVisibility(View.GONE);
        }

        //先从缓存的容器里面去获取
        View view = cacheViews.get(position);
        if (view == null) {
            //创建里面的内容
            container.removeAllViews();

            if (position == 2) {
                //组图
                //初始化组图布局
                view = createGroupImageViewLayout();
                //将组图布局添加到新闻中心页中的FramenLayout容器中
                addView(view);//baseFragment里面的方法
                //放入缓存中，如果缓存中有数据，直接取出
                cacheViews.put(position, view);
                //加载组图数据
                loadGroupImageViewData(position);
            }

        } else {
            //添加布局
            addView(view);
        }
    }

    //加载组图数据
    private void loadGroupImageViewData(int position) {

        //获取路径
        final String url = Constant.HOST + newsCenterBean.data.get(position).url;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        System.out.println("---------失败");
                        //访问失败，就读取缓存
                        try {
                            String json = CacheUtils.readCache(getContext(), url);
                            if (!TextUtils.isEmpty(json)) {
                                processGroupImageViewData(json);
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        System.out.println("------------成功");
                        //成功就缓存数据
                        try {
                            CacheUtils.saveCache(getContext(), url, response);
                            processGroupImageViewData(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //处理组图数据
    public void processGroupImageViewData(String json) {
        Gson gson = new Gson();
        //转化为数据模型
        NewsCenterGroupImageViewBean newsCenterGroupImageViewBean =
                gson.fromJson(json, NewsCenterGroupImageViewBean.class);
        //绑定适配器给rvGroupImageView
        System.out.println("------------"+json);
        rvGroupImageView.setAdapter(new NewsCenterGroupImageViewAdapter(
                newsCenterGroupImageViewBean.data.news, getContext()));
    }

    //加载组图的布局
    private View createGroupImageViewLayout() {
        View view = LayoutInflater.from(getContext()).inflate(
                R.layout.newscenter_group_imageview, (ViewGroup) getActivity().getWindow().getDecorView(), false);
                    //这里面就一个RecyclerView
        rvGroupImageView = (RecyclerView) view.findViewById(R.id.rv_group_imageview);
        //有的时候是列表，有的时候是网格
        //线性布局管理器
        llm = new LinearLayoutManager(getContext());
        //网格布局管理器
        glm = new GridLayoutManager(getContext(), 2);
        rvGroupImageView.setLayoutManager(llm);//意思默认是线性的？
        //添加分割线
        rvGroupImageView.addItemDecoration(new RecycleViewDivider(getContext(),
                LinearLayoutManager.HORIZONTAL, 1, Color.BLACK));
        return view;
    }

    //组图的显示状态
    private int groupImageViewState = LIST_STATE;
    private final static int LIST_STATE = 0;
    private final static int GRID_STATE = 1;

    //切换列表和网格的点击事件
    @OnClick(R.id.ib_pic_type)
    public void switchListGridState(View view) {
        //如果是列表，切换至网格
        if (groupImageViewState == LIST_STATE) {
            groupImageViewState = GRID_STATE;
            ibPicType.setBackgroundResource(R.drawable.icon_pic_list_type);//右上角设置成三横
            rvGroupImageView.setLayoutManager(glm);//设置成网格
            //添加垂直方向的分割线
            rvGroupImageView.addItemDecoration(new RecycleViewDivider(getContext(),
                    GridLayoutManager.VERTICAL, 1, Color.BLACK));
        } else {//如果是网络，切换至列表
            groupImageViewState = LIST_STATE;
            ibPicType.setBackgroundResource(R.drawable.icon_pic_grid_type);//右上角设置成f4
            rvGroupImageView.setLayoutManager(llm);
            //添加水平方向的分割线
            rvGroupImageView.addItemDecoration(new RecycleViewDivider(getContext(),
                    LinearLayoutManager.HORIZONTAL, 1, Color.BLACK));
        }
    }
}
