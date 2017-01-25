package com.andresolarte.harness.spring4;

import com.andresolarte.harness.spring4.service.ITestService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class App {


    @Inject
    private List<ObjectFactory<ITestService>> testServiceFactoryBeans;

    public void run() throws Exception {
        for (ObjectFactory<ITestService> testServiceFactoryBean : testServiceFactoryBeans) {
            ITestService testService = testServiceFactoryBean.getObject();
            System.out.println(testService.buildMessage());
        }
    }
}
