package com.sddy.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class ScreenUtils {

    public static int getScreenW(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point p = new Point();
        display.getRealSize(p);
        return p.x;
    }

    public static int getScreenH(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point p = new Point();
        display.getRealSize(p);
        return p.y;
    }

    public static Point getScreenSize(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point p = new Point();
        display.getRealSize(p);
        return p;
    }

    /**
     * 获取系统状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarH(Context context) {
        /**
         * 另一种获取方法
         */
//        Rect frame = new Rect();
//        context.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//        int statusBarHeight = frame.top;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return res.getDimensionPixelSize(resourceId);
        }
        return 0;
    }


    public static int getNavigationBarH(Context context) {
        Resources res = context.getResources();
        int rid = res.getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
            return res.getDimensionPixelSize(resourceId);
        }
        return 0;
    }

    public static int getActionBarH(Context context) {
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return 0;
    }
}
