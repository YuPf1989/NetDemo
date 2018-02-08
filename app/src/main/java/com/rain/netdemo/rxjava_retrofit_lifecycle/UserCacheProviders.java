package com.rain.netdemo.rxjava_retrofit_lifecycle;

import com.rain.netdemo.bean.NewsListBean;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * Author:rain
 * Date:2018/2/7 15:47
 * Description:rxCache网络缓存封装的接口
 */

public interface UserCacheProviders {

    /**
     * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider,EvictDynamicKey or EvictDynamicKeyGroup .
     * Reply如果需要知道返回的结果是来自哪里(本地,内存还是网络),是否加密,则可以使用Observable<Reply<List<Repo>>>作为方法的返回值,这样RxCache则会使用Reply包装结果
     * @param oData
     * @param pageId 驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
     * @param evictDynamicKey   可以明确地清理指定的数据 DynamicKey.
     * @return
     */
    @LifeCache(duration = 1,timeUnit = TimeUnit.HOURS)
    Observable<Reply<NewsListBean>> getWikiList(Observable<NewsListBean> oData);
}
