package com.davis.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.davis.R;

public class AIDLDemoActivity extends Activity implements View.OnClickListener{

    public static final String TAG = "AIDLDemoActivity";
    private Button mBindService;
    private Button mGetStudent;
    private IStudentMgr mMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidldemo);
        init();
    }

    private void init(){
        mBindService = (Button) findViewById(R.id.bind_service);
        mGetStudent = (Button) findViewById(R.id.get_student);
        mBindService.setOnClickListener(this);
        mGetStudent.setOnClickListener(this);
    }

    ServiceConnection connection = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMgr = IStudentMgr.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMgr = null;
        }
    };
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.bind_service:
                Intent intent = new Intent("android.intent.action.studentserver");
                intent.setPackage(getPackageName());
                bindService(intent,connection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.get_student:
                if(mMgr!=null){
                    try{
                        Student student = mMgr.getStudent(1);
                        if(student!=null){
                            Log.d(TAG,String.format("NO = %d,age = %d,sex = %d",student.getNO(),student.getAge(),student.getSex()));
                        }

                    } catch (RemoteException remoteException){

                    }

                }
                break;
        }
    }
}
