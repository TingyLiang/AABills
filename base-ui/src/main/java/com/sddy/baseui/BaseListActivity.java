package com.sddy.baseui;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.DefaultItemDivider;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;
import com.sddy.utils.ViewUtils;

import java.util.List;

public class BaseListActivity extends BaseActivity {

    private RecyclerView mRvList;
    private SimpleBindingAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);

        initView();
    }

    private void initView() {
        mRvList = findViewById(R.id.rv_base_activity_list);
        mAdapter = new SimpleBindingAdapter();
        mRvList.setAdapter(mAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    protected void updateData(List<BaseHolderData> dataList) {
        mAdapter.updateData(dataList);
    }

    public void setBackgroundColor(@ColorInt int color) {
        if (mRvList != null) {
            mRvList.setBackgroundColor(color);
        }
    }

    protected void addDefaultDivider() {
        if (mRvList != null) {
            GradientDrawable drawable = ViewUtils.getDrawableBg(R.color.line_gray);
            drawable.setSize(getResources().getDimensionPixelSize(R.dimen.line_width_half),
                    getResources().getDimensionPixelSize(R.dimen.line_width_half));
            DefaultItemDivider decoration = new DefaultItemDivider(mRvList.getContext(), DividerItemDecoration.VERTICAL);
            decoration.setDrawable(drawable);
            mRvList.addItemDecoration(decoration);
        }
    }

    protected void setListTopOffset(@DimenRes int topOffset) {
        if (mRvList != null) {
            mRvList.setPadding(mRvList.getPaddingLeft(), getResources().getDimensionPixelSize(topOffset),
                    mRvList.getPaddingRight(), mRvList.getPaddingBottom());
        }
    }
}
