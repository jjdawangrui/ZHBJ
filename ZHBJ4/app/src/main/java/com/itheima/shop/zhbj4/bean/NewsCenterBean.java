package com.itheima.shop.zhbj4.bean;

import java.util.List;

/**
 * Created by Apple on 2016/9/26.
 * 新闻中心对应的模型对象
 */
public class NewsCenterBean {
    public List<NewsCenterMenuBean> data;
    public List<String> extend;
    public int retcode;


    //侧滑菜单对应的分类数据，有4个，新闻 专题 组图 互动
    public class NewsCenterMenuBean{
        //新闻下面有很多孩子，这是新闻的tab，他们这么叫
        public List<NewsCenterNewsTabBean> children;
        public int id;
        public String title;
        public String url;
        public String url1;
        public String dayurl;
        public String excurl;
        public String weekurl;
        public int type;
    }

    //新闻中心新闻tab的模型，是上面业务类的孩子
    public class NewsCenterNewsTabBean{
        public int id;
        public String title;
        public String url;
        public int type;
    }
}
