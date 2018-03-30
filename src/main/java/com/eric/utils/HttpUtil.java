package com.eric.utils;

import com.qunar.car.http.params.HttpJ2OParams;
import com.qunar.car.http.params.OwlHttpParams;
import com.qunar.mobile.car.common.util.JsonUtil;
import org.codehaus.jackson.type.TypeReference;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
    public static String postWithApplicationJson(String uri, String param) throws IOException {
        // 创建url资源
        URL url = new URL(uri);
        // 建立http连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置允许输出
        conn.setDoOutput(true);

        conn.setDoInput(true);

        // 设置不用缓存
        conn.setUseCaches(false);
        // 设置传递方式
        conn.setRequestMethod("POST");

        // 设置维持长连接
        conn.setRequestProperty("Connection", "Keep-Alive");
        // 设置文件字符集:
        conn.setRequestProperty("Charset", "UTF-8");
        //转换为字节数组
        byte[] data = (param).getBytes();
        // 设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(data.length));

        // 设置文件类型:
        conn.setRequestProperty("Content-Type", "application/json");

        conn.setRequestProperty("accept", "/");


        // 开始连接请求
        conn.connect();
        OutputStream out = conn.getOutputStream();
        // 写入请求的字符串
        out.write((param).getBytes());
        out.flush();
        out.close();

        System.out.println(conn.getResponseCode());

        String rsp = "";
        // 请求返回的状态
        if (conn.getResponseCode() == 200) {
            System.out.println("连接成功");
            // 请求返回的数据
            InputStream in = conn.getInputStream();
            String a = process(in, "UTF-8");
            rsp = a;
        } else {
            System.out.println("no++");
        }
        return rsp;
    }

    private static String process(InputStream in, String charset) {
        byte[] buf;
        StringBuffer sb = new StringBuffer();
        try {
            buf = input2byte(in);
            sb.append(new String(buf, 0, buf.length, charset));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    private static byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 1024)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
