package com.rain.netdemo.widget.timeline;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rain.netdemo.R;
import com.rain.netdemo.widget.timeline.model.OrderStatus;
import com.rain.netdemo.widget.timeline.model.TimeLineModel;

import java.util.List;

/**
 * Author:rain
 * Date:2017/11/22 11:33
 * Description:
 */

public class TimeLineAdapter extends BaseQuickAdapter<TimeLineModel,BaseViewHolder> {

    public TimeLineAdapter(int layoutResId, @Nullable List<TimeLineModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TimeLineModel item) {

        if(item.getStatus() == OrderStatus.INACTIVE) {
//            helper.mTimelineView.setMarker(mContext.getDrawable(R.drawable.ic_marker_inactive));
        } else if(item.getStatus() == OrderStatus.ACTIVE) {
//            helper.mTimelineView.setMarker(mContext.getDrawable(R.drawable.ic_marker_active));
//            helper.mTimelineView.setMarker(R.id.timeline,VectorDrawableUtils.getDrawable(mContext, R.drawable.ic_marker_active, R.color.colorPrimary));
        } else {
//            helper.mTimelineView.setMarker(mContext.getDrawable(R.drawable.ic_marker));
//            helper.mTimelineView.setMarker(R.id.timeline,ContextCompat.getDrawable(mContext, R.drawable.ic_marker), ContextCompat.getColor(mContext, R.color.colorPrimary));
        }

        if (!item.getDate().isEmpty()) {
            helper.setGone(R.id.text_timeline_date, true);
            helper.setText(R.id.text_timeline_date, item.getDate());
        } else {
            helper.setGone(R.id.text_timeline_date, false);
        }

        helper.setText(R.id.text_timeline_title,item.getMessage());

    }
}
