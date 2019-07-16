package com.ukuya.mspc.di.component;

import com.ukuya.mspc.MyApp;
import com.ukuya.mspc.api.ApiModule;
import com.ukuya.mspc.di.module.AppModule;
import com.ukuya.mspc.mvp.presenter.EventPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {AppModule.class, ApiModule.class})
@Singleton
public interface AppComponent {
    void inject(MyApp app);

    void inject(EventPresenter eventPresenter);
}
