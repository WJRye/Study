package com.wj.study.modules.customview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.wj.base.BaseActivity;
import com.wj.study.R;
import com.wj.study.view.AudioView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AudioViewActivity extends BaseActivity {
    private AudioView mAudioView;
    private Timer mTimer = new Timer();
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mAudioView.invalidateView(msg.arg1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_audio_view;
    }

    @Override
    public void initViews(View view) {
        mAudioView = (AudioView) findViewById(R.id.audio_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.audio_view_start:
                startRecording();
                break;
            case R.id.audio_view_pause:
                pauseRecording();
                break;
        }
    }

    private void pauseRecording() {
        mTimer.cancel();
        mTimer = null;
        mTimer = new Timer();
    }

    private void startRecording() {
        final Random randon = new Random();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                mHandler.sendMessage(mHandler.obtainMessage(0x123, randon.nextInt(90), 0));
            }
        }, 1000, 50);
    }



    @Override
    public void onStop() {
        super.onStop();
    }
}
