package com.ukuya.mspc.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.ukuya.mspc.MyApp;
import com.ukuya.mspc.utils.PreferenceUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private MyApp app;

    public AppModule(MyApp app){
        this.app = app;
    }

    public MyApp getApp() {
        return app;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return app;
    }

    @Provides
    @Singleton
    public MyApp provideMyApp(){
        return app;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return app;
    }

    @Provides
    @Singleton
    public PreferenceUtil providePref(Gson gson){
        return new PreferenceUtil(app, gson);
    }
}
