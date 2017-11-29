package com.itheima.eventdispatchdemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Apple on 2016/9/20.
 */
public class MyView extends View {

    private Paint mPaint;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setTextSize(15);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText("事件传递",0,150,mPaint);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //请求父控件不要拦截事件
        getParent().requestDisallowInterceptTouchEvent(true);
        Log.i("TAG","MyView                  dispatchTouchEvent");
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.i("TAG","MainActivity          按下");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i("TAG","MainActivity          移动");
                break;
            case MotionEvent.ACTION_UP:
                Log.i("TAG","MainActivity          弹起");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("TAG","MyView                  onTouchEvent");
        return true;//return true 表示 MyView处理事件
    }
}
