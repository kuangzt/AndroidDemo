package com.davis.propertyanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;

/**
 * @author Davis
 * @Description:
 * @date 2017/8/27 下午12:26
 * @copyright
 */

class Point {
    float x;
    float y;

    public Point() {

    }

    public Point(float x,float y) {
        this.x = x;
        this.y = y;
    }
}

class PointTypeEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point start, Point end) {
        float x = start.x + fraction * (end.x - start.x);
        float y = start.y + fraction * (end.y - start.y);
        return new Point(x,y);
    }
}

class MyBaseInterpolator implements Interpolator {
    @Override
    public float getInterpolation(float input) {
        return 1-input*input*input;
    }
}

class ColorEvaluator implements TypeEvaluator {

	private int mCurrentRed = -1;

	private int mCurrentGreen = -1;

	private int mCurrentBlue = -1;

	@Override
	public Object evaluate(float fraction, Object startValue, Object endValue) {
		String startColor = (String) startValue;
		String endColor = (String) endValue;
		int startRed = Integer.parseInt(startColor.substring(1, 3), 16);
		int startGreen = Integer.parseInt(startColor.substring(3, 5), 16);
		int startBlue = Integer.parseInt(startColor.substring(5, 7), 16);
		int endRed = Integer.parseInt(endColor.substring(1, 3), 16);
		int endGreen = Integer.parseInt(endColor.substring(3, 5), 16);
		int endBlue = Integer.parseInt(endColor.substring(5, 7), 16);
		// 初始化颜色的值
		if (mCurrentRed == -1) {
			mCurrentRed = startRed;
		}
		if (mCurrentGreen == -1) {
			mCurrentGreen = startGreen;
		}
		if (mCurrentBlue == -1) {
			mCurrentBlue = startBlue;
		}
		// 计算初始颜色和结束颜色之间的差值
		int redDiff = Math.abs(startRed - endRed);
		int greenDiff = Math.abs(startGreen - endGreen);
		int blueDiff = Math.abs(startBlue - endBlue);
		int colorDiff = redDiff + greenDiff + blueDiff;
		if (mCurrentRed != endRed) {
			mCurrentRed = getCurrentColor(startRed, endRed, colorDiff, 0,
					fraction);
		} else if (mCurrentGreen != endGreen) {
			mCurrentGreen = getCurrentColor(startGreen, endGreen, colorDiff,
					redDiff, fraction);
		} else if (mCurrentBlue != endBlue) {
			mCurrentBlue = getCurrentColor(startBlue, endBlue, colorDiff,
					redDiff + greenDiff, fraction);
		}
		// 将计算出的当前颜色的值组装返回
		String currentColor = "#" + getHexString(mCurrentRed)
				+ getHexString(mCurrentGreen) + getHexString(mCurrentBlue);
		return currentColor;
	}

	/**
	 * 根据fraction值来计算当前的颜色。
	 */
	private int getCurrentColor(int startColor, int endColor, int colorDiff,
			int offset, float fraction) {
		int currentColor;
		if (startColor > endColor) {
			currentColor = (int) (startColor - (fraction * colorDiff - offset));
			if (currentColor < endColor) {
				currentColor = endColor;
			}
		} else {
			currentColor = (int) (startColor + (fraction * colorDiff - offset));
			if (currentColor > endColor) {
				currentColor = endColor;
			}
		}
		return currentColor;
	}

	/**
	 * 将10进制颜色值转换成16进制。
	 */
	private String getHexString(int value) {
		String hexString = Integer.toHexString(value);
		if (hexString.length() == 1) {
			hexString = "0" + hexString;
		}
		return hexString;
	}

}

public class BallView extends View {

    public static final float RADIUS = 50;
    private float mRadius = RADIUS;
    private Point mCurrPoint;
    private Paint mPaint = new Paint();

    public BallView(Context context) {
        this(context,null);
    }

    public BallView(Context context, AttributeSet attrs){
        super(context,attrs);
        mPaint.setColor(Color.RED);
    }

    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
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
    }

    public void startAnim () {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointTypeEvaluator(),new Point(RADIUS,RADIUS),new Point(getWidth(),getHeight()));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrPoint = (Point) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setInterpolator(new MyBaseInterpolator());
        valueAnimator.setDuration(5000);
//        valueAnimator.start();

        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "colorddd", new ColorEvaluator(),
                "#000000", "#FFFFFF");
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(valueAnimator).with(anim2);
        animSet.setDuration(5000);
        animSet.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCurrPoint != null) {
            canvas.drawCircle(mCurrPoint.x,mCurrPoint.y,mRadius,mPaint);
        } else {
            startAnim();
        }

    }
}
