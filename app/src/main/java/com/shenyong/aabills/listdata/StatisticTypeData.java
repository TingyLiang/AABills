package com.shenyong.aabills.listdata;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.BaseBindingHolder;
import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.R;
import com.shenyong.aabills.databinding.LayoutStatisticTypeBinding;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StatisticTypeData extends BaseHolderData<BaseBindingHolder<LayoutStatisticTypeBinding>> {

    public @ColorRes int mDotColor;
    public GradientDrawable mDotBg;
    public String mType;
    // 0 ~ 1.0
    public double mPercent;
    public String mPercentStr;
    public double mAmount;
    public String mAmountStr;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_statistic_type;
    }

    @Override
    public void onBindView(BaseBindingHolder<LayoutStatisticTypeBinding> holder) {
        super.onBindView(holder);
        mDotBg = ViewUtils.getDrawableBg(mDotColor);
        mDotBg.setShape(GradientDrawable.OVAL);
        int size = holder.itemView.getResources().getDimensionPixelSize(R.dimen.margin_10dp);
        mDotBg.setSize(size, size);
        DecimalFormat format = new DecimalFormat("0.00%");
        format.setRoundingMode(RoundingMode.DOWN);
        mPercentStr = format.format(mPercent);
        mAmountStr = String.format("%.1f", mAmount);
        holder.mBinding.setData(this);
    }
}
