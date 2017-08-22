// IStudentMgr.aidl
package com.davis.aidl;
import android.os.IBinder;
import com.davis.aidl.Student;
import com.davis.aidl.IClientCallback;

// Declare any non-default types here with import statements

interface IStudentMgr {
    void login(IBinder token,String name);
    void logout(IBinder token,String name);
    void register(IClientCallback callback);
    void unRegister(IClientCallback callback);
    Student getStudent(int aNO);
}
