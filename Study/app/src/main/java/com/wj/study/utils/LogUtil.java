package com.wj.study.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * Author：王江 on 2017/5/9 11:57
 * Description:
 */

public class LogUtil {

    private static Boolean isDebug = null;

    public static boolean isDebug() {
        return isDebug == null ? false : isDebug.booleanValue();
    }

    /**
     * Sync lib debug with app's debug value. Should be called in module Application
     *
     * @param context
     */
    public static void syncIsDebug(Context context) {
        if (isDebug == null) {
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
    }

    public static void d(String tag, String message) {
        if (isDebug()) {
            Log.d(tag, message);
        }
    }
}
