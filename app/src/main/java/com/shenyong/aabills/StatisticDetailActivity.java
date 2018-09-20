package com.shenyong.aabills;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.sddy.baseui.BaseBindingActivity;
import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.DefaultItemDivider;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;
import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.databinding.ActivityStatisticDetailBinding;
import com.shenyong.aabills.listdata.StatisticTypeData;

import java.util.ArrayList;
import java.util.List;

public class StatisticDetailActivity extends BaseBindingActivity<ActivityStatisticDetailBinding> {

    private SimpleBindingAdapter mAdapter;
    private SimpleBindingAdapter mCostAdapter;
    private List<BaseHolderData> mTypesData = new ArrayList<>();
    private List<BaseHolderData> mUserCostData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic_detail);
        setTitle("2018年9月");
        setTitleBackIcon(R.drawable.ic_nav_back, R.color.white);
        mAdapter = new SimpleBindingAdapter();
        mBindings.rvStatisticTypesDetail.setAdapter(mAdapter);
        mBindings.rvStatisticTypesDetail.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        DefaultItemDivider itemDivider  =new DefaultItemDivider(this, DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = ViewUtils.getDrawableBg(R.color.transparent);
        drawable.setSize(0, getResources().getDimensionPixelSize(R.dimen.margin_20dp));
        itemDivider.setDrawable(drawable);
        mBindings.rvStatisticTypesDetail.addItemDecoration(itemDivider);

        mCostAdapter = new SimpleBindingAdapter();
        mBindings.rvStatisticUserCost.setAdapter(mCostAdapter);
        mBindings.rvStatisticUserCost.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBindings.rvStatisticUserCost.addItemDecoration(itemDivider);

        int[] colors = {0xFF2A82E4, 0xFF54BFC0, 0xFF5EBE67, 0xFFF4CC49, 0xFFE05667};
        StatisticTypeData type = new StatisticTypeData();
        type.mDotColor = colors[0];
        type.mType = "果蔬";
        type.mPercent = "23.53%";
        type.mAmount = String.format("%.1f", 37.8);
        mTypesData.add(type);
        type = new StatisticTypeData();
        type.mDotColor = colors[1];
        type.mType = "蛋肉";
        type.mPercent = "45.50%";
        type.mAmount = String.format("%.1f", 79f);
        mTypesData.add(type);
        type = new StatisticTypeData();
        type.mDotColor = colors[2];
        type.mType = "粮油";
        type.mPercent = "23.53%";
        type.mAmount = String.format("%.1f", 37.8);
        mTypesData.add(type);
        type = new StatisticTypeData();
        type.mDotColor = colors[3];
        type.mType = "网费";
        type.mPercent = "23.53%";
        type.mAmount = String.format("%.1f", 37.8);
        mTypesData.add(type);
        type = new StatisticTypeData();
        type.mDotColor = colors[4];
        type.mType = "日用品";
        type.mPercent = "23.53%";
        type.mAmount = String.format("%.1f", 37.8);
        mTypesData.add(type);
        mAdapter.updateData(mTypesData);
        mBindings.tvStatisticAvgCost.setText("月人均消费247.5元");


//        mUserCostData
    }
}
