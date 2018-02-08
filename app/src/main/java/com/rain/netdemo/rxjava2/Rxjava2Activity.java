package com.rain.netdemo.rxjava2;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:rain
 * Date:2017/11/17 9:59
 * Description:
 */

public class Rxjava2Activity extends AbsBaseActivity implements View.OnClickListener {

    private TextView result;
    private Button consumer;
    private android.support.v7.widget.Toolbar toolbar;
    private StringBuffer stringBuffer = new StringBuffer();
    private static final String TAG = Rxjava2Activity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_rxjava;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        result = (TextView) findViewById(R.id.result);
        consumer = findViewById(R.id.consumer);
        consumer.setEnabled(false);

        consumer.setOnClickListener(this);

        initToolBar(toolbar, true, "Rxjava2Activity");

    }


    private void clearUI() {
        consumer.setEnabled(false);
        result.setText("");
        stringBuffer.setLength(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_rxjava, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        clearUI();
        switch (item.getItemId()) {
            case R.id.action_creat:
                simpleCreat();
                break;

            case R.id.action_just:
                justCreat();
                break;

            case R.id.action_fromIterable:
                iterableCreat();
                break;
            case R.id.action_interval:
                intervalCreat();
                break;

            case R.id.action_range:
                rangeCreat();
                break;


            case R.id.action_timer:
                timerCreat();
                break;

            case R.id.action_backpressure:  // 背压，即观察者处理不了被观察者事件的处理策略 todo 不知为何没有起作用
                consumer.setEnabled(true);
                backpressure();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private Subscription mSubscription;

    /**
     * 背压情形演示
     * 关于背压策略参见：http://www.jianshu.com/p/1f4867ce3c01
     */
    private void backpressure() {
        Flowable.create(new FlowableOnSubscribe<Integer>() {

            @Override
            public void subscribe(FlowableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 129; i++) {
                    e.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(50);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        stringBuffer.append(integer);
                        stringBuffer.append("\n");
                        result.setText(stringBuffer);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 延时发射,注意要指定线程
     */
    private void timerCreat() {
        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Exception {

                        stringBuffer.append(s);
                        stringBuffer.append("\n");
                        result.setText(stringBuffer);
                    }
                });
    }

    /**
     * 发送一段整数序列内的数据
     */
    private void rangeCreat() {
        Observable.range(1, 10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer s) throws Exception {
                stringBuffer.append(s);
                stringBuffer.append("\n");
                result.setText(stringBuffer);
            }
        });
    }

    /**
     * 间隔发射,注意默认不是main线程
     */
    private void intervalCreat() {

        Observable.interval(1, TimeUnit.SECONDS)
                .take(5)    // 输出指定数量的结果
                .doOnNext(new Consumer<Long>() {    // 每次发送前做一些别的事情
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.e(TAG, "accept: Threadname:" + Thread.currentThread().getName());
                        Log.e(TAG, "第" + aLong + "次");
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long s) throws Exception {

                        Log.e(TAG, "accept: Threadname:" + Thread.currentThread().getName());

                        stringBuffer.append(s);
                        stringBuffer.append("\n");
                        result.setText(stringBuffer);
                    }
                });

    }

    /**
     * 将集合遍历按顺序发送
     */
    private void iterableCreat() {
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            strings.add(i + "");
        }
        Observable.fromIterable(strings).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                stringBuffer.append(s);
                stringBuffer.append("\n");
                result.setText(stringBuffer);
            }
        });
    }


    /**
     * just是Observable按顺序发送到onNext()方法中
     */
    private void justCreat() {
        Observable.just("1", "2", "3")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        stringBuffer.append(s);
                        stringBuffer.append("\n");
                        result.setText(stringBuffer);
                    }
                });
    }


    /**
     * rxjava2基本创建使用
     */
    private void simpleCreat() {
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "accept: Threadname:" + Thread.currentThread().getName());

                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.e(TAG, "accept: Threadname:" + Thread.currentThread().getName());

                stringBuffer.append(integer);
                stringBuffer.append("\n");
                result.setText(stringBuffer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // 订阅，注意没有指定观察者和被观察者的线程，默认都在当前线程，即main线程。
        observable.subscribe(observer);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.consumer: // 点击消费50个事件
                mSubscription.request(50);
                break;
        }
    }
}
