package com.davis.aidl;

import android.os.IBinder;
import android.os.RemoteException;

/**
 * @author Davis
 * @Description:
 * @date 2019/5/19 18:28
 * @copyright
 */
public class StudentMgr extends IStudentMgr.Stub {

    @Override
    public void login(IBinder token, String name) throws RemoteException {

    }

    @Override
    public void logout(IBinder token, String name) throws RemoteException {

    }

    @Override
    public void register(IClientCallback callback) throws RemoteException {

    }

    @Override
    public void unRegister(IClientCallback callback) throws RemoteException {

    }

    @Override
    public Student getStudent(int aNO) throws RemoteException {
        Student student = new Student();
        student.setNO(1);
        student.setAge(18);
        student.setSex(1);
        return student;
    }
}
