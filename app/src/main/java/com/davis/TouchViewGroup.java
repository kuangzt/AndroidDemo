package com.davis;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author Davis
 * @Description:
 * @date 2016/11/21 19:27
 * @copyright Davis
 */

public class TouchViewGroup extends LinearLayout {

    public static final String TAG = TouchViewGroup.class.getSimpleName();

    public TouchViewGroup(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Log.e(TAG,"TouchViewGroup:-->"+"dispatchTouchEvent"+MotionEvent.actionToString(event.getAction()));

        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        Log.e(TAG,"TouchViewGroup:-->"+"onInterceptTouchEvent"+MotionEvent.actionToString(event.getAction()));
        super.onInterceptTouchEvent(event);
        if(event.getAction()==MotionEvent.ACTION_MOVE){
            return true;
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"TouchViewGroup:-->"+"onTouchEvent"+MotionEvent.actionToString(event.getAction()));
        super.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG,"onDraw");
    }
}
