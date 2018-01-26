package com.eric.utils;

/**
 * 异常信息工具类
 * @author yinglong.li
 * @version 1.0.0
 */
public abstract class CarExceptionUtil {
    /** 异常堆栈信息分隔串 */
    private static final String EX_ST_SEP = " --> ";
    private static final int MAX_TRACE_COUNT_LIMIT = 8;
    /**
     * 获取异常堆栈信息串
     * @param thr
     * @return
     */
    public static String getExStackTraceStr(Throwable thr){
        StringBuilder stsb = new StringBuilder();
        stsb.append(thr.toString());
        StackTraceElement[] trace = thr.getStackTrace();
        int limit = Math.min(MAX_TRACE_COUNT_LIMIT, trace.length);
        for (int i = 0; i < limit; i++) {
            stsb.append(EX_ST_SEP);
            stsb.append("at ");
            stsb.append(trace[i].toString());
        }
        
        Throwable ourCause = thr.getCause();
        if (ourCause != null){
            doFillExStackTraceAsCause(stsb, ourCause, trace);
        }
        
        return stsb.toString();
    }
    
    /**
     * 填充异常cause堆栈信息
     * @param stsb
     * @param cause
     * @param causedTrace
     */
    private static void doFillExStackTraceAsCause(StringBuilder stsb,Throwable cause,
            StackTraceElement[] causedTrace) {
        // Compute number of frames in common between this and caused
        StackTraceElement[] trace = cause.getStackTrace();
        int m = trace.length - 1;
        int n = causedTrace.length - 1;
        while (m >= 0 && n >= 0 && trace[m].equals(causedTrace[n])) {
            m--;
            n--;
        }
        int framesInCommon = trace.length - 1 - m;
        
        stsb.append(EX_ST_SEP);
        stsb.append("Caused by: ");
        stsb.append(cause.toString());
        for (int i = 0; i <= m; i++){
            stsb.append(EX_ST_SEP);
            stsb.append("at ");
            stsb.append(trace[i]);
        }
        if (framesInCommon != 0){
            stsb.append(EX_ST_SEP);
            stsb.append("... ");
            stsb.append(framesInCommon);
            stsb.append(" more");
        }
        // Recurse if we have a cause
        Throwable ourCause = cause.getCause();
        if (ourCause != null){
            doFillExStackTraceAsCause(stsb, ourCause, trace);
        }
    }
}
