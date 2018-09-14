package com.sddy.baseui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseBindingFragment<VDB extends ViewDataBinding> extends BaseFragment {

    protected VDB mBinding;

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View pageRoot = super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.bind(mContentRoot);
        return pageRoot;
    }
}
