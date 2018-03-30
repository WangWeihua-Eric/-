package com.eric.service;

import com.eric.pojo.FlightsPojo;

import java.io.IOException;
import java.util.List;

public interface IGraduationProjectService {

    /**
     * 查询飞行计划
     * @param goDate
     * @param depCity
     * @param arrCity
     * @return
     */
    List<FlightsPojo> queryFlightPlant(String goDate, String depCity, String arrCity) throws IOException;
}
