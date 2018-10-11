package com.shenyong.aabills.view;

import android.app.Dialog;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bigkoo.pickerview.view.BasePickerView;
import com.shenyong.aabills.R;

public class BottomPickerWrapper {

    private BasePickerView mPickerView;

    public BottomPickerWrapper(BasePickerView pickerView) {
        mPickerView = pickerView;
        Dialog mDialog = mPickerView.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            mPickerView.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                //修改动画样式
                dialogWindow.setWindowAnimations(R.style.picker_view_slide_anim);
                //改成Bottom,底部显示
                dialogWindow.setGravity(Gravity.BOTTOM);
            }
        }
    }

    public void show() {
        if (mPickerView != null) {
            mPickerView.show();
        }
    }
}
