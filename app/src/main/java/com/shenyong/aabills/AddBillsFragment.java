package com.shenyong.aabills;

import android.app.DatePickerDialog;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.DatePicker;

import com.sddy.baseui.BaseBindingFragment;
import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.DefaultItemDivider;
import com.sddy.baseui.recycler.IItemClickLisntener;
import com.sddy.baseui.recycler.SimpleRecyclerAdapter;
import com.sddy.baseui.recycler.databinding.SimpleBindingAdapter;
import com.sddy.utils.TimeUtils;
import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.databinding.FragmentAddBillBinding;
import com.shenyong.aabills.listdata.BillTypeData;

import java.util.ArrayList;
import java.util.List;

public class AddBillsFragment extends BaseBindingFragment<FragmentAddBillBinding>
        implements IItemClickLisntener<BillTypeData> {

    public static AddBillsFragment newInstance() {
        return new AddBillsFragment();
    }

    private SimpleBindingAdapter mAdapter;
    private List<BillTypeData> mTypeData = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_add_bill;
    }

    @Override
    protected void onCreatedView(View rootView, Bundle savedInstanceState) {
        setTitle(R.string.title_add_bill);
        mBinding.setPresenter(this);
        mBinding.viewAmountDateBg.setBackground(ViewUtils.getDrawableBg(R.color.white, R.dimen.margin_bigger));
        String[] billTypes = getResources().getStringArray(R.array.bill_types);
        for (String type : billTypes) {
            BillTypeData data = new BillTypeData(type);
            data.mClicklistener = this;
            mTypeData.add(data);
        }
        mAdapter = new SimpleBindingAdapter();
        mBinding.rvAddBillTypes.setAdapter(mAdapter);
        mBinding.rvAddBillTypes.setLayoutManager(new GridLayoutManager(getContext(), 3));
        GradientDrawable drawable = ViewUtils.getDrawableBg(R.color.transparent);
        drawable.setSize(1, getResources().getDimensionPixelSize(R.dimen.margin_big));
        DefaultItemDivider decoration = new DefaultItemDivider(getContext(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(drawable);
        mBinding.rvAddBillTypes.addItemDecoration(decoration);
        mAdapter.updateData(mTypeData);

        mBinding.tvAddBillDate.setText(TimeUtils.getCurrentDate());
    }

    @Override
    public void onClick(BillTypeData data, int position) {
        data.checkStatusChanged();
        for (BillTypeData typeData : mTypeData) {
            if (typeData != data && data.checked) {
                typeData.setChecked(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_add_bill_date:
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mBinding.tvAddBillDate.setText(String.format("%d-%02d-%02d", year, month+1, dayOfMonth));
                    }
                }, TimeUtils.getYear(), TimeUtils.getMonth(), TimeUtils.getDay());
                dialog.show();
                break;
            case R.id.btn_add_bill_ok:

                break;
            default:
                break;
        }
    }

}
