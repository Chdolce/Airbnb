package com.xykj.demo.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xykj.demo.R;
import com.xykj.demo.activity.Datechoose;
import com.xykj.demo.activity.Guestchoose;
import com.xykj.demo.adapter.MonthAdapter;
import com.xykj.demo.fragment.BrowseFragment;
import com.xykj.demo.view.bean.DateRange;
import com.xykj.demo.view.util.CalendarUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by blanke on 16-12-15.
 */

public class AirCalendarView extends FrameLayout {
    public interface OnSelectedDayListener {
        void onDaySelected(Calendar startDay, Calendar endDay);
    }

    private RecyclerView mRecyclerView;
    private Calendar toDay;
    private MonthAdapter mMonthAdapter;
    private DateRange dataRange;
    private int toDayYear;
    private int toDayMonth;
    private int toDayPosition;//今天这个月的position
    private TextView startDateTv, endDateTv;
    private String StartDay;
    private String EndDay;
    private Button confirm;
    private Context mContext;
    private Intent mintent;
    public AirCalendarView(Context context) {
        super(context);
        mintent = new Intent();
        mContext = context;
        mintent.putExtra("Start",StartDay);
        mintent.putExtra("End",EndDay);
        init(null);
    }

    public AirCalendarView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        mintent = new Intent();
        mContext = context;
        mintent.putExtra("Start",StartDay);
        mintent.putExtra("End",EndDay);
        init(attrs);
    }

    public AirCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mintent = new Intent();
        mContext = context;
        mintent.putExtra("Start",StartDay);
        mintent.putExtra("End",EndDay);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.AirCalendarView);
        int visibleYearCount = typedArray.getInt(R.styleable.AirCalendarView_visibleYearCount, 1);
        int daySelectOffset = typedArray.getInt(R.styleable.AirCalendarView_daySelectOffset, 1);
        //未来选择 没有用
        boolean isSelectFuture = typedArray.getBoolean(R.styleable.AirCalendarView_isSelectFuture, false);
        typedArray.recycle();
        dataRange = new DateRange(isSelectFuture, daySelectOffset);
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.view_aircalendarview, this, false);
        addView(rootView);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.view_aircalendar_rv_date);
        startDateTv = (TextView) rootView.findViewById(R.id.view_aircalendar_tv_start_date);
        endDateTv = (TextView) rootView.findViewById(R.id.view_aircalendar_tv_end_date);
//        confirm = (Button)rootView.findViewById(R.id.rb_receive);
//        confirm.setOnClickListener(new OnClickListener() {
//            /**
//             * Called when a view has been clicked.
//             *
//             * @param v The view that was clicked.
//             */
//            @Override
//            public void onClick(View v) {
//                mintent.setClass(mContext,BrowseFragment.class);
//                mContext.startActivity(mintent);
//                //finish();
//            }
//        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        toDay = CalendarUtils.getToDay();
        toDayYear = toDay.get(Calendar.YEAR);
        toDayMonth = toDay.get(Calendar.MONTH);
        List<Calendar> years = new ArrayList<>();
        if (isSelectFuture()) {
            for (int i = 1; i < visibleYearCount; i++) {
                years.addAll(getYearMonths(i - 1 + toDayYear));
            }
            toDayPosition = toDayMonth;
        } else {
            toDayPosition = toDayMonth + 12 * (visibleYearCount - 1);
            for (int i = visibleYearCount; i > 0; i--) {
                years.addAll(getYearMonths(-i + 1 + toDayYear));
            }
        }
        mMonthAdapter = new MonthAdapter(years, dataRange, mRecyclerView);
        mRecyclerView.setAdapter(mMonthAdapter);
        mRecyclerView.scrollToPosition(toDayPosition);

    }


    private List<Calendar> getYearMonths(int year) {
        List<Calendar> months = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Calendar calendar = CalendarUtils.getCalendar(year, i, 1);
            if (year == toDayYear && i > toDayMonth) {
                break;
            }
            months.add(calendar);
        }
        return months;
    }


    public Calendar getStartDate() {
        return mMonthAdapter.getDateRange().getStartDate();
    }

    public Calendar getEndDate() {
        return mMonthAdapter.getDateRange().getEndDate();
    }

    public void clearSelect() {
        mMonthAdapter.clearSelect();
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public boolean isSelectFuture() {
        return dataRange.isSelectFuture();
    }

    public int getDaySelectOffset() {
        return dataRange.getDaySelectOffset();
    }

    public OnSelectedDayListener getOnSelectedDayListener() {
        return mMonthAdapter.getOnSelectedDayListener();
    }

    private String getDateStr(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void setOnSelectedDayListener(final OnSelectedDayListener onSelectedDayListener) {
        this.mMonthAdapter.setOnSelectedDayListener(new OnSelectedDayListener() {
            @Override
            public void onDaySelected(Calendar startDay, Calendar endDay) {
                StartDay = getDateStr(startDay);
                EndDay = getDateStr(endDay);
                startDateTv.setText(getDateStr(startDay));
                endDateTv.setText(getDateStr(endDay));
                onSelectedDayListener.onDaySelected(startDay, endDay);
            }
        });
    }
}
