package com.davis;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Davis
 * @Description:
 * @date 2016/12/12 16:14
 * @copyright HAWK
 */

public class LinearGradientView extends View {

    Paint mPaint;
    public LinearGradientView(Context context) {
        super(context);
        init();
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinearGradientView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint();
        LinearGradient gradient = new LinearGradient(0,50,100,50, Color.RED,Color.BLUE, Shader.TileMode.CLAMP);
        Matrix matrix=new Matrix();
        gradient.setLocalMatrix(matrix);
        matrix.setRotate(180,50,50);
        mPaint.setShader(gradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,100,100,mPaint);
    }
}
