package com.wj.study.modules.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class CustomViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initViews(View view) {
        int[] ids = {R.id.item_character_view, R.id.item_progress_view, R.id.item_audio_view, R.id.item_arc_view};
        int[] stringIds = {R.string.character_view, R.string.progress_view, R.string.audio_view, R.string.arc_view};
        for (int i = 0, len = ids.length; i < len; i++) {
            ((TextView) findViewById(ids[i]).findViewById(R.id.default_item_title)).setText(stringIds[i]);
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
