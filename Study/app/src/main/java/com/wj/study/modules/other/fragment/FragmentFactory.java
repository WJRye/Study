package com.wj.study.modules.other.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by wangjiang on 17/10/17.
 */

public final class FragmentFactory {

    public static final String SHADOW = "shadow";
    public static final String CONSTRAINT = "constraint";
    public static final String TEST = "test";


    public static Fragment getFragment(String tag) {
        Fragment fragment = null;
        if (SHADOW.equals(tag)) {
            fragment = new ShadowFragment();
        } else if (CONSTRAINT.equals(tag)) {
            fragment = new ConstraintFragment();
        } else if (TEST.equals(tag)) {
            fragment = new TestFragment();
        }
        return fragment;
    }
}
