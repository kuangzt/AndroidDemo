package com.davis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private ViewGroup mViewGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_clip);
//        mViewGroup = (ViewGroup)findViewById(R.id.activity_main);
//        View view = LayoutInflater.from(this).inflate(R.layout.test,mViewGroup,false);
//        mViewGroup.addView(view);ImageView sd;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e(TAG,"MainActivity:-->"+"dispatchTouchEvent"+MotionEvent.actionToString(event.getAction()));
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG,"MainActivity:-->"+"onTouchEvent"+MotionEvent.actionToString(event.getAction()));
        return super.onTouchEvent(event);
    }
}
