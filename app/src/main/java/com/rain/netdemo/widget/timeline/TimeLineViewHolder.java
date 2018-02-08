package com.rain.netdemo.widget.timeline;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.github.vipulasri.timelineview.TimelineView;
import com.rain.netdemo.R;


/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineViewHolder extends BaseViewHolder {

    protected TextView mDate;
    protected TextView mMessage;
    protected TimelineView mTimelineView;

    public TimeLineViewHolder(View itemView, int viewType) {
        super(itemView);

        mDate = itemView.findViewById(R.id.text_timeline_date);
        mMessage = itemView.findViewById(R.id.text_timeline_title);
        mTimelineView = itemView.findViewById(R.id.time_marker);
        mTimelineView.initLine(viewType);
    }
}
