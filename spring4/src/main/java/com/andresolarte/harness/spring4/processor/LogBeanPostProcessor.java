package com.andresolarte.harness.spring4.processor;

import com.andresolarte.harness.spring4.processor.annotation.Log;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.NoOp;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import net.sf.cglib.proxy.Enhancer;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class LogBeanPostProcessor implements BeanPostProcessor{

    private final Objenesis OBJENESIS = new ObjenesisStd();

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        if (o.getClass().isAnnotationPresent(Log.class)) {
            //Has our annotation, let's enhance the object
            Class<?> proxyClass = createProxyClass(o.getClass());
            Factory proxy = (Factory) OBJENESIS.newInstance(proxyClass);
            proxy.setCallbacks(new Callback[]{new TestInvocationHandler(o), NoOp.INSTANCE}); //I think NoOp.INSTANCE is not needed
            return proxy;
        } else {
            //Doesn't have out annotation, nothing to do
            return o;
        }
    }



    public Class<?> createProxyClass(Class<?> baseClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(baseClass);
        enhancer.setUseFactory(true);
        enhancer.setCallbackType(InvocationHandler.class);
        return enhancer.createClass();
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
