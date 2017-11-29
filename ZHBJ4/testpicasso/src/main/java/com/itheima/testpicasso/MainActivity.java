package com.itheima.testpicasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        //联网加载图片
        Picasso.with(this).load("http://10.0.2.2:8080/zhbj/photos/images/46728356JBUO.jpg").into(iv);
    }
}
