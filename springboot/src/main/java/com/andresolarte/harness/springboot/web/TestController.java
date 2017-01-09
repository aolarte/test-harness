package com.andresolarte.harness.springboot.web;

import com.andresolarte.harness.springboot.exception.TestException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/test")
public class TestController {


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
