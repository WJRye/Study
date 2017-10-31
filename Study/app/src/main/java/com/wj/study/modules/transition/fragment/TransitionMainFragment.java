package com.wj.study.modules.transition.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.base.BaseFragment;
import com.wj.study.R;
import com.wj.study.modules.transition.TransitionActivity;

/**
 * Author：王江 on 2017/2/22 16:51
 * Description:
 */

public class TransitionMainFragment extends BaseFragment implements View.OnClickListener {

    public TransitionMainFragment() {
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        int[] resStringIds = {R.string.visible, R.string.slid, R.string.explode, R.string.image, R.string.path, R.string.transition_name};
        int[] resIds = {R.id.item_visible, R.id.item_slid, R.id.item_explode, R.id.item_image, R.id.item_path, R.id.item_transition_name};
        for (int i = 0, len = resIds.length; i < len; i++) {
            ViewGroup layout = (ViewGroup) view.findViewById(resIds[i]);
            TextView textView = (TextView) layout.findViewById(R.id.default_item_title);
            textView.setText(resStringIds[i]);
            layout.setOnClickListener(this);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.transition_main_fragment;
    }

    @Override
    public void onClick(View v) {
        String tag = null;
        switch (v.getId()) {
            case R.id.item_visible:
                tag = TransitionActivity.TAG_VISIBLE;
                break;
            case R.id.item_slid:
                tag = TransitionActivity.TAG_SLID;
                break;
            case R.id.item_explode:
                tag = TransitionActivity.TAG_EXPLODE;
                break;
            case R.id.item_image:
                tag = TransitionActivity.TAG_IMAGE;
                break;
            case R.id.item_path:
                tag = TransitionActivity.TAG_PATH;
                break;
            case R.id.item_transition_name:
                tag = TransitionActivity.TAG_TRANSITION_NAME;
                break;
            default:
                break;
        }
        ((TransitionActivity) getActivity()).replaceFragment(tag);
    }
}
