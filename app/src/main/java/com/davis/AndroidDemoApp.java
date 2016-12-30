package com.davis;

import android.app.Application;

import com.davis.base.AppContext;

/**
 * @author Davis
 * @Description:
 * @date 2016/12/30 13:34
 * @copyright Davis
 */

public class AndroidDemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().setContext(getApplicationContext());
    }
}
