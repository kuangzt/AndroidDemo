package com.davis;

import android.os.FileObserver;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @author
 * @Description: FileObserver可以监听两种类型的文件：一种是单个文件，另一种是文件目录。
 * 需要注意的是监听文件目录的时候有个不能递归子目录的问题，
 * 因此要么确保监听的文件下没有子目录，要么做特殊的操作，
 * 手动递归监听每一个子目录。
 * 需要两个权限
 * <!--往sdcard中写入数据的权限 -->
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 * <!--在sdcard中创建/删除文件的权限 -->
 * <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"></uses-permission>
 * @date 2018/1/5 14:35
 * @copyright HAWK
 */

public class FileMonitor extends FileObserver {

    public static final String TAG = "FileMonitor";
    public FileMonitor(String path, int mask) {
        super(path,mask);
    }

    @Override
    public void onEvent(int event, @Nullable String path) {
        /*event的值是与0x40000000进行或运算后的值，所以在case之前需要先和FileObserver.ALL_EVENTS进行与运算*/
        int e = event & FileObserver.ALL_EVENTS;
        if (e == DELETE) {
            Log.d(TAG,"delete " + path);
        } else if (e == CREATE) {
            Log.d(TAG,"create " + path);
        }
    }
}
