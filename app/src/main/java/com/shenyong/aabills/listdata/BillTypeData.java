package com.shenyong.aabills.listdata;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.BaseBindingHolder;
import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.R;
import com.shenyong.aabills.databinding.LayoutBillTypeItemBinding;

public class BillTypeData extends BaseHolderData<BaseBindingHolder<LayoutBillTypeItemBinding>> {

    public String mDesc;
    public boolean checked;
    public Drawable mItemBg;
    public ColorStateList mTextColor;

    public BillTypeData(String type) {
        mDesc = type;
        mItemBg = ViewUtils.getDrawableBg(R.color.white, R.color.main_blue, R.dimen.margin_large);
        mTextColor = ViewUtils.getCheckableColors(R.color.text_color_gray, R.color.white);
    }

    @Override
    public int getLayoutRes() {
    return R.layout.layout_bill_type_item;
    }

    @Override
    public void onBindView(BaseBindingHolder<LayoutBillTypeItemBinding> holder) {
        super.onBindView(holder);
        holder.mBinding.setData(this);
    }

    public void checkStatusChanged() {
        checked = !checked;
        refreshUi();
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
        refreshUi();
    }
}
