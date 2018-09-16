package com.sddy.baseui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sddy.utils.log.Log;

public abstract class BaseFragment extends Fragment implements Presenter {
    private ConstraintLayout mLayoutTitle;
    protected View mContentRoot;
    private ImageButton mBtnBack;
    private TextView mTvTitle;
    private TextView mTvFunc;

    protected abstract @LayoutRes int getLayoutRes();

    public void onShow() {

    }

    protected abstract void onCreatedView(View rootView, Bundle savedInstanceState);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.Ui.d("onAttach called.");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.Ui.d("onCreate called.");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.Ui.d("onStart called.");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.Ui.d("onResume called.");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.Ui.d("onPause called.");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.Ui.d("onStop called.");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.Ui.d("onDestroyView called.");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.Ui.d("onDestroy called.");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.Ui.d("onDetach called.");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.Ui.d("onCreateView called.");
        View pageRoot = inflater.inflate(R.layout.fragment_base, container, false);
        initTitle(pageRoot);
        FrameLayout frameContent = pageRoot.findViewById(R.id.fl_base_fragment_content);
        mContentRoot = inflater.inflate(getLayoutRes(), container, false);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        frameContent.addView(mContentRoot, lp);

        return pageRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreatedView(view, savedInstanceState);
    }

    private void initTitle(View pageRoot) {
        mLayoutTitle = pageRoot.findViewById(R.id.layout_base_fragment_title);
        mBtnBack = pageRoot.findViewById(R.id.btn_title_back);
        mTvTitle = pageRoot.findViewById(R.id.tv_title_title);
        mTvFunc = pageRoot.findViewById(R.id.btn_title_func);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackBtnClick();
            }
        });
        setFuncBtnVisible(false);
    }

    protected void onBackBtnClick() {

    }

    public void setTitleBackIcon(@DrawableRes int iconRes) {
        if (mBtnBack != null) {
            mBtnBack.setImageResource(iconRes);
        }
    }

    public void setTitleBackIcon(@DrawableRes int iconRes, @ColorRes int tintColorRes) {
        if (mBtnBack != null) {
            mBtnBack.setImageResource(iconRes);
            mBtnBack.setColorFilter(getResources().getColor(tintColorRes));
        }
    }

    protected void setBackBtnVisible(boolean visible) {
        if (mBtnBack != null) {
            mBtnBack.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    protected void setFuncBtn(String text, View.OnClickListener clickListener) {
        if (mTvFunc != null && !TextUtils.isEmpty(text)) {
            mTvFunc.setText(text);
            mTvFunc.setOnClickListener(clickListener);
        }
    }

    protected void setFuncBtn(@StringRes int textRes, View.OnClickListener clickListener) {
        setFuncBtn(getString(textRes), clickListener);
    }

    protected void setFuncBtnVisible(boolean visible) {
        if (mTvFunc != null) {
            mTvFunc.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
        }
    }

    protected void hideTitleBar() {
        if (mLayoutTitle != null) {
            mLayoutTitle.setVisibility(View.GONE);
        }
    }

    protected void setTitle(String title) {
        if (mTvTitle != null && !TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
    }

    protected void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    @Override
    public void onClick(View v) {

    }

    public void startActivity(Class<? extends Activity> activity) {
        Intent intent = new Intent(getActivity(), activity);
        startActivity(intent);
    }

    public void startActivity(Class<? extends Activity> activity, Bundle bundle) {
        Intent intent = new Intent(getActivity(), activity);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }
}
