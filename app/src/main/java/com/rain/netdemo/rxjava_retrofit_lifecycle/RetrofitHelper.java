package com.rain.netdemo.rxjava_retrofit_lifecycle;

import com.rain.netdemo.Constant;
import com.rain.netdemo.rxjava_retrofit_lifecycle.MyGsonConverter.MyGsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


/**
 * Author:rain
 * Date:2017/11/13 10:48
 * Description:retrofit工具类
 */

public class RetrofitHelper {

    public static final int DEFAULT_TIMEOUT = 5;

    private static RetrofitHelper retrofitHelper;

    // 调用接口中的网络请求方法的对象
    private final RetrofitService retrofitService;

    public static RetrofitHelper getInstance() {
        return retrofitHelper == null ? new RetrofitHelper() : retrofitHelper;
    }

    private RetrofitHelper() {
        // 初始化retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())         // gson解析
                .addConverterFactory(MyGsonConverterFactory.create())         // 自定义gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  // rxjava支持
                .client(getOkHttpClient())                                  // okhttpClient
                .build();

        retrofitService = retrofit.create(RetrofitService.class);
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    /**
     * 获取retrofitService接口对象，进行网络请求方法的调用
     * @return
     */
    public RetrofitService getRetrofitService() {
        return retrofitService;
    }
}
