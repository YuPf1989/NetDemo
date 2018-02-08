package com.rain.netdemo.rxjava_retrofit_lifecycle;

import com.rain.netdemo.bean.NewsList;
import com.rain.netdemo.bean.NewsListBean;
import com.rain.netdemo.rxjava_retrofit_lifecycle.MyGsonConverter.Result;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author:rain
 * Date:2017/11/13 11:08
 * Description:该类封装了各种接口
 */

interface RetrofitService {

    /**
     * 获取健康百科列表 ok
     * @param
     * @param
     * @return
     */
    @GET("getWikiList.html")
    Observable<NewsListBean> getWikiList();
}
