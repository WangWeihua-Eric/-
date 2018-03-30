package com.eric.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eric.pojo.FlightsPojo;
import com.eric.rpc.FlightPlantInfoPojo;
import com.eric.rpc.FlightPlantRpcPojo;
import com.eric.rpc.QueryFlightPlantRpcPojo;
import com.eric.service.IGraduationProjectService;
import com.eric.utils.HttpUtil;
import com.qunar.car.http.params.HttpJ2OParams;
import com.qunar.car.http.params.OwlHttpParams;
import com.qunar.mobile.car.common.util.JsonUtil;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class GraduationProjectServiceImpl implements IGraduationProjectService{
    @Override
    public List<FlightsPojo> queryFlightPlant(String goDate, String depCity, String arrCity) throws IOException {
        FlightPlantInfoPojo flightPlantInfoPojo = new FlightPlantInfoPojo();
        flightPlantInfoPojo.setGoDate(goDate);
        flightPlantInfoPojo.setDepCity(depCity);
        flightPlantInfoPojo.setArrCity(arrCity);
        flightPlantInfoPojo.setSearchKey("flightCity");
        flightPlantInfoPojo.setActiveIndex("1");
        flightPlantInfoPojo.setT("f_flightstatus_list");
        FlightPlantRpcPojo flightPlantRpcPojo = new FlightPlantRpcPojo();
        flightPlantRpcPojo.setB(flightPlantInfoPojo);
        String uri = "https://dynamic.flight.qunar.com/flightDynamic/ncs/api/flightList?t=f_flightstatus_list&timestamp=";
        StringBuffer uriTemp = new StringBuffer();
        uriTemp.append(uri);
        Date nowTime = new Date();
        Long timestamp = nowTime.getTime();
        uriTemp.append(timestamp);
        String rps = HttpUtil.postWithApplicationJson(uriTemp.toString(), JsonUtil.toJson(flightPlantRpcPojo));
        JSONArray jsonArray = JSONObject.parseArray(rps);
        String temp = jsonArray.getJSONObject(0).toString();
        OwlHttpParams httpParams = new OwlHttpParams();
        httpParams.appendHttpParams(OwlHttpParams.ParamsEnum.J2O_REF_TYPE, new TypeReference<QueryFlightPlantRpcPojo>() {
        });
        HttpJ2OParams<QueryFlightPlantRpcPojo> j2OParams = httpParams.getJ2OParams();

        QueryFlightPlantRpcPojo rsp = null;
        if (j2OParams.isUseClazz()) {
            rsp = JsonUtil.fromJson(temp, j2OParams.getTypeClazz());
        } else {
            rsp = JsonUtil.fromJson(temp, j2OParams.getTypeRef());
        }
        return rsp.getData().getFlights();
    }
}
