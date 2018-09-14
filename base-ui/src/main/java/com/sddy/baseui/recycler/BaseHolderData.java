package com.sddy.baseui.recycler;

import android.support.annotation.LayoutRes;

public abstract class BaseHolderData<T extends BaseHolder> {

    public IItemClickLisntener mClicklistener;

    public abstract @LayoutRes int getLayoutRes();

    public abstract void onBindView(T holder);

    public boolean isSameType(int type) {
        return getLayoutRes() == type;
    }
}
