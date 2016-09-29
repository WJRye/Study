package com.wj.study.handler;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MyHandlers {

    public void onClickFriend(View view) {
        Toast.makeText(view.getContext(), "onClickFriend", Toast.LENGTH_LONG).show();
        Log.d("TAG", "onClickFriend");
    }
}