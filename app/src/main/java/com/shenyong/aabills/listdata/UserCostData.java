package com.shenyong.aabills.listdata;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.BaseBindingHolder;
import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.R;
import com.shenyong.aabills.databinding.LayoutUserCostItemBinding;

public class UserCostData extends BaseHolderData<BaseBindingHolder<LayoutUserCostItemBinding>> {

    public int mColorRes;
    public GradientDrawable mNameBg;
    public String mName;
    public double mCost;
    public String mCostStr;
    /** 应支付或收取金额 */
    public String mPayOrGet;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_user_cost_item;
    }

    @Override
    public void onBindView(BaseBindingHolder<LayoutUserCostItemBinding> holder) {
        super.onBindView(holder);
        mNameBg = ViewUtils.getDrawableBg(mColorRes);
        int size = holder.itemView.getResources().getDimensionPixelSize(R.dimen.circle_head_size_in_ost);
        mNameBg.setShape(GradientDrawable.OVAL);
        mNameBg.setSize(size, size);
        mCostStr = holder.itemView.getResources().getString(R.string.statistic_user_cost, mCost);
        holder.mBinding.setData(this);
    }
}
