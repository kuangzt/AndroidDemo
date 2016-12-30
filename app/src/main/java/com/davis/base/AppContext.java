package com.davis.base;

import android.content.Context;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Davis
 * @Description:
 * @date 2016/12/30 11:27
 * @copyright Davis
 */

public class AppContext {

    private Context mContext;
    private static AtomicReference<AppContext> mAppContextRef = new AtomicReference<AppContext>();
    private AppContext(){

    }

    /**
     * 一般Application的onCreate中调用
     * @param context
     */
    public void setContext(Context context){
        this.mContext = context;
    }

    public Context getContext(){
        return this.mContext;
    }

    public static AppContext getInstance(){
        AppContext appContext;
        do{
            appContext = mAppContextRef.get();
            if(appContext!=null){
                return appContext;
            }
            appContext = new AppContext();
            boolean success = mAppContextRef.compareAndSet(null,appContext);
            if(success){
                return appContext;
            }
        }while (true);
    }
}
