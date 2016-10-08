package com.wptdxii.androidrepo.internal.di.component;

import android.app.Activity;

import com.wptdxii.androidrepo.internal.di.module.ActivityModule;
import com.wptdxii.androidrepo.internal.di.scope.PerActivity;

import dagger.Component;

/**
 * Created by wptdxii on 2016/9/17 0017.
 */
@PerActivity
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {
    Activity activity();
}
