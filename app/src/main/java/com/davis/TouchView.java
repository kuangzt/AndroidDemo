package com.davis;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * @author Davis
 * @Description:
 * @date 2016/11/21 19:28
 * @copyright Davis
 */

public class TouchView extends TextView {

    public static final String TAG = TouchView.class.getSimpleName();

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG,"TouchView:-->"+"dispatchTouchEvent"+MotionEvent.actionToString(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"TouchView:-->"+"onTouchEvent"+MotionEvent.actionToString(event.getAction()));
        super.onTouchEvent(event);
        return true;
    }
}
