package com.sddy.baseui;

import android.app.Application;

public class BaseApplication extends Application {

    protected static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static BaseApplication getInstance() {
        return mInstance;
    }
}
