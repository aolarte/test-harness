package com.andresolarte.harness.dropwizard;

import com.andresolarte.harness.dropwizard.model.Detail;
import com.andresolarte.harness.dropwizard.model.Test;
import com.andresolarte.harness.dropwizard.model.TestOptional;
import com.andresolarte.harness.dropwizard.model.TestOptionalGuava;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    @GET
    @Path("/test1")
    public Test getTest1(){
        Test test=new Test();
        test.setName("name");
        test.setDetail(createDetail());
        return test;
    }

    @GET
    @Path("/test2")
    public TestOptional getTest2(){
        TestOptional test=new TestOptional();
        test.setName("name");
        test.setDetail(Optional.of(createDetail()));
        return test;
    }

    @GET
    @Path("/test3")
    public TestOptionalGuava getTest3(){
        TestOptionalGuava test=new TestOptionalGuava();
        test.setName("name");
        test.setDetail(com.google.common.base.Optional.of(createDetail()));
        return test;
    }

    private Detail createDetail() {
        Detail detail=new Detail();
        detail.setKey("key1");
        detail.setValue("My value");
        return detail;
    }
}
