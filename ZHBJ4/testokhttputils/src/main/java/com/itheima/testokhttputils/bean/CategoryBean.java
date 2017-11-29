package com.itheima.testokhttputils.bean;

import java.util.List;

/**
 * Created by Apple on 2016/9/21.
 */
public class CategoryBean {
    public List<DataBean> data;
    public List<String> extend;
    public int retcode;

    public class DataBean{
        public List<ChildrenBean> children;
        public String id;
        public String title;
        public String type;
        public String url;
        public String url1;
    }

    public class ChildrenBean{
        public String  id;
        public String title;
        public String  type;
        public String  url;
    }
}
