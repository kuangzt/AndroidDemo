package com.davis.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Davis
 * @Description:
 * @date 2019/5/19 17:41
 * @copyright
 */
public class BinderPoolMgr {

    private Context mContext;
    private IBinderPool mBinderPool;
    private String serviceAction;

    private BinderPoolMgr() {

    }

    public void setAppContext(Context context) {
        this.mContext = context;
    }

    public void setServiceAction(String action) {
        this.serviceAction = action;
    }

    static class BinderPoolMgrHolder {
        public static BinderPoolMgr INSTASNCE = new BinderPoolMgr();
    }

    public static BinderPoolMgr getInstance() {
        return BinderPoolMgrHolder.INSTASNCE;
    }

    public void connectBinderPoolService() {
        Intent service = new Intent(serviceAction);
        service.setPackage(mContext.getPackageName());
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mContext.bindService(service, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mBinderPool = IBinderPool.Stub.asInterface(service);
                try {
                    IBinderPool binderPool = mBinderPool;
                    if (binderPool != null) {
                        IBinder binder = binderPool.asBinder();
                        if (binder != null) {
                            binder.linkToDeath(recipient,0);
                        }
                    }
                } catch (RemoteException re) {

                }
                countDownLatch.countDown();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                //不允许解绑,所以此处可以忽略
            }
        },Context.BIND_AUTO_CREATE);

        try {
            countDownLatch.await(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }
    }

    public IBinder queryBinder(String binderCode) {
        IBinderPool binderPool = mBinderPool;
        if (binderPool == null) {
            connectBinderPoolService();
        }
        binderPool = mBinderPool;
        if (binderPool != null) {
            try {
                return binderPool.queryBinder(binderCode);
            } catch (RemoteException re) {

            }

        }
        return null;
    }

    private IBinder.DeathRecipient recipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            IBinderPool binderPool = mBinderPool;
            if (binderPool != null) {
                IBinder binder = binderPool.asBinder();
                binder.unlinkToDeath(recipient,0);
            }
            mBinderPool = null;
            connectBinderPoolService();
        }
    };

}
