package com.rain.netdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rain.netdemo.function_use.FunctionUseActivity;
import com.rain.netdemo.rxbus.RxBusActivity;
import com.rain.netdemo.rxjava2.Rxjava2Activity;
import com.rain.netdemo.rxjava2.RxjavaBindingActivity;
import com.rain.netdemo.rxjava_retrofit_lifecycle.RetrofitActivity;
import com.rain.netdemo.widget.WidgetActivity;

public class MainActivity extends AbsBaseActivity implements View.OnClickListener {

    private Button btnRetrofit;
    private Button rxjava2;
    private Button rxBus;
    // 使用rxjava进行控件的绑定
    private Button rxBtn;
    private Button widget;
    private Button functionUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        btnRetrofit = (Button) findViewById(R.id.btn_1);
        rxjava2 = (Button) findViewById(R.id.btn_2);
        rxBus = findViewById(R.id.btn_3);
        rxBtn = findViewById(R.id.btn_4);
        widget = findViewById(R.id.btn_5);
        functionUse = findViewById(R.id.btn_6);

        btnRetrofit.setOnClickListener(this);
        rxjava2.setOnClickListener(this);
        rxBtn.setOnClickListener(this);
        widget.setOnClickListener(this);
        functionUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1:
                startActivity(new Intent(this, RetrofitActivity.class));
                break;

            case R.id.btn_2:
                startActivity(new Intent(this, Rxjava2Activity.class));
                break;

            case R.id.btn_3:
                startActivity(new Intent(this, RxBusActivity.class));
                break;

            case R.id.btn_4:
                startActivity(new Intent(this, RxjavaBindingActivity.class));
                break;

            case R.id.btn_5:
                startActivity(new Intent(this, WidgetActivity.class));
                break;

            case R.id.btn_6:
                startActivity(new Intent(this, FunctionUseActivity.class));
                break;
        }
    }


}
