package com.sddy.baseui.recycler.databinding;

import android.databinding.ViewDataBinding;

import com.sddy.baseui.recycler.BaseHolder;

public class BaseBindingHolder<VDB extends ViewDataBinding> extends BaseHolder {

    public VDB mBinding;

    public BaseBindingHolder(VDB databinding) {
        super(databinding.getRoot());
        mBinding = databinding;
    }
}
