package com.shenyong.aabills;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sddy.baseui.BaseFragment;
import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;
import com.sddy.utils.ArrayUtils;
import com.sddy.utils.TimeUtils;
import com.sddy.utils.ViewUtils;
import com.sddy.utils.log.Log;
import com.shenyong.aabills.listdata.BillRecordData;
import com.shenyong.aabills.listdata.EmptyData;
import com.shenyong.aabills.room.BillDatabase;
import com.shenyong.aabills.room.BillRecord;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class StatisticFragment extends BaseFragment {

    public static StatisticFragment newInstance() {
        return new StatisticFragment();
    }

    private TabLayout mTabIndicator;
    private RecyclerView mRvRecordList;
    private SimpleBindingAdapter mAdapter;
    private List<BaseHolderData> mListData = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_statistic;
    }

    @Override
    protected void onCreatedView(View rootView, Bundle savedInstanceState) {
        hideTitleBar();
        mTabIndicator = rootView.findViewById(R.id.tl_stat_mon_year_indicator);
        mRvRecordList = rootView.findViewById(R.id.rv_stat_mon_year_record);
        mTabIndicator.addTab( mTabIndicator.newTab().setText(R.string.statistic_month));
        mTabIndicator.addTab( mTabIndicator.newTab().setText(R.string.statistic_year));
        mTabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mAdapter = new SimpleBindingAdapter();
        mRvRecordList.setAdapter(mAdapter);
        mRvRecordList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = ViewUtils.getDrawableBg(R.color.line_gray);
        int size = getResources().getDimensionPixelSize(R.dimen.line_width_half);
        drawable.setSize(size, size);
        decoration.setDrawable(drawable);
        mRvRecordList.addItemDecoration(decoration);
    }

    @Override
    public void onResume() {
        super.onResume();
        mListData.clear();
        Observable.create(new ObservableOnSubscribe<BillRecordData>() {
            @Override
            public void subscribe(ObservableEmitter<BillRecordData> emitter) throws Exception {
                List<BillRecord> bills = BillDatabase.getInstance().billDao().getAllBills();
                if (!ArrayUtils.isEmpty(bills)) {
                    for (BillRecord billRecord : bills) {
                        BillRecordData data = new BillRecordData();
                        data.mAmount = String.format("%.1f元", billRecord.mAmount);
                        data.mTime = TimeUtils.getDateString(billRecord.mTimestamp, "yyyy年MM月dd日");
                        data.mType = "主要消费：" + billRecord.mType;
                        emitter.onNext(data);
                    }
                }
                emitter.onComplete();
            }
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<BillRecordData>() {
            @Override
            public void accept(BillRecordData billRecordData) throws Exception {
                mListData.add(billRecordData);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                throwable.printStackTrace();
                if (ArrayUtils.isEmpty(mListData)) {
                    EmptyData emptyData = new EmptyData();
                    mListData.add(emptyData);
                }
                mAdapter.updateData(mListData);
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                if (ArrayUtils.isEmpty(mListData)) {
                        EmptyData emptyData = new EmptyData();
                        mListData.add(emptyData);
                    }
                    mAdapter.updateData(mListData);
                }
            });
    }
}
