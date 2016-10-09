package com.wptdxii.androidrepo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.wptdxii.androidrepo.R;
import com.wptdxii.uiframework.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initToolbarTitle(@StringRes int titleResId) {
        super.initToolbarTitle(R.string.main_activity_toolbar_title);
    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_main, -1, -1, MODE_NONE);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}