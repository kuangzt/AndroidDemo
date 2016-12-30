package com.davis.propertyanimation;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.davis.R;

/**
 * @author Davis
 * @Description:
 * @date 2016/11/5 15:23
 * @copyright Davis
 */

public class PropertyAnimationActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = PropertyAnimationActivity.class.getSimpleName();
    private Button mTestValue;
    private Button mTestObject;
    private TextView mTV;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animation);
        init();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.testvalueanimation:
                testValueAnimator();
                break;
            case R.id.testobjectanimation:
                testObjectAnimator();
                break;
        }
    }

    private void init(){
        mTestValue = (Button)findViewById(R.id.testvalueanimation);
        mTestValue.setOnClickListener(this);
        mTestObject = (Button)findViewById(R.id.testobjectanimation);
        mTestObject.setOnClickListener(this);
        mTV = (TextView)findViewById(R.id.tv);
    }

    private void testValueAnimator(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.0f,1.0f);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float current = (float)animation.getAnimatedValue();
                Log.e(TAG,"current value is "+current);
            }
        });
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e(TAG,"onAnimationStart");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e(TAG,"onAnimationEnd");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.e(TAG,"onAnimationCancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e(TAG,"onAnimationRepeat");
            }
        });
        valueAnimator.start();
    }

    private void testObjectAnimator(){
        float translationX = mTV.getTranslationX();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mTV,"translationX",translationX,translationX+1000);
        objectAnimator.setDuration(1000);
        objectAnimator.start();
    }
}
