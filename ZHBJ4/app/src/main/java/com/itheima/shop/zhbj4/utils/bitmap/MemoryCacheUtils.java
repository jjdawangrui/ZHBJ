package com.itheima.shop.zhbj4.utils.bitmap;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.util.HashMap;

/**
 * Created by Apple on 2016/10/6.
 * 内存缓存：通过HashMap来进行数据的存储
 */
public class MemoryCacheUtils {

    static {
//        caches = new HashMap<>();
        /**
         * java默认的数据类型是强引用，java语言提供了另外一种机制：软引用、弱引用、虚引用
         * 软引用：当虚拟机内存不足的时候，会回收软引用的对象
         * 弱引用：当对象没有应用的时候，马上回收
         * 虚引用：任何情况下都可能被回收
         * 我们的这种情况，当然选择软引用，就是把Bitmap包装一下SoftReference<Bitmap>
         */
        //因为从 Android 2.3 (API Level 9)开始，垃圾回收器会更倾向于回收持有软引用或弱引用的对象，
        // 这让软引用和弱引用变得不再可靠。
        /**
         * 所以我们使用LruCache:least recently use 最近最少使用的算法
         * 图片A
         * 图片B
         * 图片C  那么C会被回收
         * 图片B
         * 图片A
         */

        long maxMemory = Runtime.getRuntime().maxMemory();//获取Dalvik虚拟机最大的内存大小：16

        lruCache = new LruCache<String,Bitmap>((int) (maxMemory/8)){//指定内存缓存集合的大小
            //获取图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes()*value.getHeight();//图片大小，横向字节数乘以高度
            }
        };
    }

//    private static HashMap<String,Bitmap> caches;

    private static LruCache<String, Bitmap> lruCache;

    //写缓存，hashmap，键是地址，值就是位图
    public static void saveCache(Bitmap bitmap,String url){
//        caches.put(url,bitmap);
        lruCache.put(url,bitmap);
    }
    //读缓存
    public static Bitmap readCache(String url){
//        return caches.get(url);
        return lruCache.get(url);
    }
}
