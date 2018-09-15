package com.sddy.baseui.recycler;

import android.support.annotation.LayoutRes;

import java.lang.ref.WeakReference;

public abstract class BaseHolderData<T extends BaseHolder> {

    public WeakReference<T> mHolder;

    public IItemClickLisntener mClicklistener;

    public abstract @LayoutRes int getLayoutRes();

    public void onBindView(T holder) {
        mHolder = new WeakReference<>(holder);
    }

    public boolean isSameType(int type) {
        return getLayoutRes() == type;
    }

    public void refreshUi() {
        if (mHolder == null) {
            return;
        }
        T holder = mHolder.get();
        if (holder != null) {
            onBindView(holder);
        }
    }
}
