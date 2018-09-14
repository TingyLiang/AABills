package com.sddy.baseui;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;

import java.util.ArrayList;
import java.util.List;

public class BaseListFragment extends BaseFragment {

    private RecyclerView mRvList;
    private List<BaseHolderData> mListData = new ArrayList<>();
    private SimpleBindingAdapter mAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_base_list;
    }

    @Override
    protected void onCreatedView(View rootView, Bundle savedInstanceState) {
        mRvList = rootView.findViewById(R.id.rv_base_fragment_list);
        mAdapter = new SimpleBindingAdapter();
        mRvList.setAdapter(mAdapter);
        mRvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
    }

    protected void updateData(List<BaseHolderData> dataList) {
        mAdapter.updateData(dataList);
    }

    public void setBackgroundColor(@ColorInt int color) {
        if (mRvList != null) {
            mRvList.setBackgroundColor(color);
        }
    }
}
