package com.andresolarte.harness.eureka.service;

import com.andresolarte.harness.eureka.model.ResultModel;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public interface IEndpoint {

    @GET
    @Produces({"application/json"})
    ResultModel message();

}