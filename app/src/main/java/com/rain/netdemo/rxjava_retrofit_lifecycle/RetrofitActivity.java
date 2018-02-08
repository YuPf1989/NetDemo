package com.rain.netdemo.rxjava_retrofit_lifecycle;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;
import com.rain.netdemo.bean.NewsListBean;
import com.rain.netdemo.rxjava_retrofit_lifecycle.MyObserver.MyObserver;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.Reply;

/**
 * Author:rain
 * Date:2017/11/13 14:38
 * Description:rxjava+
 */

public class RetrofitActivity extends AbsBaseActivity implements View.OnClickListener {

    private static final String TAG  = RetrofitActivity.class.getSimpleName();

    private Button connect;
    private TextView content;
    private Toolbar toolbar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_retrofit;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        connect = (Button) findViewById(R.id.connect);
        content = (TextView) findViewById(R.id.content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        initToolBar(toolbar,true,"RetrofitActivity");

        connect.setOnClickListener(this);
        findViewById(R.id.connect2).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        clearUI();
        switch (v.getId()) {
            case R.id.connect:
                connect();
                break;

            case R.id.connect2:
                connect2();
                break;
        }
    }

    private void clearUI() {
        content.setText("");
    }

    /**
     * 带缓存
     */
    private void connect2() {
        Observable<NewsListBean> wikiList = RetrofitHelper.getInstance()
                .getRetrofitService()
                .getWikiList();
        CacheProvidersHelper.getInstance()
                .getUserCacheProviders()
                .getWikiList(wikiList)
                .subscribeOn(Schedulers.io())   // 在子线程请求
                .observeOn(AndroidSchedulers.mainThread())  // 主线程处理结果
                .compose(this.<Reply<NewsListBean>>bindUntilEvent(ActivityEvent.DESTROY))  // DESTROY时候取消订阅
                .subscribe(new MyObserver<Reply<NewsListBean>>() {
                    @Override
                    public void onNext(@NonNull Reply<NewsListBean> re) {
                        String json = new Gson().toJson(re.getData());
                        Log.e(TAG, "onNext: 数据来源："+re.getSource());
                        content.setText("content:"+json);
                    }
                });
    }

    private void connect() {
        RetrofitHelper.getInstance()
                .getRetrofitService()
                .getWikiList()
                .subscribeOn(Schedulers.io())   // 在子线程请求
                .observeOn(AndroidSchedulers.mainThread())  // 主线程处理结果
                .compose(this.<NewsListBean>bindUntilEvent(ActivityEvent.DESTROY))  // DESTROY时候取消订阅
                .subscribe(new MyObserver<NewsListBean>() {
                    @Override
                    public void onNext(@NonNull NewsListBean result) {
                        List<NewsListBean.NewsListBean1> newsList = result.getNewsList();
                        String json = new Gson().toJson(newsList);
                        content.setText("content:"+json);
                    }
                });
    }

    /**
     * 如果在<activity></>中配置了android:configChanges="keyboardHidden|orientation|screenSize",
     * 则横竖屏切换activity不重建，会调用该方法
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {    // 横屏
            Log.e(TAG, "onConfigurationChanged: ORIENTATION_LANDSCAPE");

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {  // 竖屏
            Log.e(TAG, "onConfigurationChanged: ORIENTATION_PORTRAIT");
        }
    }


}
