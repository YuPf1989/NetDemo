package com.rain.netdemo.function_use;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;

/**
 * Author:rain
 * Date:2017/11/22 16:08
 * Description:
 */

public class IncludeUseActivity extends AbsBaseActivity {

    private LinearLayout parent;
    private static final String TAG  = IncludeUseActivity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_includeuse;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        parent = findViewById(R.id.parent);
        Log.e(TAG, "initViews: parent.id"+parent.getId());

//        View inflate = LayoutInflater.from(this).inflate(R.layout.layout_text, null, false);

        // 参数说明：参数1，要填充的子view布局；参数2，子view要填充到的父view；参数3，子view是否添加到父view
        // 如果参数3为true，返回值为父view，如果为false则返回子view
//        View inflate1 = LayoutInflater.from(this).inflate(R.layout.layout_text, parent, true);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.layout_text, parent, false);

//        Log.e(TAG, "initViews: id"+inflate.getId());
        Log.e(TAG, "initViews: id"+inflate2.getId());
    }
}
