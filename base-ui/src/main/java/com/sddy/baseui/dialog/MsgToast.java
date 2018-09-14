package com.sddy.baseui.dialog;

import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sddy.baseui.BaseApplication;
import com.sddy.baseui.R;
import com.sddy.utils.ViewUtils;

public class MsgToast {

    private static Toast mToast;

    private static Toast mCenterToast;

    public static void shortToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mCenterToast != null) {
            mCenterToast.cancel();
        }
        if (mToast == null) {
            mToast = Toast.makeText(BaseApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void shortToast(@StringRes int msgRes) {
        shortToast(BaseApplication.getInstance().getResources().getString(msgRes));
    }

    public static void centerToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        if (mToast != null) {
            mToast.cancel();
        }
        if (mCenterToast == null) {
            mCenterToast = Toast.makeText(BaseApplication.getInstance(), "", Toast.LENGTH_SHORT);
        }
        View view = LayoutInflater.from(BaseApplication.getInstance()).inflate(R.layout.layout_center_toast, null, false);
        ((TextView) view.findViewById(R.id.tv_center_toast_msg)).setText(msg);
        view.setBackground(ViewUtils.getDrawableBg(R.color.center_toast_bg, R.dimen.margin_small));
        mCenterToast.setView(view);
        mCenterToast.setGravity(Gravity.CENTER, 0, 0);
        mCenterToast.show();
    }

    public static void centerToast(@StringRes int msgRes) {
        centerToast(BaseApplication.getInstance().getResources().getString(msgRes));
    }
}
