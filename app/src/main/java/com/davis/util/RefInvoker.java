package com.davis.util;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import android.util.Log;

@SuppressWarnings("unchecked")
public class RefInvoker {
    private static final String TAG = "RefInvoker";

    private static final ClassLoader system = ClassLoader.getSystemClassLoader();
    private static final ClassLoader bootloader = system.getParent();
    private static final ClassLoader application = RefInvoker.class.getClassLoader();

    private static HashMap<String, Class> clazzCache = new HashMap<String, Class>();

    public static Class forName(String clazzName) throws ClassNotFoundException {
        Class clazz = clazzCache.get(clazzName);
        if (clazz == null) {
            clazz = Class.forName(clazzName);
            ClassLoader cl = clazz.getClassLoader();
            if (cl == system || cl == application || cl == bootloader) {
                clazzCache.put(clazzName, clazz);
            }
        }
        return clazz;
    }

    public static Object newInstance(String className, Class[] paramTypes, Object[] paramValues) {
        try {
            Class clazz = forName(className);
            Constructor constructor = clazz.getConstructor(paramTypes);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(paramValues);
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
        return null;
    }

    public static Object invokeMethod(Object target, String className, String methodName, Class[] paramTypes,
                                      Object[] paramValues) {

        try {
            Class clazz = forName(className);
            return invokeMethod(target, clazz, methodName, paramTypes, paramValues);
        } catch (ClassNotFoundException e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
        return null;
    }

    public static Object invokeMethod(Object target, Class clazz, String methodName, Class[] paramTypes,
                                      Object[] paramValues) {
        try {
            Method method = clazz.getDeclaredMethod(methodName, paramTypes);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method.invoke(target, paramValues);
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Object getField(Object target, String className, String fieldName) {
        try {
            Class clazz = forName(className);
            return getField(target, clazz, fieldName);
        } catch (ClassNotFoundException e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static Object getField(Object target, Class clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field.get(target);
        } catch (SecurityException e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        } catch (NoSuchFieldException e) {
            // try supper for Miui, Miui has a class named MiuiPhoneWindow
            try {
                Field field = clazz.getSuperclass().getDeclaredField(fieldName);
                field.setAccessible(true);
                return field.get(target);
            } catch (Exception superE) {
                if (Log.isLoggable(TAG, Log.WARN)) {
                    Log.w(TAG, e);
                    Log.w(TAG, superE);
                }
            }
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
        return null;

    }

    @SuppressWarnings("rawtypes")
    public static void setField(Object target, String className, String fieldName, Object fieldValue) {
        try {
            Class clazz = forName(className);
            setField(target, clazz, fieldName, fieldValue);
        } catch (ClassNotFoundException e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
    }

    public static void setField(Object target, Class clazz, String fieldName, Object fieldValue) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            field.set(target, fieldValue);
        } catch (SecurityException e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        } catch (NoSuchFieldException e) {
            // try supper for Miui, Miui has a class named MiuiPhoneWindow
            try {
                Field field = clazz.getSuperclass().getDeclaredField(fieldName);
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(target, fieldValue);
            } catch (Exception superE) {
                if (Log.isLoggable(TAG, Log.WARN)) {
                    Log.w(TAG, e);
                }
            }
        } catch (Exception e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
    }

    public static Method findMethod(Object object, String methodName, Class[] clazzes) {
        try {
            return object.getClass().getDeclaredMethod(methodName, clazzes);
        } catch (NoSuchMethodException e) {
            if (Log.isLoggable(TAG, Log.WARN)) {
                Log.w(TAG, e);
            }
        }
        return null;
    }

    public static Method findMethod(Object object, String methodName, Object[] args) {
        if (args == null) {
            try {
                return object.getClass().getDeclaredMethod(methodName, (Class[]) null);
            } catch (NoSuchMethodException e) {
                if (Log.isLoggable(TAG, Log.WARN)) {
                    Log.w(TAG, e);
                }
            }
            return null;
        } else {
            Method[] methods = object.getClass().getDeclaredMethods();
            boolean isFound = false;
            Method method = null;
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    Class<?>[] types = m.getParameterTypes();
                    if (types.length == args.length) {
                        isFound = true;
                        for (int i = 0; i < args.length; i++) {
                            if (!(types[i] == args[i].getClass() || (types[i].isPrimitive() && primitiveToWrapper(types[i]) == args[i]
                                    .getClass()))) {
                                isFound = false;
                                break;
                            }
                        }
                        if (isFound) {
                            method = m;
                            break;
                        }
                    }
                }
            }
            return method;
        }
    }

    private static final Map<Class<?>, Class<?>> primitiveWrapperMap = new HashMap<Class<?>, Class<?>>();

    static {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
    }

    static Class<?> primitiveToWrapper(final Class<?> cls) {
        Class<?> convertedClass = cls;
        if (cls != null && cls.isPrimitive()) {
            convertedClass = primitiveWrapperMap.get(cls);
        }
        return convertedClass;
    }

}

