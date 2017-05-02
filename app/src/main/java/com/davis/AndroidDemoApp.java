package com.davis;

import android.app.Application;

import com.davis.base.AppContext;


public class AndroidDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().setContext(getApplicationContext());
    }
}
