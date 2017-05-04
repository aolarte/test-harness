package com.andresolarte.harness.spring4.processor;

import com.andresolarte.harness.spring4.processor.annotation.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class LogBeanPostProcessor implements BeanPostProcessor{

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o.getClass().isAnnotationPresent(Log.class)) {
            return o;
        } else {
            //Doesn't have out annotation, nothing to do
            return o;
        }
    }

    static class TestInvocationHandler implements InvocationHandler {
        private final Object target;

        public TestInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Entering method: " + method.getName());
            try {
                return method.invoke(target, args);
            } finally {
                System.out.println("Leaving method: " + method.getName());
            }
        }
    }
}
