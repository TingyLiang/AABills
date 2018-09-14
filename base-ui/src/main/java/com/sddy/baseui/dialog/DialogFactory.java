package com.sddy.baseui.dialog;

import android.support.annotation.StringRes;
import android.view.View;

import com.sddy.baseui.R;

public class DialogFactory {

    public static MsgDialog confirmDialogWithoutTitle(String msg,
                                                      @StringRes int positiveText,
                                                      View.OnClickListener positive,
                                                      @StringRes int negativeText,
                                                      View.OnClickListener negative) {
        MsgDialog dialog = new MsgDialog();
        dialog.setMsg(msg)
        .setPositiveBtn(positiveText, positive)
        .setNegativeBtn(negativeText, negative);
        return dialog;
    }

    public static MsgDialog confirmDialogWithoutTitle(String msg,
                                                      View.OnClickListener positive) {
        return confirmDialogWithoutTitle(msg, R.string.common_ok, positive,
                R.string.common_cancel, null);
    }
}
