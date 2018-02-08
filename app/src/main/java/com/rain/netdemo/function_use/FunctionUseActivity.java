package com.rain.netdemo.function_use;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;

/**
 * Author:rain
 * Date:2017/11/22 16:03
 * Description:
 */

public class FunctionUseActivity extends AbsBaseActivity implements View.OnClickListener {

    private Button includeUse;

    @Override
    public int getLayoutId() {
        return R.layout.activity_functionuse;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        includeUse = findViewById(R.id.include_use);

        includeUse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_use:
                startActivity(new Intent(this,IncludeUseActivity.class));
        }
    }
}
