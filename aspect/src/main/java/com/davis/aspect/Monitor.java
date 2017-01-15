package com.davis.aspect;

/**
 * @author zengtao.kuang
 * @Description:
 * @date 2017/1/15 11:26
 * @copyright TCL-MIE
 */
public class Monitor {
    private long mStartTime = 0;
    private long mStopTime = 0;
    private long mElapsedTime = 0;

    private void reset(){
        mStartTime = 0;
        mStopTime = 0;
        mElapsedTime = 0;
    }
    public void start(){
        reset();
        mStartTime =  System.currentTimeMillis();
    }

    public void stop(){
        if(mStartTime!=0){
            mStopTime = System.currentTimeMillis();
            mElapsedTime = mStopTime-mStartTime;
        }else{
            reset();
        }
    }

    public long getTotalTimeMillis(){
        return mElapsedTime;
    }
}
