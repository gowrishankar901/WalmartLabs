package com.walmart.walmartlabstest.shared;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Component(modules = {
        ActivityModule.class,
        WalmartLabsModule.class,
        AndroidSupportInjectionModule.class
})

@Singleton
public interface WalmartLabsTestApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        @BindsInstance
        Builder context(Context context);
        WalmartLabsTestApplicationComponent build();
    }

    void inject(WalmartLabsTestApplication walmartLabsTestApplication);
}
