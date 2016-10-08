package com.wptdxii.androidrepo.internal.di.component;

import com.wptdxii.androidrepo.internal.di.module.ActivityModule;
import com.wptdxii.androidrepo.internal.di.module.GanksModule;
import com.wptdxii.androidrepo.internal.di.scope.PerActivity;
import com.wptdxii.androidrepo.ui.activity.ContentActivity;

import dagger.Component;

/**
 * Created by wptdxii on 2016/9/17 0017.
 */
@PerActivity
@Component(modules = {ActivityModule.class, GanksModule.class}, dependencies = AppComponent.class)
public interface GanksComponent {

    void inject(ContentActivity activity);
}
