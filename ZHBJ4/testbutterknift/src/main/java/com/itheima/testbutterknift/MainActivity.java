package com.itheima.testbutterknift;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bt_1)
    Button bt1;
    @BindView(R.id.bt_2)
    Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.bt_1, R.id.bt_2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
                break;
            case R.id.bt_2:
                break;
        }
    }
    public void loadView() {
        View view = View.inflate(this, R.layout.activity_main, null);
    }
    static class ViewHolder {
        @BindView(R.id.bt_1)
        Button bt1;
        @BindView(R.id.bt_2)
        Button bt2;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
