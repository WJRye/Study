package com.wj.study.modules.other.fragment;

import android.graphics.Outline;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.ImageView;
import android.widget.TextView;

import com.wj.base.BaseFragment;
import com.wj.study.R;

/**
 * Created by wangjiang on 17/10/17.
 */

public class ShadowFragment extends BaseFragment {


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ImageView imageView = (ImageView) view.findViewById(R.id.shadow_image_view);
        imageView.setBackgroundResource(R.drawable.shadow);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setElevation(getResources().getDimensionPixelSize(R.dimen.size_5dp));
            imageView.setTranslationZ(getResources().getDimensionPixelSize(R.dimen.size_5dp));
            ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), getResources().getDimensionPixelSize(R.dimen.size_2dp));
                    }
                }
            };
            imageView.setOutlineProvider(viewOutlineProvider);
        }

        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.me);
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(), ((BitmapDrawable) drawable).getBitmap());
        roundedBitmapDrawable.setCornerRadius(getResources().getDimensionPixelSize(R.dimen.size_2dp));
        roundedBitmapDrawable.setAntiAlias(true);
        imageView.setImageDrawable(roundedBitmapDrawable);


        TextView textView = (TextView) view.findViewById(R.id.shadow_text_view);
        textView.append("屏幕密度：" + getResources().getDisplayMetrics().densityDpi + "dpi");
        textView.append("\n");
        textView.append("图片宽度：" + drawable.getIntrinsicWidth());
        textView.append("\n");
        textView.append("图片高度：" + drawable.getIntrinsicHeight());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shadow;
    }


}
