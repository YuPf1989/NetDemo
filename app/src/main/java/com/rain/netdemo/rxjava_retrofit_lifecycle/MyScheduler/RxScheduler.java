package com.rain.netdemo.rxjava_retrofit_lifecycle.MyScheduler;

import android.content.Context;
import android.widget.Toast;

import com.rain.netdemo.MyApplication;
import com.rain.netdemo.rxjava_retrofit_lifecycle.Dialog.LoadingDialog;
import com.rain.netdemo.utils.NetworkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/8/26/026.
 */

public class RxScheduler {


    public static <T> ObservableTransformer<T, T> io_main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())//指定联网请求的线程，事件产生的线程
                        .unsubscribeOn(Schedulers.io())//指定取消订阅的线程
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {

                                if (!NetworkUtils.isNetAvailable(MyApplication.getInstance())) {
                                    Toast.makeText(MyApplication.getInstance(), "请检查您的网络状态", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())//指定doonSubscribe的线程
                        .observeOn(AndroidSchedulers.mainThread())//指定doOnTerminate的线程
                        ;
            }
        };
    }

    public static <T> ObservableTransformer<T, T> io_main_showDialog(final Context act) {

        return new ObservableTransformer<T, T>() {

            private LoadingDialog dialog;

            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream

                        .subscribeOn(Schedulers.io())//指定联网请求的线程，事件产生的线程
                        .unsubscribeOn(Schedulers.io())//指定取消订阅的线程
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {//当订阅的时候调用

                                dialog = new LoadingDialog(act);
                                dialog.setCanceledOnTouchOutside(false);
                                dialog.show();

                            }
                        })
                        .subscribeOn(AndroidSchedulers.mainThread())//指定doonSubscribe的线程
                        .observeOn(AndroidSchedulers.mainThread())//指定doOnTerminate的线程
                        .doOnTerminate(new Action() {
                            @Override
                            public void run() throws Exception {//当事件流结束的时候调用，不管是失败，还是完成，都会走这个方法
                                if (dialog != null) {
                                    dialog.dismiss();
                                    dialog = null;
                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread());//指定事件消费的线程，更新UI
            }
        };
    }

}
