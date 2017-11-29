package com.itheima.shop.zhbj4.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.itheima.shop.zhbj4.utils.MyLogger;


/**
 * Created by Apple on 2016/10/6.
 * 图片三级缓存框架
 * 一层是内存memory，二层是磁盘local，三层是网络net
 */

public class BitmapUtils {

    /**
     *  顺序是这样的：
     *  先从内存中读取，读到了就读到了
     *  如果没读到，就从本地磁盘获取，获取到了，并且把数据在内存中保存一份，方便下次调用
     *  如果本地磁盘也没有，那么就从网络获取，获取到了，就把数据在内存和磁盘都保存一份，方便下次调用
     */


    private static final String TAG = "BitmapUtils";

    static{
        netCacheUtils = new NetCacheUtils();
        localCacheUtils = new LocalCacheUtils();
        memoryCacheUtils = new MemoryCacheUtils();
    }

    private static NetCacheUtils netCacheUtils;
    private static LocalCacheUtils localCacheUtils;
    private static MemoryCacheUtils memoryCacheUtils;

    //显示图片
    public static void display(Context context,ImageView iv, String url){
        Bitmap bitmap = null;
        //内存缓存
        bitmap = memoryCacheUtils.readCache(url);
        if(bitmap != null){
            iv.setImageBitmap(bitmap);
            MyLogger.i(TAG,"从内存获取了图片");
            return;
        }
        //磁盘缓存
        bitmap = localCacheUtils.readCache(context, url);
        if(bitmap != null){
            iv.setImageBitmap(bitmap);
            MyLogger.i(TAG,"从磁盘获取了图片");
            return;
        }
        //网络缓存
        netCacheUtils.getBitmapFromNet(context,iv,url);
    }
}
