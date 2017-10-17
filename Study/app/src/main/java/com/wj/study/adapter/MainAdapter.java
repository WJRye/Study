package com.wj.study.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.TextView;

import com.wj.study.MainActivity;
import com.wj.study.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author：王江 on 2016/7/7 16:47
 * Description:
 */
public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = MainAdapter.class.getSimpleName();
    private List<String> mTitles = new ArrayList<>();
    private MainActivity mActivity;

    public MainAdapter(MainActivity context) {
        String[] titles = context.getResources().getStringArray(R.array.titles);
        mTitles.addAll(Arrays.asList(titles));
//        for (int i=0;i<20;i++)
//            mTitles.addAll(Arrays.asList(titles));
        mActivity = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.default_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(-1, parent.getResources().getDimensionPixelSize(R.dimen.size_45dp)));
        return new ViewCache(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final String[] array = mTitles.get(holder.getLayoutPosition()).split("，");
        ViewCache viewCache = (ViewCache) holder;
        viewCache.title.setText(array[0]);
        viewCache.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Intent intent = new Intent(array[1]);
                mActivity.openActivity(intent);
            }
        });
        viewCache.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {

                Log.d(TAG, "width=" + view.getWidth());
                PropertyValuesHolder alphaValuesHolder = PropertyValuesHolder.ofFloat(View.ALPHA, 1.0f, 0.0f);
                PropertyValuesHolder transitionYValuesHolder = PropertyValuesHolder.ofFloat(View.TRANSLATION_X, 0f, -view.getWidth());
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, alphaValuesHolder, transitionYValuesHolder).setDuration(200);
                objectAnimator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        startAnimation(view);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                objectAnimator.start();

                return true;
            }
        });
    }


    private void startAnimation(final View view) {
        final int initialHeight = view.getHeight();
        Animation animation = new Animation() {
            @Override
            public boolean willChangeBounds() {
                return true;
            }

            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    view.getLayoutParams().height = 0;
                    view.requestLayout();
                    view.setVisibility(View.GONE);
                } else {
                    view.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    view.requestLayout();
                }
            }
        };
        animation.setDuration(200);
        animation.setInterpolator(new LinearInterpolator());
        view.startAnimation(animation);


    }


    @Override
    public int getItemCount() {
        return mTitles.size();
    }

    private static class ViewCache extends RecyclerView.ViewHolder {

        TextView title;

        ViewCache(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.default_item_title);
        }
    }
}
