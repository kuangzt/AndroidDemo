package com.davis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.davis.aspect.annotation.DebugTrace;
import com.davis.aspect.annotation.SingleClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();
    private ViewGroup mViewGroup;
    private Button mTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_clip);
        init();
//        mViewGroup = (ViewGroup)findViewById(R.id.activity_main);
//        View view = LayoutInflater.from(this).inflate(R.layout.test,mViewGroup,false);
//        mViewGroup.addView(view);ImageView sd;
    }

    private void init(){
        mTest = (Button)findViewById(R.id.test);
        mTest.setOnClickListener(this);
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

    @SingleClick
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.test:
            test();
            break;
        }
    }

    @DebugTrace
    public void test(){
        try{
            Thread.sleep(100);
        }catch (Throwable t){

        }
        Log.e(TAG,"test");

    }
}
