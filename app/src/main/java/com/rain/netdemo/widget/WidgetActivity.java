package com.rain.netdemo.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;
import com.rain.netdemo.widget.timeline.TimeLineActivity;

/**
 * Author:rain
 * Date:2017/11/21 11:37
 * Description:
 */

public class WidgetActivity extends AbsBaseActivity implements View.OnClickListener {

    private Button timeLine;

    @Override
    public int getLayoutId() {
        return R.layout.activity_widget;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        timeLine = findViewById(R.id.timeline);

        timeLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.timeline:
                startActivity(new Intent(this,TimeLineActivity.class));
                break;
        }
    }
}
