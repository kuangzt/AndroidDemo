package com.example.first;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * @author Davis
 * @Description:
 * @date 2017/8/17 下午5:25
 * @copyright
 */
public class App extends Application {

    public static final String LIFE_CYCLE = "lifeCycle";
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Log.e(LIFE_CYCLE,"onCreate"+activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e(LIFE_CYCLE,"onStart"+activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e(LIFE_CYCLE,"onResume"+activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e(LIFE_CYCLE,"onPause"+activity);
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e(LIFE_CYCLE,"onStop"+activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                Log.e(LIFE_CYCLE,"onSaveInstanceState"+activity);
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e(LIFE_CYCLE,"onDestroy"+activity);
            }
        });
    }
}
