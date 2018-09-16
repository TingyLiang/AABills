package com.shenyong.aabills.listdata;

import android.support.annotation.DrawableRes;

import com.sddy.baseui.recycler.BaseHolderData;
import com.sddy.baseui.recycler.databinding.BaseBindingHolder;
import com.shenyong.aabills.AABilsApp;
import com.shenyong.aabills.R;
import com.shenyong.aabills.databinding.LayoutNoContentItemBinding;

public class EmptyData extends BaseHolderData<BaseBindingHolder<LayoutNoContentItemBinding>> {

    public @DrawableRes
    int mIconRes;
    public String mDesc;

    public EmptyData() {
        mIconRes = R.drawable.ic_no_record;
        mDesc = AABilsApp.getInstance().getResources().getString(R.string.no_record);
    }

    public EmptyData(int mIconRes, String mDesc) {
        this.mIconRes = mIconRes;
        this.mDesc = mDesc;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.layout_no_content_item;
    }

    @Override
    public void onBindView(BaseBindingHolder<LayoutNoContentItemBinding> holder) {
        if (mIconRes != 0) {
            holder.setImageResource(R.id.iv_no_content_img, mIconRes);
        }
        holder.mBinding.setData(this);
    }
}
