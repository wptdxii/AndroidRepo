package com.wptdxii.androidrepo.ui.activity;

import com.wptdxii.androidrepo.R;
import com.wptdxii.androidrepo.model.Module;
import com.wptdxii.androidrepo.ui.base.BaseContentActivity;

import java.util.ArrayList;

/**
 * 常用控件
 */
public class WidgetActivity extends BaseContentActivity {
    @Override
    protected void initToolbarTitle(int titleResId) {
        super.initToolbarTitle(R.string.widget_activity_toolbar_title);
    }

    @Override
    protected void addItem(ArrayList<Module> mDataList) {
        
    }
}
