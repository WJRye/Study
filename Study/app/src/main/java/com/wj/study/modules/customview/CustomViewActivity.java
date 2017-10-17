package com.wj.study.modules.customview;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class CustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDefaultActionBarView();
        ImageView imageView = getLeftIcon();
        if (imageView != null) {
            imageView.setClickable(true);
//            imageView.setImageDrawable(DrawableUtil.getTintDrawable(this, R.drawable.ic_arrow_back_white, R.color.arrow_back));
            Drawable drawable = getResources().getDrawable(R.drawable.ic_arrow_back_white);
            drawable.mutate();
            imageView.setImageDrawable(drawable);
            imageView.setBackgroundResource(R.drawable.arrwo_back_selector);
//            leftIcon.setImageDrawable(DrawableUtil.getStateDrawable(this, R.drawable.ic_arrow_back_white, R.color.colorAccent, android.R.color.white));
        }

        TextView centerTitle = getCenterTitle();
        if (centerTitle != null) {
            centerTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimensionPixelSize(R.dimen.textSize_22sp));
            centerTitle.setText(R.string.custom_view);
        }
    }

    @Override
    public void initViews(View view) {
        int[] ids = {R.id.item_character_view, R.id.item_progress_view, R.id.item_audio_view, R.id.item_arc_view};
        int[] stringIds = {R.string.character_view, R.string.progress_view, R.string.audio_view, R.string.arc_view};
        for (int i = 0, len = ids.length; i < len; i++) {
            FrameLayout layout = (FrameLayout) findViewById(ids[i]);
            ((TextView) layout.findViewById(R.id.default_item_title)).setText(stringIds[i]);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_character_view:
                openActivity(CharacterViewActivity.class);
                break;
            case R.id.item_progress_view:
                openActivity(ProgressViewActivity.class);
                break;
            case R.id.item_audio_view:
                openActivity(AudioViewActivity.class);
                break;
            case R.id.item_arc_view:
                openActivity(ArcViewActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_custom_view;
    }
}
