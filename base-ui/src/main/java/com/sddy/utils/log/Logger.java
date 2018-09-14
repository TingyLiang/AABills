package com.sddy.utils.log;

import android.util.Log;

public class Logger {

    public static boolean debug = true;
    
    private String mTag;

    public Logger(String mTag) {
        this.mTag = mTag;
    }

    private String getFormatMsg(String format, Object... args) {
        return String.format(format, args);
    }

    /**
     * 获取调用处的类名，方法和行号信息
     */
    private String getCallerInfo() {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        if (elements.length < 5) {
            return "";
        }
        StackTraceElement element = elements[4];
        String clsName = element.getClassName();
        clsName = clsName.substring(clsName.lastIndexOf(".") + 1);
        String method = element.getMethodName();
        int lineNum = element.getLineNumber();
        return clsName + "." + method + "():" + lineNum;
    }


    public void v(String msg) {
        if (debug) {
            Log.v(mTag, msg);
        }
    }

    public void v(String format, Object... args) {
        if (debug) {
            Log.v(mTag, getFormatMsg(format, args));
        }
    }

    public void v(Throwable tr) {
        if (debug) {
            Log.v(mTag, "", tr);
        }
    }

    public void d(String msg) {
        if (debug) {

            Log.d(mTag + "-" + getCallerInfo(), msg);
        }
    }

    public void d(String format, Object... args) {
        if (debug) {
            Log.d(mTag + "-" + getCallerInfo(), getFormatMsg(format, args));
        }
    }

    public void d(Throwable tr) {
        if (debug) {
            Log.d(mTag, "", tr);
        }
    }

    public void i(String msg) {
        if (debug) {
            Log.i(mTag, msg);
        }
    }

    public void i(String format, Object... args) {
        if (debug) {
            Log.i(mTag, getFormatMsg(format, args));
        }
    }

    public void i(Throwable tr) {
        if (debug) {
            Log.i(mTag, "", tr);
        }
    }

    public void w(String msg) {
        if (debug) {
            Log.w(mTag, msg);
        }
    }

    public void w(String format, Object... args) {
        if (debug) {
            Log.w(mTag, getFormatMsg(format, args));
        }
    }

    public void w(Throwable tr) {
        if (debug) {
            Log.w(mTag, "", tr);
        }
    }

    public void e(String msg) {
        if (debug) {
            Log.e(mTag, msg);
        }
    }

    public void e(String format, Object... args) {
        if (debug) {
            Log.e(mTag, getFormatMsg(format, args));
        }
    }

    public void e(Throwable tr) {
        if (debug) {
            Log.e(mTag, "", tr);
        }
    }
}
