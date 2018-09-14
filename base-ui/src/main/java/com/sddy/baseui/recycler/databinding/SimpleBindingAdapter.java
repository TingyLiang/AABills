package com.sddy.baseui.recycler.databinding;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.sddy.baseui.recycler.BaseHolder;
import com.sddy.baseui.recycler.BaseRecyclerAdapter;

public class SimpleBindingAdapter extends BaseRecyclerAdapter {

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BaseHolder holder = super.onCreateViewHolder(parent, viewType);
        return new BaseBindingHolder<>(DataBindingUtil.bind(holder.itemView));
    }
}
