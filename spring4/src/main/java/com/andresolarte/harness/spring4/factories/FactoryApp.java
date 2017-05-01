package com.andresolarte.harness.spring4.factories;

import com.andresolarte.harness.spring4.factories.service.ITestService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Component
public class FactoryApp {
    @Inject
    private List<ObjectFactory<ITestService>> testServiceFactoryBeans;

    @Inject
    private List<ITestService> testServiceBeanList;

    @Inject
    private Map<String, ITestService> testServiceBeanMap;


    public void run() throws Exception {


        for (ITestService service : testServiceBeanList) {
            System.out.println(service.buildMessage());
        }
        System.out.println("***********************");
        for (Map.Entry<String, ITestService> entry : testServiceBeanMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().buildMessage());
        }
        System.out.println("***********************");
        for (ObjectFactory<ITestService> testServiceFactoryBean : testServiceFactoryBeans) {
            ITestService testService = testServiceFactoryBean.getObject();
            System.out.println(testService.buildMessage());
        }
    }
}
