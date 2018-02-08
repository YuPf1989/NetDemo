package com.rain.netdemo.widget.timeline;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.rain.netdemo.AbsBaseActivity;
import com.rain.netdemo.R;
import com.rain.netdemo.widget.timeline.model.OrderStatus;
import com.rain.netdemo.widget.timeline.model.TimeLineModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:rain
 * Date:2017/11/21 11:43
 * Description:
 */

public class TimeLineActivity extends AbsBaseActivity {

    private RecyclerView mRecyclerView;
    private Toolbar toolbar;
    private List<TimeLineModel> mDataList = new ArrayList<>();
    private TimeLineAdapter1 adapter1;
    private TimeLineAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_timeline;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.recyclerView);
        toolbar = findViewById(R.id.toolbar);

        initToolBar(toolbar,true,"Timeline");

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setHasFixedSize(true);

        initData();
    }

    private void initData() {
        setDataListItems();

        adapter1 = new TimeLineAdapter1( mDataList);
        adapter = new TimeLineAdapter(R.layout.item_timeline_line_padding, mDataList);
        mRecyclerView.setAdapter(adapter1);
//        mRecyclerView.setAdapter(adapter);

    }

    private void setDataListItems(){
        mDataList.add(new TimeLineModel("Item successfully delivered", "", OrderStatus.INACTIVE));
        mDataList.add(new TimeLineModel("Courier is out to delivery your order", "2017-02-12 08:00", OrderStatus.ACTIVE));
        mDataList.add(new TimeLineModel("Item has reached courier facility at New Delhi", "2017-02-11 21:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Item has been given to the courier", "2017-02-11 18:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Item is packed and will dispatch soon", "2017-02-11 09:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Order is being readied for dispatch", "2017-02-11 08:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Order processing initiated", "2017-02-10 15:00", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Order confirmed by seller", "2017-02-10 14:30", OrderStatus.COMPLETED));
        mDataList.add(new TimeLineModel("Order placed successfully", "2017-02-10 14:00", OrderStatus.COMPLETED));
    }
}
