package com.sddy.baseui.recycler;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public BaseHolder(View itemView) {
        super(itemView);
        mViews = new SparseArray<>();
    }

    public RecyclerView.ViewHolder setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 通过id获取view
     */
    public <T extends View> T getView(int viewId) {
        // 先从缓存中找
        View view = mViews.get(viewId);
        if (view == null) {
            // 直接从ItemView中找
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置View的Visibility
     */
    public RecyclerView.ViewHolder setViewVisibility(int viewId, int visibility) {
        getView(viewId).setVisibility(visibility);
        return this;
    }

    /**
     * 设置ImageView的资源
     */
    public RecyclerView.ViewHolder setImageResource(int viewId, int resourceId) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(resourceId);
        return this;
    }
}
