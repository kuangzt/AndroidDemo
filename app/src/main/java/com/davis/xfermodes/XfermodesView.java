package com.davis.xfermodes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import com.davis.R;

/**
 * https://developer.android.com/reference/android/graphics/PorterDuff.Mode.html
 * 1.不允许硬件加速
 * 2.两个Bitmap大小一致
 * 3.背景颜色透明
 */
public class XfermodesView extends View {

    private Paint mPaint = new Paint();
    private Xfermode mode = new PorterDuffXfermode(PorterDuff.Mode.ADD);
    private Bitmap destinationImage;
    private Bitmap sourceImage;
    public XfermodesView(Context context) {
        this(context,null);
    }

    public XfermodesView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XfermodesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        destinationImage = ((BitmapDrawable)(context.getResources().getDrawable(R.drawable.composite_dst))).getBitmap();
        sourceImage = ((BitmapDrawable)(context.getResources().getDrawable(R.drawable.composite_src))).getBitmap();
//        destinationImage = makeDst(dip2px(context,100),dip2px(context,100));
//        sourceImage = makeSrc(dip2px(context,100),dip2px(context,100));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int id = canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(destinationImage, 0, 0, mPaint);
        mPaint.setXfermode(mode);
        canvas.drawBitmap(sourceImage, 0, 0, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(id);
    }

    public void setMode(Xfermode mode){
        this.mode = mode;
    }

    static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.RED);
        c.drawRoundRect(new RectF(0, 0, w, h),100,100,p);
        return bm;
    }

    // create a bitmap with a rect, used for the "src" image
    static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.YELLOW);
        c.drawRect(new RectF(0, h/2.0F, w, h), p);
        return bm;
    }

    public static int dip2px(Context context,float dp){
        int px = (int) (dp + 0.5f);
        try {
            px = (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return px;
    }
}
