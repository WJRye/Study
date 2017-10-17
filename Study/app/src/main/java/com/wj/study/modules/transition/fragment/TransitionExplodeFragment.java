package com.wj.study.modules.transition.fragment;

import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;

import com.wj.base.BaseFragment;
import com.wj.study.R;
import com.wj.study.modules.transition.adapter.TransitionExplodeAdapter;

/**
 * Author：王江 on 2017/2/27 10:28
 * Description:
 */

public class TransitionExplodeFragment extends BaseFragment {
    @Override
    public int getLayoutId() {
        return R.layout.recycler_view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        TransitionExplodeAdapter adapter = new TransitionExplodeAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new TransitionExplodeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                if (Build.VERSION.SDK_INT >= 21) {
                    // save rect of view in screen coordinates
                    final Rect viewRect = new Rect();
                    view.getGlobalVisibleRect(viewRect);
                    // create Explode transition with epicenter
                    Transition explode = new Explode();
                    explode.setEpicenterCallback(new Transition.EpicenterCallback() {
                        @Override
                        public Rect onGetEpicenter(Transition transition) {
                            return viewRect;
                        }
                    });
                    explode.setDuration(3000);
                    TransitionManager.beginDelayedTransition(recyclerView, explode);

                    // remove all views from Recycler View
                    recyclerView.setAdapter(null);
                }
            }
        });
    }
}
