package com.itheima.shop.zhbj4.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import com.itheima.shop.zhbj4.utils.Md5Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Apple on 2016/10/6.
 * 磁盘缓存（该应用程序的cache目录下的zhbj_cache目录）
 */
public class LocalCacheUtils {

    //写缓存
    public static void saveCache(Context context,Bitmap bitmap, String url){
        //缓存目录
        File dir = new File(context.getCacheDir(),"zhbj_cache");
        if(!dir.exists()){
            dir.mkdirs();
        }

        //把图片缓存在缓存目录，用Md5来加密文件名
        File file = new File(dir, Md5Utils.encode(url));
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                                        //百分百压缩
    }

    //读缓存
    public static Bitmap readCache(Context context,String url){
        File dir = new File(context.getCacheDir(),"zhbj_cache");
        if(!dir.exists()){
           return null;
        }
        File file = new File(dir, Md5Utils.encode(url));
        if(!file.exists()){
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        //把数据缓存在内存中
        MemoryCacheUtils.saveCache(bitmap,url);
        return bitmap;
    }
}
