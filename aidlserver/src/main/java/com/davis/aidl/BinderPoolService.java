package com.davis.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * @author Davis
 * @Description:
 * @date 2019/5/19 17:34
 * @copyright
 */
public class BinderPoolService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new BinderPool();
    }

    static class BinderPool extends IBinderPool.Stub {
        private StudentMgr studentMgr = new StudentMgr();
        @Override
        public IBinder queryBinder(String binderCode) throws RemoteException {
            if ("stuMgr".equals(binderCode)) {
                return studentMgr;
            }
            return null;
        }
    }
}
