package com.rain.netdemo.rxjava2;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Author:rain
 * Date:2017/11/20 9:17
 * Description:
 */

public class RxjavaBindingActivity extends AbsBaseActivity {

    private EditText input;
    private TextView content;
    private Button getCode;
    private Button getCode2;
    private Button login;
    private static final String TAG = RxjavaBindingActivity.class.getSimpleName();
    private EditText name;
    private EditText pwd;
    private Disposable disposable;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rx_binding;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        input = findViewById(R.id.input);
        content = findViewById(R.id.content);
        getCode = findViewById(R.id.get_code);
        getCode2 = findViewById(R.id.get_code2);
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        login = findViewById(R.id.login);

        // 使用rxjava获取验证码
        getCode2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                        .take(10)
                        .compose(RxjavaBindingActivity.this.<Long>bindUntilEvent(ActivityEvent.DESTROY))    // activityx销毁时解绑
                        .subscribe(new Observer<Long>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Long aLong) {
                                getCode2.setEnabled(false);
                                getCode2.setText("剩余" + (10 - aLong) + "秒");
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {
                                getCode2.setEnabled(true);
                                getCode2.setText("获取验证码");
                            }
                        });

            }
        });

        // 使用rxjava进行数据绑定
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(final ObservableEmitter<String> e) throws Exception {
                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        e.onNext(s.toString());
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        content.setText(s);
                    }
                });

        // 使用rxbinding模拟获取验证码
        disposable = RxView.clicks(getCode)
                .throttleFirst(10, TimeUnit.SECONDS)    // 防止10s内连续点击
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        RxView.enabled(getCode).accept(false);
                        Log.e(TAG, "accept: enabled");
                    }
                })
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Observable.interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                                .take(10)
                                .subscribe(new Observer<Long>() {
                                    @Override
                                    public void onSubscribe(Disposable d) {

                                    }

                                    @Override
                                    public void onNext(Long aLong) {
                                        try {
                                            RxTextView.text(getCode).accept(("剩余" + (10 - aLong) + "秒"));
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onComplete() {
                                        try {
                                            RxTextView.text(getCode).accept("获取验证码");
                                            RxView.enabled(getCode).accept(true);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                });
                    }
                });

        // 模拟登录的表单验证
        InitialValueObservable<CharSequence> observableName = RxTextView.textChanges(name);
        InitialValueObservable<CharSequence> observablePwd = RxTextView.textChanges(pwd);

        Observable.combineLatest(observableName, observablePwd, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence name, CharSequence pwd) throws Exception {
                return isPasswordValid(pwd.toString()) && isPhoneValid(name.toString());
            }
        })
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean b) throws Exception {
                        RxView.enabled(login).accept(b);
                    }
                });

        RxView.clicks(login)
                .throttleFirst(1, TimeUnit.SECONDS) // 防抖
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(RxjavaBindingActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Boolean isPhoneValid(String phone) {
        return phone.length() == 11;
    }

    private Boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 防止rx持有的view没有释放导致的内存泄漏
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
