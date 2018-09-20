package com.shenyong.aabills;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sddy.baseui.BaseFragment;
import com.sddy.baseui.dialog.DialogFactory;
import com.sddy.baseui.dialog.MsgDialog;
import com.sddy.baseui.dialog.MsgToast;
import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.DefaultItemDivider;
import com.sddy.baseui.recycler.IItemClickLisntener;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;
import com.sddy.utils.ArrayUtils;
import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.listdata.BillRecordData;
import com.shenyong.aabills.listdata.EmptyData;
import com.shenyong.aabills.room.BillsDataSource;

import java.util.ArrayList;
import java.util.List;

public class StatisticFragment extends BaseFragment {

    public static StatisticFragment newInstance() {
        return new StatisticFragment();
    }

    private TabLayout mTabIndicator;
    private RecyclerView mRvRecordList;
    private SimpleBindingAdapter mAdapter;
    private List<BaseHolderData> mListData = new ArrayList<>();

    private BillStatisticsViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new BillStatisticsViewModel();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_statistic;
    }

    @Override
    protected void onCreatedView(View rootView, Bundle savedInstanceState) {
        hideTitleBar();
        mTabIndicator = rootView.findViewById(R.id.tl_stat_mon_year_indicator);
        mRvRecordList = rootView.findViewById(R.id.rv_stat_mon_year_record);
        mTabIndicator.addTab(mTabIndicator.newTab().setText(R.string.statistic_month));
        mTabIndicator.addTab(mTabIndicator.newTab().setText(R.string.statistic_year));
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
        DefaultItemDivider decoration = new DefaultItemDivider(getContext(), DividerItemDecoration.VERTICAL);
        GradientDrawable drawable = ViewUtils.getDrawableBg(R.color.line_gray);
        int size = getResources().getDimensionPixelSize(R.dimen.line_width_half);
        drawable.setSize(size, size);
        decoration.setDrawable(drawable);
        mRvRecordList.addItemDecoration(decoration);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    public void onShow() {
        super.onShow();
        if (isAdded()) {
            updateList();
        }
    }

    private void updateList() {
        mListData.clear();
        mViewModel.loadBills(new BillsDataSource.LoadBillsCallback<BillRecordData>() {
            @Override
            public void onBillsLoaded(List<BillRecordData> bills) {
                for (BillRecordData recordData : bills) {
//                    recordData.mLongClickListener = mLongClickListener;
                    recordData.mClicklistener = mClickListener;
                    mListData.add(recordData);
                }
//                mListData.addAll(bills);
                mAdapter.updateData(mListData);
            }

            @Override
            public void onDataNotAvailable() {
                if (ArrayUtils.isEmpty(mListData)) {
                    EmptyData emptyData = new EmptyData();
                    mListData.add(emptyData);
                }
                mAdapter.updateData(mListData);
            }
        });
    }

    private IItemClickLisntener<BillRecordData> mClickListener = new IItemClickLisntener<BillRecordData>() {
        @Override
        public void onClick(final BillRecordData data, int position) {
            startActivity(StatisticDetailActivity.class);
        }
    };

    private IItemClickLisntener<BillRecordData> mLongClickListener = new IItemClickLisntener<BillRecordData>() {
        @Override
        public void onClick(final BillRecordData data, int position) {
            MsgDialog dialog = DialogFactory.confirmDialogWithoutTitle("确定删除该条记录？(" + data.toString() + ")",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewModel.delBill(data, new BillsDataSource.DelBillCallback() {
                            @Override
                            public void onDeleteSuccess() {
                                MsgToast.centerToast("已删除选择的账单记录");
                                mListData.remove(data);
                                mAdapter.updateData(mListData);
                            }

                            @Override
                            public void onDeleteFailed() {

                            }
                        });
                    }
            });


            dialog.show(getFragmentManager()   );
        }
    };
}
