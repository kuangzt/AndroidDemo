package com.davis.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.davis.R;
import com.davis.util.DimenUtils;

import java.util.Calendar;

/**
 * @author Davis
 * @Description:
 * @date 2016/12/29 11:19
 * @copyright Davis
 */

public class ClockView extends View {

    public static final String TAG = ClockView.class.getSimpleName();
    private float mRadius;
    private float mLongScale;
    private float mShortScale;
    private Paint mPaint;
    private float mCx;
    private float mCy;
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        parseAttrs(context,attrs,defStyleAttr);
    }

    private void parseAttrs(Context context,AttributeSet attrs,int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, com.davis.R.styleable.ClockView, defStyleAttr,0);
        final int N = a.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            switch (attr) {
                case R.styleable.ClockView_long_scale:
                    mLongScale = a.getDimensionPixelOffset(i, DimenUtils.getDimensionPixelOffset(R.dimen.clockview_default_long_scale));
                    break;
                case R.styleable.ClockView_short_scale:
                    mShortScale = a.getDimensionPixelOffset(i, DimenUtils.getDimensionPixelOffset(R.dimen.clockview_default_short_scale));
                    break;
            }
        }
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int specHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int specHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        int size = 10000;
        if(specHeightMode==MeasureSpec.AT_MOST&&specHeightMode==MeasureSpec.AT_MOST){
            throw new IllegalArgumentException("至少有一个确定值");
        }else{
            if(specWidthMode==MeasureSpec.EXACTLY){
                size = Math.min(specWidthSize,size);
            }
            if(specHeightMode==MeasureSpec.EXACTLY){
                size = Math.min(specHeightSize,size);
            }
        }
        setMeasuredDimension(size,size);
        Log.e(TAG,"onMeasure"+size);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRadius = getWidth()/2.0f;
        mCx = getWidth()/2.0f;
        mCy = getHeight()/2.0f;
        Log.e(TAG,"onSizeChanged"+mRadius);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(mCx,mCy,mRadius,mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.BLACK);
        for(int i=0;i<72;i++){
            if(i%3==0){
                canvas.drawLine(mRadius,0,mRadius,mLongScale,mPaint);
            }else{
                canvas.drawLine(mRadius,0,mRadius,mShortScale,mPaint);
            }
            canvas.rotate(5.0f,mCx,mCy);
        }
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.BLACK);
        for(int i=12;i>0;i--){
            Rect textBounds = new Rect();
            mPaint.getTextBounds(String.valueOf(i), 0, String.valueOf(i).length(), textBounds);
            canvas.drawText(String.valueOf(i),(int)(mCx-mPaint.measureText(String.valueOf(i))/2.0),mLongScale+30,mPaint);
            canvas.rotate(-30,mCx,mCy);
        }
        canvas.restore();

        Calendar calendar = Calendar.getInstance();
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.rotate(second*6.0f,mCx,mCy);
        canvas.drawRect(mCx-2,mLongScale+10,mCx+2,mCy+50,mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.BLUE);
        canvas.rotate(minute*6.0f,mCx,mCy);
        canvas.drawRect(mCx-4,mLongScale+50,mCx+4,mCy+30,mPaint);
        canvas.restore();

        canvas.save();
        mPaint.setColor(Color.BLACK);
        canvas.rotate(hour*30.0f+(float) (minute/2.0),mCx,mCy);
        canvas.drawRect(mCx-6,mLongScale+20,mCx+6,mCy+10,mPaint);
        canvas.restore();
        postInvalidateDelayed(1000);
    }
}
