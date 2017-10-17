package com.wj.study.modules.design;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class SnackbarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_snackbar;
    }

    @Override
    public void initViews(View view) {
        TextView desView = (TextView) findViewById(R.id.snackbar_description);
        desView.setText("Snackbars provide lightweight feedback about an operation. They show a brief message at the bottom of the screen on mobile and lower left on larger devices. Snackbars appear above all other elements on screen and only one can be displayed at a time.\n" +
                "\n" +
                "They automatically disappear after a timeout or after user interaction elsewhere on the screen, particularly after interactions that summon a new surface or activity. Snackbars can be swiped off screen.\n" +
                "\n" +
                "Snackbars can contain an action which is set via setAction(CharSequence, android.view.View.OnClickListener).\n" +
                "\n" +
                "To be notified when a snackbar has been shown or dismissed, you can provide a Snackbar.Callback via setCallback(Callback).");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.show_snackbar) {
            Snackbar snackbar = Snackbar.make(v, "这是Snackbar!", Snackbar.LENGTH_LONG)
                    .setAction("取消", new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                        }
                    });
            snackbar.setActionTextColor(Color.RED);//设置动作文本颜色
            View sbView = snackbar.getView();//得到显示文本的View
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
