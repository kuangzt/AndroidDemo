package com.davis.util;

import android.content.Context;

import com.davis.base.AppContext;

/**
 * @author Davis
 * @Description:
 * @date 2016/12/30 13:41
 * @copyright HAWK
 */

public final class DimenUtils {
    private DimenUtils(){

    }

    public static int getDimensionPixelOffset(int resId) {
        AppContext appContext = AppContext.getInstance();
        Context context = appContext.getContext();
        int dimension = 0;
        try {
            dimension = context.getResources().getDimensionPixelOffset(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dimension;
    }



    public static int dip2px(float dp){
        AppContext appContext = AppContext.getInstance();
        Context context = appContext.getContext();
        int px = (int) (dp + 0.5f);
        try {
            px = (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return px;
    }

    public static int sp2px(int sp){
        AppContext appContext = AppContext.getInstance();
        Context context = appContext.getContext();
        int px = (int)(sp + 0.5f);
        try {
            px = (int)(context.getResources().getDisplayMetrics().scaledDensity * sp + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return px;
    }
}
