package com.eric.utils;

import java.lang.reflect.Method;

public class LogIdUtils {

    public static String getClassName(String fullClassName) {
        return fullClassName.toLowerCase().replace('.', '_');
    }

    public static String getMethodAlarmId(Method method) {
        return getMethodLogId(method) + "_error";
    }

    public static String getMethodLogId(Method method) {
        return getClassName(method.getDeclaringClass().getName()) + "_" + method.getName().toLowerCase();
    }

    public static String getCurrentLogId() {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        return getClassName(stackTrace.getClassName()) + "_" + stackTrace.getMethodName().toLowerCase();
    }

    public static String getCurrentAlarmId() {
        StackTraceElement stackTrace = Thread.currentThread().getStackTrace()[2];
        return getClassName(stackTrace.getClassName()) + "_" + stackTrace.getMethodName().toLowerCase() + "_error";
    }

}
