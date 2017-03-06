package com.davis.crash;

import android.os.Handler;
import android.os.Looper;

public final class CrashTerminator {

    private CrashTerminator() {
    }

    private static Thread.UncaughtExceptionHandler sExceptionHandler;
    private static Thread.UncaughtExceptionHandler sUncaughtExceptionHandler;
    private static boolean sInstalled = false;//标记位，避免重复安装卸载

    public static synchronized void assemble(Thread.UncaughtExceptionHandler exceptionHandler) {
        if (sInstalled) {
            return;
        }
        sInstalled = true;
        sExceptionHandler = exceptionHandler;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        if(e instanceof ExitException){
                            break;
                        }
                        if (sExceptionHandler != null) {
                            sExceptionHandler.uncaughtException(Looper.getMainLooper().getThread(), e);
                        }
                    }
                }
            }
        });

        sUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                if (sExceptionHandler != null) {
                    sExceptionHandler.uncaughtException(t, e);
                }
            }
        });

    }

    public static synchronized void disassemble() {
        if (!sInstalled) {
            return;
        }
        sInstalled = false;
        sExceptionHandler = null;
        Thread.setDefaultUncaughtExceptionHandler(sUncaughtExceptionHandler);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                throw new ExitException("forec exit loop");
            }
        });
    }

    static class ExitException extends RuntimeException{
        public ExitException(String msg){
            super(msg);
        }
    }
}
