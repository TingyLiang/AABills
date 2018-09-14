package com.sddy.baseui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseBindingActivity<VDB extends ViewDataBinding> extends BaseActivity {

    protected VDB mBindings;

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        bindView(view);
    }

    private void bindView(View view) {
        mBindings = DataBindingUtil.bind(view);
    }
}
