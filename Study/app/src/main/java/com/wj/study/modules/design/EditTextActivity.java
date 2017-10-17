package com.wj.study.modules.design;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.wj.base.BaseActivity;
import com.wj.study.R;

public class EditTextActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_edit_text;
    }

    @Override
    public void initViews(View view) {
        final AppCompatEditText appCompatEditText = (AppCompatEditText) findViewById(R.id.app_compat_edit_text);
        final TextInputEditText inputEditText = (TextInputEditText) findViewById(R.id.text_input_edit_text);
        inputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    appCompatEditText.setError("名字输入错误");
                }
            }
        });

        TextInputEditText emailView = (TextInputEditText) findViewById(R.id.text_input_edit_text_email);
        final TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        emailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("TAG",s.toString());
                if("1".equals(s.toString())){
//                    inputLayout.setErrorEnabled(false);
                }else{
                    inputLayout.setErrorEnabled(true);
                    inputLayout.setError("邮箱错误");
                }
            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
