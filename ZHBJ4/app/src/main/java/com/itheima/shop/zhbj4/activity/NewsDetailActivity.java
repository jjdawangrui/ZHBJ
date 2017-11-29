package com.itheima.shop.zhbj4.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;


import com.itheima.shop.zhbj4.R;
import com.itheima.shop.zhbj4.utils.Constant;
import com.itheima.shop.zhbj4.utils.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

//文字内容
public class NewsDetailActivity extends Activity {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.ib_textsize)
    ImageButton ibTextsize;
    @BindView(R.id.ib_share)
    ImageButton ibShare;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.pb)
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);
        String url = getIntent().getStringExtra("url");

        //让WebView支持javaScript脚本，也就是一次性不全部加载出来，下面有一个  查看全文
        webView.getSettings().setJavaScriptEnabled(true);

        //设置WebView的客户端，辅助webView，处理各种跳转时间
        webView.setWebViewClient(new WebViewClient() {
            @Override//监听页面加载完成
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pb.setVisibility(View.GONE);//隐藏进度条
            }
        });
        int defaultPosition = SPUtils.getInt(this, Constant.WEB_TEXT_SIZE, 2);
        defaultPosition = defaultPosition == -1 ? 2 : defaultPosition;
        webView.getSettings().setTextSize(textSizes[defaultPosition]);
//        webView.getSettings().setTextSize(WebSettings.TextSize.LARGER);
        //让WebView去加载网页
        webView.loadUrl(url);
    }

    @OnClick({R.id.ib_back, R.id.ib_textsize, R.id.ib_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            case R.id.ib_textsize:
                changeWebViewTextSize();
                break;
            case R.id.ib_share:
                showShare();
                break;
        }
    }


    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("黑马程序员分享的");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是黑马程序员，我骄傲！");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

    private String[] types = new String[]{
            "超大号字体",
            "大号字体",
            "正常字体",
            "小号字体",
            "超小号字体",
    };
    private WebSettings.TextSize[] textSizes = new WebSettings.TextSize[]{
            WebSettings.TextSize.LARGEST,
            WebSettings.TextSize.LARGER,
            WebSettings.TextSize.NORMAL,
            WebSettings.TextSize.SMALLER,
            WebSettings.TextSize.SMALLEST,
    };

    int position;//从sp里面获取的条目位置
    int selectPosition;//选择的条目位置
    boolean isSelect = false;//是否在dialog里面选择了

    //修改网页字体大小
    private void changeWebViewTextSize() {
        position = SPUtils.getInt(this, Constant.WEB_TEXT_SIZE, 2);
        //单选的对话框   AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("选择字体的大小")
                //默认是2条目，也就是默认第三个正常字体
                .setSingleChoiceItems(types, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectPosition = which;
                        isSelect = true;
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isSelect) {
                            SPUtils.saveInt(getApplicationContext(), Constant.WEB_TEXT_SIZE, selectPosition);
                            webView.getSettings().setTextSize(textSizes[selectPosition]);
                        } else {
                            webView.getSettings().setTextSize(textSizes[position]);
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

}
