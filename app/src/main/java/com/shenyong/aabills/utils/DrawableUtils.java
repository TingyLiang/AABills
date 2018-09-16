package com.shenyong.aabills.utils;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;

import com.sddy.utils.ViewUtils;
import com.shenyong.aabills.AABilsApp;

public class DrawableUtils {

    public static GradientDrawable getCircleDrawable(@ColorRes int solidRes, @DimenRes int sizeRes) {
        Resources res = AABilsApp.getInstance().getResources();
        GradientDrawable drawable = ViewUtils.getDrawableBg(solidRes);
        int size = res.getDimensionPixelSize(sizeRes);
        drawable.setSize(size, size);
        drawable.setShape(GradientDrawable.OVAL);

        return drawable;
    }

}
