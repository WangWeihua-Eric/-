package com.eric.controller;

import com.eric.pojo.FlightsPojo;
import com.eric.service.IGraduationProjectService;
import com.google.common.base.Strings;
import com.qunar.mobile.car.qb.drivcommon.enums.CommonBaseStatus;
import com.qunar.mobile.car.qb.drivcommon.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/graduation")
public class GraduationProjectController {

    @Autowired
    private IGraduationProjectService graduationProjectService;

    /**
     * 查询飞行计划
     * @param goDate
     * @param depCity
     * @param arrCity
     * @param request
     * @return
     */
    @RequestMapping(value = "/query/flight/plant")
    @ResponseBody
    public Object queryFlightPlant(@RequestParam(value = "goDate", required = false) String goDate,
                                   @RequestParam(value = "depCity", required = false) String depCity,
                                   @RequestParam(value = "arrCity", required = false) String arrCity,
                                    HttpServletRequest request) throws IOException {
        if(Strings.isNullOrEmpty(goDate) || Strings.isNullOrEmpty(depCity) || Strings.isNullOrEmpty(arrCity)){
            throw new BaseException(CommonBaseStatus.PARAM_ERROR);
        }
        List<FlightsPojo> flightsPojoList = graduationProjectService.queryFlightPlant(goDate, depCity, arrCity);
        return flightsPojoList;
    }
}
