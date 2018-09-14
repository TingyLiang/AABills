package com.sddy.baseui.dialog;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sddy.baseui.BaseApplication;
import com.sddy.baseui.R;
import com.sddy.baseui.databinding.LayoutCommonDialogBinding;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MsgDialog extends DialogFragment {

    private LayoutCommonDialogBinding mBinding;
    private View mContentView;
    private String mTitle;
    private String mMesg;
    private String mBtnNegativeText;
    private String mBtnPositiveText;
    private View.OnClickListener mPositiveListener;
    private View.OnClickListener mNegativeListener;
    private int mBgColor = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparent);
        View root = inflater.inflate(R.layout.layout_common_dialog, container, false);
        mBinding = DataBindingUtil.bind(root);
        if (mContentView != null) {
            mBinding.flCommonDialogContent.removeAllViews();
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            int margin = getResources().getDimensionPixelSize(R.dimen.margin_large);
            lp.leftMargin = margin;
            lp.rightMargin = margin;
            lp.topMargin = margin;
            lp.bottomMargin = margin;
            mBinding.flCommonDialogContent.addView(mContentView, lp);
        }

        int visibility = TextUtils.isEmpty(mTitle) ? GONE : VISIBLE;
        mBinding.tvCommonDialogTitle.setVisibility(visibility);
        mBinding.viewCommonDialogTopLine.setVisibility(visibility);
        if (!TextUtils.isEmpty(mTitle)) {
            mBinding.tvCommonDialogTitle.setText(mTitle);
        }

        visibility = TextUtils.isEmpty(mMesg) && mContentView == null ? GONE : VISIBLE;
        mBinding.flCommonDialogContent.setVisibility(visibility);
        if (!TextUtils.isEmpty(mMesg) && mContentView == null) {
            mBinding.tvCommonDialogMsg.setText(mMesg);
        }
        visibility = !TextUtils.isEmpty(mBtnPositiveText) && mPositiveListener != null ? VISIBLE : GONE;
        mBtnNegativeText = TextUtils.isEmpty(mBtnNegativeText) ?
                BaseApplication.getInstance().getString(R.string.common_cancel) : mBtnNegativeText;
        mNegativeListener = mNegativeListener == null ? mDefNegativeListener : mNegativeListener;
        mBinding.viewCommonDialogBottomLine.setVisibility(visibility);
        mBinding.btnCommonDialogNegative.setVisibility(visibility);
        mBinding.viewCommonDialogBtnLine.setVisibility(visibility);
        mBinding.btnCommonDialogPositive.setVisibility(visibility);
        mBinding.btnCommonDialogNegative.setText(mBtnNegativeText);
        mBinding.btnCommonDialogPositive.setText(mBtnPositiveText);
        mBinding.btnCommonDialogNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mNegativeListener != null) {
                    mNegativeListener.onClick(v);
                }
            }
        });
        mBinding.btnCommonDialogPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mPositiveListener != null) {
                    mPositiveListener.onClick(v);
                }
            }
        });

        if (mBgColor >= 0) {
            mBinding.getRoot().setBackgroundColor(mBgColor);
        }

        return root;
    }

    public MsgDialog setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle = title;
        }
        return this;
    }

    public MsgDialog setTitle(@StringRes int titleRes) {
        setTitle(BaseApplication.getInstance().getResources().getString(titleRes));
        return this;
    }

    public MsgDialog setMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            mMesg = msg;
        }
        return this;
    }

    public MsgDialog setMsg(@StringRes int megRes) {
        setTitle(getString(megRes));
        return this;
    }

    public MsgDialog setContentView(View root) {
        mContentView = root;
        return this;
    }

    public MsgDialog setPositiveBtn(String btnText, View.OnClickListener clickListener) {
        if (!TextUtils.isEmpty(btnText)) {
            mBtnPositiveText = btnText;
        }
        mPositiveListener = clickListener;
        return this;
    }

    public MsgDialog setPositiveBtn(@StringRes int textRes, View.OnClickListener clickListener) {
        setPositiveBtn(BaseApplication.getInstance().getResources().getString(textRes), clickListener);
        return this;
    }

    public MsgDialog setNegativeBtn(String btnText, View.OnClickListener clickListener) {
        if (!TextUtils.isEmpty(btnText)) {
            mBtnNegativeText = btnText;
        }
        mNegativeListener = clickListener;
        return this;
    }

    public MsgDialog setNegativeBtn(@StringRes int textRes, View.OnClickListener clickListener) {
        setNegativeBtn(BaseApplication.getInstance().getResources().getString(textRes), clickListener);
        return this;
    }

    public MsgDialog setNegativeBtn(String btnText) {
       return setNegativeBtn(btnText, null);
    }

    public MsgDialog setNegativeBtn(@StringRes int textRes) {
        return setNegativeBtn(textRes, null);
    }

    public MsgDialog setBackgroundColor(@ColorInt int color) {
        mBgColor = color;
        return this;
    }

    public void show(FragmentManager manager) {

        super.show(manager, "MsgDialog");
    }

    private View.OnClickListener mDefNegativeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
}
