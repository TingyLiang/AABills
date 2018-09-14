package com.sddy.utils;

import android.content.res.Resources;
import android.util.TypedValue;

public class DimenUtils {

//    public static float dp2px(int dp) {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
//                Resources.getSystem().getDisplayMetrics());
//    }

    public static int dp2px(float dp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics()) + 0.5);
    }

//    public static float sp2px(int sp) {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
//                Resources.getSystem().getDisplayMetrics());
//    }

    public static int sp2px(float sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                        Resources.getSystem().getDisplayMetrics()) + 0.5);
    }
}
