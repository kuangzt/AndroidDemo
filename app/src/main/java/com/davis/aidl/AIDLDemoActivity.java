package com.davis.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.davis.R;
import com.davis.base.AppContext;

public class AIDLDemoActivity extends Activity implements View.OnClickListener {

    public static final String TAG = "AIDLDemoActivity";
    public static final String NAME = "aidl";
    private IBinder token = new Binder();
    private Button mBindService;
    private Button mLoginBt;
    private Button mRegisterBt;
    private Button mLogoutBt;
    private Button mUnregisterBt;
    private Button mGetStudent;
    private IStudentMgr mMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidldemo);
        init();
    }

    private void init() {
        mBindService = (Button) findViewById(R.id.bind_service);
        mLoginBt = (Button) findViewById(R.id.login);
        mRegisterBt = (Button) findViewById(R.id.register);
        mLogoutBt = (Button) findViewById(R.id.logout);
        mUnregisterBt = (Button) findViewById(R.id.unregister);
        mGetStudent = (Button) findViewById(R.id.get_student);
        mBindService.setOnClickListener(this);
        mLoginBt.setOnClickListener(this);
        mRegisterBt.setOnClickListener(this);
        mLogoutBt.setOnClickListener(this);
        mUnregisterBt.setOnClickListener(this);
        mGetStudent.setOnClickListener(this);
    }

    ServiceConnection connection = new ServiceConnection() {
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
        switch (id) {
            case R.id.bind_service:
//                Intent intent = new Intent("android.intent.action.studentserver");
//                intent.setPackage(getPackageName());
//                bindService(intent, connection, Context.BIND_AUTO_CREATE);
                BinderPoolMgr binderPoolMgr = BinderPoolMgr.getInstance();
                binderPoolMgr.setAppContext(AppContext.getInstance().getContext());
                binderPoolMgr.setServiceAction("android.intent.action.bindpool");
                IBinder binder = (IBinder) binderPoolMgr.queryBinder("stuMgr");
                if (binder != null) {
                    try {
                        Student student = IStudentMgr.Stub.asInterface(binder).getStudent(1);
                        Log.e("TAG",""+student.getNO());
                    } catch (RemoteException re) {

                    }

                }
                break;
            case R.id.login:
                if (mMgr != null) {
                    try {
                        mMgr.login(token, NAME);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.register:
                if (mMgr != null) {
                    try {
                        mMgr.register(callback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.logout:
                if (mMgr != null) {
                    try {
                        mMgr.logout(token, NAME);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.unregister:
                if (mMgr != null) {
                    try {
                        mMgr.unRegister(callback);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.get_student:
                if (mMgr != null) {
                    try {
                        Student student = mMgr.getStudent(1);
                        if (student != null) {
                            Log.d(TAG, String.format("NO = %d,age = %d,sex = %d", student.getNO(), student.getAge(), student.getSex()));
                        }

                    } catch (RemoteException remoteException) {

                    }
                }
                break;
        }
    }

    IClientCallback callback = new IClientCallback.Stub() {
        @Override
        public void onLogin(String name) throws RemoteException {
            Log.e(TAG,"onLogin:"+name);
        }

        @Override
        public void onLogout(String name) throws RemoteException {
            Log.e(TAG,"onLogout:"+name);
        }
    };
}
