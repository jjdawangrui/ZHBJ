package com.itheima.eventdispatchdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i("TAG","MainActivity          dispatchTouchEvent");
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
        Log.i("TAG","MainActivity          onTouchEvent");
        return super.onTouchEvent(event);
    }
}
