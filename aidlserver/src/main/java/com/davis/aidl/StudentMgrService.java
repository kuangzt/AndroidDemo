package com.davis.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

public class StudentMgrService extends Service {

    public static final String TAG = "StudentMgrService";
    private StudentMgr mMgr = new StudentMgr();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,intent.getAction());
        return mMgr;
    }

    static class StudentMgr extends IStudentMgr.Stub{
        private SparseArray<Student> mStudents = new SparseArray<Student>();
        private List<Client> mClientList = new ArrayList<>();
        private RemoteCallbackList<IClientCallback> callbackList = new MyRemoteCallbackList();

        public StudentMgr(){
            Student student = new Student();
            student.setNO(1);
            student.setAge(12);
            student.setSex(1);
            mStudents.put(1,student);
        }

        @Override
        public void login(IBinder token,String name) throws RemoteException {
            int index = findClient(token);
            if (index >= 0) {
                return;
            }
            Client client = new Client(token);
            token.linkToDeath(client,0);
            mClientList.add(client);
            notifyLogin(name);
        }

        @Override
        public void logout(IBinder token,String name) throws RemoteException {
            int index = findClient(token);
            if (index <= -1) {
                return;
            }
            Client client = mClientList.remove(index);
            token.unlinkToDeath(client,0);
            notifyLogout(name);
        }

        @Override
        public Student getStudent(int aNO) throws RemoteException {
            return mStudents.get(aNO);
        }

        @Override
        public void register(IClientCallback callback) throws RemoteException {
            callbackList.register(callback);
        }

        @Override
        public void unRegister(IClientCallback callback) throws RemoteException {
            callbackList.unregister(callback);
        }

        private void notifyLogin(String name) {
            final int len = callbackList.beginBroadcast();
            for (int i = 0; i < len; i++) {
                try {
                    // 通知回调
                    callbackList.getBroadcastItem(i).onLogin(name);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            callbackList.finishBroadcast();
        }

        private void notifyLogout(String name) {
            final int len = callbackList.beginBroadcast();
            for (int i = 0; i < len; i++) {
                try {
                    // 通知回调
                    callbackList.getBroadcastItem(i).onLogout(name);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            callbackList.finishBroadcast();
        }

        private int findClient(IBinder token) {
            for (int i = 0; i < mClientList.size(); i++) {
                if (mClientList.get(i).mToken == token) {
                    return i;
                }
            }
            return -1;
        }

        class MyRemoteCallbackList extends RemoteCallbackList<IClientCallback> {
            @Override
            public void onCallbackDied(IClientCallback callback, Object cookie) {
                super.onCallbackDied(callback, cookie);
                Log.e(TAG,"onCallbackDied");
            }
        }

        class Client implements IBinder.DeathRecipient {

            public IBinder mToken;
            public Client(IBinder token) {
                this.mToken = token;
            }

            @Override
            public void binderDied() {
                Log.e(TAG,"binderDied");
            }
        }

    }

}
