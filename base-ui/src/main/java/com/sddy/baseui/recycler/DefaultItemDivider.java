package com.sddy.baseui.recycler;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import static android.support.v7.widget.DividerItemDecoration.HORIZONTAL;
import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class DefaultItemDivider extends RecyclerView.ItemDecoration {

    private static final String TAG = "DividerItem";
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    public static final int GRID = 2;

    private Drawable mDivider;

    private int mOrientation;

    private final Rect mBounds = new Rect();

    public DefaultItemDivider(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        if (mDivider == null) {
            Log.w(TAG, "@android:attr/listDivider was not set in the theme used for this "
                    + "DividerItemDecoration. Please set that attribute all call setDrawable()");
        }
        a.recycle();
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL && orientation != GRID) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        mOrientation = orientation;
    }

    /**
     * Sets the {@link Drawable} for this divider.
     *
     * @param drawable Drawable that should be used as a divider.
     */
    public void setDrawable(@NonNull Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        mDivider = drawable;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }
        if (mOrientation == VERTICAL) {
            drawVertical(c, parent);
        } else if (mOrientation == HORIZONTAL) {
            drawHorizontal(c, parent);
        } else if (mOrientation == GRID) {
            // 网格布局
            drawGrid(c, parent);
        }
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int left;
        final int right;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        // 最后一个条目不要分割线
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final int top = bottom - mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int top;
        final int bottom;
        //noinspection AndroidLintNewApi - NewApi lint fails to handle overrides.
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getHeight();
        }

        final int childCount = parent.getChildCount();
        // 最后一个条目不要分割线
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
            final int right = mBounds.right + Math.round(child.getTranslationX());
            final int left = right - mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        if (mDivider == null) {
            outRect.set(0, 0, 0, 0);
            return;
        }
        if (mOrientation == VERTICAL) {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else if (mOrientation == HORIZONTAL) {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else if (mOrientation == GRID) {
            int position = parent.getChildLayoutPosition(view);
            int childCount = parent.getAdapter().getItemCount();
            int spanCount = ((GridLayoutManager)parent.getLayoutManager()).getSpanCount();

            int left = mDivider.getIntrinsicWidth() / 2;
            int right = mDivider.getIntrinsicWidth() / 2;
            int top = 0;
            int bottom = mDivider.getIntrinsicHeight();
            if (isFirstColumn(position, spanCount)) {
                left = 0;
//                right = mDivider.getIntrinsicWidth();
            }
            if (isLastColumn(position, spanCount)) {
                right = 0;
//                left = mDivider.getIntrinsicWidth() / 2;
            }
            if (isLastRow(position, spanCount, childCount)) {
                bottom = 0;
            }
            outRect.set(left, top, right, bottom);
        }
    }

    private void drawGrid(Canvas canvas, RecyclerView parent) {
//        canvas.save();
//        int top;
//        int bottom;
//        int left;
//        int right;
//        if (parent.getClipToPadding()) {
//            left = parent.getPaddingLeft();
//            right = parent.getWidth() - parent.getPaddingRight();
//            top = parent.getPaddingTop();
//            bottom = parent.getHeight() - parent.getPaddingBottom();
//            canvas.clipRect(left, top,
//                    right, bottom);
//        } else {
//            top = 0;
//            bottom = parent.getHeight();
//        }

        final int childCount = parent.getChildCount();
//        GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
//        int spanCount = layoutManager.getSpanCount();
        // 最后一个条目不要分割线
        for (int i = 0; i < childCount; i++) {
            drawVertical(canvas, parent);
            drawHorizontal(canvas, parent);
//            final View child = parent.getChildAt(i);
//            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
//            right = mBounds.right + Math.round(child.getTranslationX());
//            left = right - mDivider.getIntrinsicWidth();
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(canvas);
        }
//        canvas.restore();
    }

    private boolean isFirstColumn(int position, int spanCount) {
        return position % spanCount == 0;
    }
    private boolean isLastColumn(int position, int spanCount) {
        return (position + 1) % spanCount == 0;
    }
    private boolean isFirstRow(int position, int spanCount) {
        return position < spanCount;
    }
    private boolean isLastRow(int position, int spanCount, int childCount) {
        int lines = childCount % spanCount == 0 ? childCount / spanCount : childCount / spanCount + 1;
        return lines == position / spanCount + 1;
    }
}
