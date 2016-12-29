package com.davis;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

/**
 * @author Davis
 * @Description:
 * @date 2016/11/18 18:00
 * @copyright HAWK
 */

public class MyButton extends Button {

    public static final String TAG = MyButton.class.getSimpleName();
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(widthSize,height);
        Log.e(TAG,"mode:->"+mode+"   widthSize:->"+widthSize);
    }
}
