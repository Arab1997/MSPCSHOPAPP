package com.ukuya.mspc;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.ukuya.mspc.di.component.AppComponent;
import com.ukuya.mspc.di.component.DaggerAppComponent;
import com.ukuya.mspc.di.module.AppModule;

public class MyApp extends Application {
    public static MyApp app;
    public AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        buildAppComponent();
    }

    private void buildAppComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(app))
                .build();

        Utils.init(this);
    }
}
