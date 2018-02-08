package com.rain.netdemo.rxbus;

import io.reactivex.subjects.Subject;

/**
 * Author:rain
 * Date:2017/11/17 17:09
 * Description:
 */

public class RxBus {
//    private final Subject<Object> bus;
    private static RxBus instance;
    private void RxBus(){};
    public static RxBus getInstance() {
        return instance == null ? new RxBus() : instance;
    }

    /**
     * 发送一个事件
     *
     * @param o
     */
    public void post(Object o) {
//        bus.onNext(o);
    }
}
