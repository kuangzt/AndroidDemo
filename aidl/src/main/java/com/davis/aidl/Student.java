package com.davis.aidl;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {

    private int mNO;//学号
    private int mAge;//年龄
    private int mSex;//性别，1表示女，0表示男

    public Student(){

    }

    public Student(Parcel source){
        mNO = source.readInt();
        mAge = source.readInt();
        mSex = source.readInt();
    }

    public void setNO(int no){
        this.mNO = no;
    }

    public void setAge(int age){
        this.mAge = age;
    }

    public void setSex(int sex){
        this.mSex = sex;
    }

    public int getNO(){
        return this.mNO;
    }

    public int getAge(){
        return this.mAge;
    }

    public int getSex(){
        return this.mSex;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mNO);
        dest.writeInt(mAge);
        dest.writeInt(mSex);
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
