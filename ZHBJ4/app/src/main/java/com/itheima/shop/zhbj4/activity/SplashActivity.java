package com.itheima.shop.zhbj4.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;


import com.itheima.shop.zhbj4.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.itheima.shop.zhbj4.utils.Constant;
import com.itheima.shop.zhbj4.utils.SPUtils;

public class SplashActivity extends Activity implements Animation.AnimationListener {

//    @BindView(R.id.rl)
    RelativeLayout rl;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        ButterKnife.bind(this);
        rl = findViewById(R.id.rl);
        System.out.println("有syso的日志！！！");

        Animation animation = createAnimation();
        rl.startAnimation(animation);
        //监听动画
        animation.setAnimationListener(this);
    }

    //创建动画
    private Animation createAnimation(){
        AnimationSet set = new AnimationSet(false);
        //旋转
        RotateAnimation rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setDuration(1000);
        //缩放
        ScaleAnimation scale = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scale.setDuration(1000);
        //透明度
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        alpha.setDuration(800);

        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        return set;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    //动画执行后调用
    @Override
    public void onAnimationEnd(Animation animation) {
        //延时2s进入向导界面GuideActivity  该方法在主线程   发送一个延时的消息
        mHandler.postDelayed(new MyTask(),1000);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private class MyTask implements Runnable{

        @Override
        public void run() {
            boolean has_guide =  SPUtils.getBoolean(getApplicationContext(), Constant.KEY_HAS_GUIDE,false);
            if(has_guide){
                //进入主界面
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }else{
                //进入向导界面
                Intent intent = new Intent(getApplicationContext(),GuideActivity.class);
                startActivity(intent);
            }
            finish();
        }
    }
}
