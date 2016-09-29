package com.wj.study.modules.design.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wj.study.R;

/**
 * Author：王江 on 2016/8/23 11:49
 * Description:
 */
public class ArtFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        textView.setGravity(Gravity.CENTER);
        textView.setText("美术");
        textView.setTextColor(getContext().getResources().getColor(android.R.color.primary_text_light));
        textView.setTextSize(getContext().getResources().getDimensionPixelSize(R.dimen.textSize_14sp));
        return textView;
    }
}
