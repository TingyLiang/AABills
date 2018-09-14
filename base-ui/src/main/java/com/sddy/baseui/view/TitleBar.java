package com.sddy.baseui.view;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sddy.baseui.R;

public class TitleBar extends ConstraintLayout {

    private ConstraintLayout mRoot;
    private ImageButton mBtnBack;
    private TextView mTvTitle;
    private TextView mTvFunc;
    private View.OnClickListener mBackListener;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View root = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_title_bar, this);
        mRoot = root.findViewById(R.id.cl_title_container);
        mBtnBack = mRoot.findViewById(R.id.btn_title_back);
        mBtnBack.setOnClickListener(mBackListener);
        mTvTitle = mRoot.findViewById(R.id.tv_title_title);
        mTvFunc = mRoot.findViewById(R.id.btn_title_func);
        mTvFunc.setVisibility(View.INVISIBLE);
    }

    public void setTitle(String title) {
        if (mTvTitle != null && !TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    public void setTitle(@StringRes int titleId) {
        setTitle(getContext().getString(titleId));
    }


    /** 标题栏返回按钮点击回调，通知页面处理关闭前的工作 */
    public void onTitleBackClick() {

    }

    public void setBackBtnCallback(OnClickListener onClickListener) {
        mBtnBack.setOnClickListener(onClickListener);
    }

    public void setBackBtnVisible(boolean visible) {
        if (mBtnBack != null) {
            mBtnBack.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void setFuncBtn(String text, View.OnClickListener clickListener) {
        if (mTvFunc != null && !TextUtils.isEmpty(text)) {
            mTvFunc.setText(text);
            mTvFunc.setVisibility(View.VISIBLE);
            mTvFunc.setOnClickListener(clickListener);
        }
    }

    public void setFuncBtn(@StringRes int textRes, View.OnClickListener clickListener) {
        setFuncBtn(getContext().getString(textRes), clickListener);
    }

    public void setFuncBtnVisible(boolean visible) {
        if (mTvFunc != null) {
            mTvFunc.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    public void setBgColor(@ColorRes int colorRes) {
        if (mRoot != null) {
            mRoot.setBackgroundResource(colorRes);
        }
    }
}
