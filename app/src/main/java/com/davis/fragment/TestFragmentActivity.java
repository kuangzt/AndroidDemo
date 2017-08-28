package com.davis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.davis.R;
import com.davis.util.ActivityUtils;

/**
 * @author Davis
 * @Description:
 * @date 2017/8/23 上午11:15
 * @copyright
 */
public class TestFragmentActivity extends AppCompatActivity implements IUpdateListener {

    public static final String TAG = TestFragmentActivity.class.getSimpleName();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG,"onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);
        FragmentManager fm = getSupportFragmentManager();
        ActivityUtils.addFragmentToActivity(fm,new AFragment(),R.id.table);
        ActivityUtils.addFragmentToActivity(fm,new BFragment(),R.id.content);

    }

    @Override
    protected void onStart() {
        Log.e(TAG,"onStart");
        super.onStart();

    }

    @Override
    public void update(String content) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content);
        if (fragment instanceof IContentListener) {
            IContentListener contentListener = (IContentListener) fragment;
            contentListener.onUpdate(content);
        }
    }
}
