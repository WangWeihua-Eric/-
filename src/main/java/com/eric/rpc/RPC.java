package com.eric.rpc;

import com.eric.pojo.ConvertCardDetailReqPojo;
import com.eric.rpc.ConvertCardDetailRsp;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import com.qunar.mobile.car.qb.drivcommon.model.BaseRsp;
import com.qunar.mobile.car.qb.drivcommon.utils.HttpClientAdapterUtils;
import com.qunar.mobile.car.qb.drivcommon.utils.LogIdUtils;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by wangweihua on 2018/4/30.
 */

@Component
public class RPC {

    private String convertCardDetailUrl = "https://api.xhhmei.com/Open/convertCardDetail";
    private String convertCardUseUrl = "";

    private final static Integer RETRY_TIMES = null;
    private final static Long CONN_TIME_OUT = 3000L;
    private final static Long READ_TIME_OUT = 3000L;

    public String queryListByCondition(ConvertCardDetailReqPojo req, String urlTemp) throws BaseException, IOException {

        //访问准备
        URL url = new URL(urlTemp);
//post参数
        Map<String,Object> params = req.toMap();
        params.put("geomInfo", "");
        params.put("f", "json");

        //开始访问
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        System.out.println(response);
        return response;

    }
}


