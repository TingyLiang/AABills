package com.shenyong.aabills;

import android.os.Bundle;

import com.sddy.baseui.BaseActivity;
import com.sddy.baseui.BaseListActivity;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;
import com.shenyong.aabills.listdata.BillRecordData;

import java.util.List;

public class BillsListActivity extends BaseListActivity {

    private static final int TYPE_MONTH = 1;
    private static final int TYPE_YEAR = 2;

    private static final String KEY_TIME = "stat.key.time";
    private static final String KEY_TYPE = "stat.key.type";
    private static final String KEY_START = "stat.key.start";
    private static final String KEY_END = "stat.key.end";
    private static final String KEY_YEAR = "stat.key.year";

    private String mTitle;
    private int mType;
    private long mStartTime;
    private long mEndTime;
    private int mYear;
    private BillsListViewModel mViewModel;

    public static void showThisPage(BaseActivity activity, String title, long startTime, long endTime) {
        Bundle data = new Bundle();
        data.putInt(KEY_TYPE, TYPE_MONTH);
        data.putString(KEY_TIME, title);
        data.putLong(KEY_START, startTime);
        data.putLong(KEY_END, endTime);
        activity.startActivity(BillsListActivity.class, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addDefaultDivider();
        Bundle data = getIntent().getExtras();
        if (data == null) {
            finish();
        }
        mTitle = data.getString(KEY_TIME, "");
        mType = data.getInt(KEY_TYPE);
        mStartTime = data.getLong(KEY_START);
        mEndTime = data.getLong(KEY_END);
        mYear = data.getInt(KEY_YEAR);

        setTitle(mTitle);
        setTitleBackIcon(R.drawable.ic_nav_back, R.color.white);

        mViewModel = new BillsListViewModel();
        loadData();
    }

    private void loadData() {
        mViewModel.loadBills(mStartTime, mEndTime, new BillsListViewModel.BillsListLoadCallback() {
            @Override
            public void onComplete(List<BillRecordData> bills) {
                updateData(bills);
            }
        });
    }
}
