package com.davis.crash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.davis.R;

public class CrashActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mAssembleBt;
    private Button mDisassembleBt;
    private Button mMainThreadExceptionBt;
    private Button mOtherThreadExceptionBt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash);
        init();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.assemble:
                CrashTerminator.assemble(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread thread, Throwable throwable) {

                    }
                });
                break;
            case R.id.disassemble:
                CrashTerminator.disassemble();
                break;
            case R.id.main_thread:
                throw new IllegalStateException("main");
            case R.id.other_thread:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        throw new IllegalStateException("other");
                    }
                }).start();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CrashTerminator.disassemble();
    }

    private void init(){
        mAssembleBt = (Button)findViewById(R.id.assemble);
        mDisassembleBt = (Button)findViewById(R.id.disassemble);
        mMainThreadExceptionBt = (Button)findViewById(R.id.main_thread);
        mOtherThreadExceptionBt = (Button)findViewById(R.id.other_thread);
        mAssembleBt.setOnClickListener(this);
        mDisassembleBt.setOnClickListener(this);
        mMainThreadExceptionBt.setOnClickListener(this);
        mOtherThreadExceptionBt.setOnClickListener(this);
    }
}
