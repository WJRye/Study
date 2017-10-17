package com.wj.base;

import android.view.View;

/**
 * Author：王江 on 2016/7/7 16:23
 * Description:
 */
public interface IBaseView {

    View getContentView();

    void initViews(View rootView);

    void onClick(View v);
}
