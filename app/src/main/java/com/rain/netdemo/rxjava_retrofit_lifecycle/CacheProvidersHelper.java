package com.rain.netdemo.rxjava_retrofit_lifecycle;

import com.rain.netdemo.MyApplication;

import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * Author:rain
 * Date:2018/2/7 16:19
 * Description:userCacheProviders帮助类，采用单例
 */

class CacheProvidersHelper {
    private static final CacheProvidersHelper ourInstance = new CacheProvidersHelper();
    private static UserCacheProviders userCacheProviders;

    static CacheProvidersHelper getInstance() {
        userCacheProviders = new RxCache.Builder()
                .useExpiredDataIfLoaderNotAvailable(true)// 设置成true,会在数据为空或者发生错误时,忽视EvictProvider为true或者缓存过期的情况,继续使用缓存(前提是之前请求过有缓存)
                .persistence(MyApplication.getInstance().getExternalCacheDir(), new GsonSpeaker())// 缓存文件的配置、数据的解析配置
                .using(UserCacheProviders.class);// 这些配置对应的缓存接口

        return ourInstance;
    }

    private CacheProvidersHelper() {
    }

    public UserCacheProviders getUserCacheProviders() {
        return userCacheProviders;
    }
}
