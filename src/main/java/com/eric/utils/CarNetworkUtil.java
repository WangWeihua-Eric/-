package com.eric.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 网络信息工具类
 * @author yinglong.li
 * @version 1.0.0
 */
public abstract class CarNetworkUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarNetworkUtil.class);
    
    /** 本地IP信息 */
    private static String LOCAL_IP_INFO = null;
    
    /**
     * 获取本地IP信息，支持多网卡情况
     * @return
     */
    public static String getLocalIPInfo() {
        String ipinfo = LOCAL_IP_INFO;
        if(StringUtils.isBlank(ipinfo)){
            //没必要同步，多个线程可以同时执行
            ipinfo = doGetLocalIPInfo();
            //保存，避免每次调用都调用doGetLocalIPInfo方法，耗费时间
            LOCAL_IP_INFO = ipinfo;
        }
        return ipinfo;
    }
    
    /**
     * 获取本地IP信息，支持多网卡情况
     * @return
     */
    private static String doGetLocalIPInfo() {
        StringBuilder ipsb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if(networkInterfaces == null){
                return ipsb.toString();
            }
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress != null 
                            && inetAddress instanceof Inet4Address
                            && !"127.0.0.1".equals(inetAddress.getHostAddress())) { 
                        ipsb.append(inetAddress.getHostAddress());
                        ipsb.append("(");
                        ipsb.append(inetAddress.getHostName());
                        ipsb.append("); ");
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.warn("getLocalIPInfo Exception!",e);
        }
        return ipsb.toString();
    }
}
