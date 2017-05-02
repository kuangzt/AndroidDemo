// IStudentMgr.aidl
package com.davis.aidl;
import com.davis.aidl.Student;

// Declare any non-default types here with import statements

interface IStudentMgr {
    Student getStudent(int aNO);
}
