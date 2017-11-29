package com.itheima.shop.zhbj4.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

// * 缓存的工具类：缓存服务器返回的json字符串
public final class CacheUtils {

    //缓存json，传进来存储的路径url，是/data/data/packagename/files
    /**
        上下文有非常方便的方法，contex.openFileInput  .openFileOutput 打开的就是上面的文件路径
     */
    public static void saveCache(Context context,String url,String json) throws  Exception{
        /**
            文件名，通过加密url得到，因为url里面有/，所以不能直接作为文件名
         */
        String name = Md5Utils.encode(url);
        //输出流
        FileOutputStream fileOutputStream = context.openFileOutput(name, Context.MODE_PRIVATE);
        //写数据
        fileOutputStream.write(json.getBytes());//String转成字符数组
        //关流
        fileOutputStream.close();
    }

    //读取json
    public static String readCache(Context context,String url)throws  Exception{
        //文件名
        String name = Md5Utils.encode(url);
        FileInputStream fileInputStream = context.openFileInput(name);//上下文可以open
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while((len = fileInputStream.read(buffer)) != -1){
            bos.write(buffer,0,len);
        }
        String json = bos.toString();
        bos.close();
        fileInputStream.close();
        return json;
    }
}
