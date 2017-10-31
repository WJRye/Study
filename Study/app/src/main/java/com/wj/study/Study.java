package com.wj.study;

import android.app.Application;

import com.wj.study.utils.LogUtil;


/**
 * Author：王江 on 2017/5/9 11:59
 * Description:
 */

public class Study extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.syncIsDebug(getApplicationContext());
    }
}
