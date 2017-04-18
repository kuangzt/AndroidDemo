package com.davis.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

/**
 * 普通应用set方法无效
 */
public final class SystemPropHelper {

    public static final String TAG = "SystemPropHelper";

    public static String getString(String key){
        try{
            Class<?> systemPropertiesClazz = Class.forName("android.os.SystemProperties");
            Method getMethod = systemPropertiesClazz.getMethod("get",String.class,String.class);
            Object ret = getMethod.invoke(null,key,"");
            if(ret instanceof String){
                return (String)ret;
            }
        } catch (Throwable ignored){

        }
        return "";
    }

    public static void setString(String key,String value){
        try{
            Class<?> systemPropertiesClazz = Class.forName("android.os.SystemProperties");
            Method setMethod = systemPropertiesClazz.getMethod("set",String.class,String.class);
            setMethod.invoke(null,key,value);
        } catch (Throwable ignored){

        }
    }

    public static String getString2(String key){
        BufferedReader br = null;
        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;
        try{
            process = Runtime.getRuntime().exec("getprop "+key);
            process.waitFor();
            if(process.exitValue()==0){
                br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String data;
                while ((data=br.readLine())!=null){
                    stringBuilder.append(data);
                }
                if(Log.isLoggable(TAG,Log.ERROR)){
                    Log.e(TAG,stringBuilder.toString());
                }
            }
        } catch (Throwable ignored){
        } finally {
            if(br!=null){
                try{
                    br.close();
                } catch (Throwable ignored){

                }
            }
            if(process!=null){
                process.destroy();
            }
        }
        return stringBuilder.toString();
    }

    public static void setString2(String key,String value){
        BufferedReader br = null;
        StringBuilder stringBuilder = new StringBuilder();
        Process process = null;
        try{
            process = Runtime.getRuntime().exec("setprop "+key+" "+value);
            process.waitFor();
            if(Log.isLoggable(TAG,Log.ERROR)){
                if(process.exitValue()!=0){
                    br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                    String data;
                    while ((data=br.readLine())!=null){
                        stringBuilder.append(data);
                    }
                    Log.e(TAG,stringBuilder.toString());
                } else {
                    br = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    String data;
                    while ((data=br.readLine())!=null){
                        stringBuilder.append(data);
                    }
                    Log.e(TAG,stringBuilder.toString());
                }
            }
        } catch (Throwable ignored){
        } finally {
            if(br!=null){
                try{
                    br.close();
                } catch (Throwable ignored){

                }
            }
            if(process!=null){
                process.destroy();
            }
        }
    }

}
