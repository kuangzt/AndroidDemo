package com.davis;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;

import com.davis.base.AppContext;


public class AndroidDemoApp extends Application {

    public static final String TAG = AndroidDemoApp.class.getSimpleName();
    @Override
    public void onCreate() {
        super.onCreate();
        AppContext.getInstance().setContext(getApplicationContext());
        //SystemClock.sleep(30000);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Log.e(TAG,"onCreate:"+String.valueOf(activity));
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e(TAG,"onStart:"+String.valueOf(activity));
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e(TAG,"onResume:"+String.valueOf(activity));
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e(TAG,"onPause:"+String.valueOf(activity));
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e(TAG,"onStop:"+String.valueOf(activity));
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                Log.e(TAG,"onSaveInstanceState:"+String.valueOf(activity));
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e(TAG,"onDestroyed:"+String.valueOf(activity));
            }
        });
    }
}
