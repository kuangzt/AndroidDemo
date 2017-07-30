package com.davis;

import android.app.Application;
import android.os.SystemClock;

import com.davis.base.AppContext;


public class AndroidDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().setContext(getApplicationContext());
        SystemClock.sleep(30000);
    }
}
