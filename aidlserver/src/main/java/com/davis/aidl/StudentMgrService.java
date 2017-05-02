package com.davis.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;

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
        public StudentMgr(){
            Student student = new Student();
            student.setNO(1);
            student.setAge(12);
            student.setSex(1);
            mStudents.put(1,student);
        }

        @Override
        public Student getStudent(int aNO) throws RemoteException {
            return mStudents.get(aNO);
        }
    }

}
