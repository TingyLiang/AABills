package com.shenyong.aabills.listdata;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.BaseBindingHolder;
import com.shenyong.aabills.R;
import com.shenyong.aabills.databinding.LayoutStatisticTypeBinding;

public class StatisticTypeData extends BaseHolderData<BaseBindingHolder<LayoutStatisticTypeBinding>> {

    public @ColorInt int mDotColor;
    public GradientDrawable mDotBg;
    public String mType;
    public String mPercent;
    public String mAmount;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_statistic_type;
    }

    @Override
    public void onBindView(BaseBindingHolder<LayoutStatisticTypeBinding> holder) {
        super.onBindView(holder);
        mDotBg = new GradientDrawable();
        mDotBg.setColor(mDotColor);
        mDotBg.setShape(GradientDrawable.OVAL);
        int size = holder.itemView.getResources().getDimensionPixelSize(R.dimen.margin_10dp);
        mDotBg.setSize(size, size);
        holder.mBinding.setData(this);
    }
}
