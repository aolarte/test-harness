package com.andresolarte.harness.eureka.service;

import com.andresolarte.harness.eureka.model.ResultModel;
import org.springframework.stereotype.Component;

@Component
public class Endpoint implements IEndpoint {


    @Override
    public ResultModel message() {
        System.out.println("Executing message");
        ResultModel ret=new ResultModel();
        ret.message="Hello World";
        return ret;
    }
}
