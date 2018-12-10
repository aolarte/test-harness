package com.andresolarte.harness.springboot.web;

import com.andresolarte.harness.springboot.exception.TestException;
import com.andresolarte.harness.springboot.service.ITestService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private ITestService testService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<Long,List<Map>> test(){

        Map<Long,List<Map> > map = new Hashtable<>();
        map.put(1L, Arrays.asList(ImmutableMap.of("xx", "yy"), ImmutableMap.of("xx2", "yy2")));
        map.put(2L, Arrays.asList(ImmutableMap.of("aa", "bb"), ImmutableMap.of("aa2", "bb2")));
                return map;

    }

    @RequestMapping(value = "/verify", method = RequestMethod.GET)
    public String verify() {
        return testService.run();
    }

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    public String ok() {
        return "ok";
    }

    @RequestMapping(value = "/fail", method = RequestMethod.GET)
    public String fail() {
        //Throw Service Unavailable, status=503.
        throw new TestException();
    }
}
