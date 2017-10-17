package com.wj.study.modules.design;

import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class FloatingActionButtonActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_floating_action_button;
    }

    @Override
    public void initViews(View view) {

        TextView desView = (TextView) findViewById(R.id.fab_description);
        desView.setText("Floating action buttons are used for a special type of promoted action. They are distinguished by a circled icon floating above the UI and have special motion behaviors related to morphing, launching, and the transferring anchor point.\n" +
                "\n" +
                "Floating action buttons come in two sizes: the default and the mini. The size can be controlled with the fabSize attribute.\n" +
                "\n" +
                "As this class descends from ImageView, you can control the icon which is displayed via setImageDrawable(Drawable).\n" +
                "\n" +
                "The background color of this view defaults to the your theme's colorAccent. If you wish to change this at runtime then you can do so via setBackgroundTintList(ColorStateList).");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.floating_action_button) {
            PopupMenu popupMenu = new PopupMenu(v.getContext(), v, Gravity.TOP);
            MenuInflater menuInflater = popupMenu.getMenuInflater();
            menuInflater.inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.show();
        }
    }
}
