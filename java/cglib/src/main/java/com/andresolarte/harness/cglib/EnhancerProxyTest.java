package com.andresolarte.harness.cglib;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.core.Predicate;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.NoOp;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.function.Function;


public class EnhancerProxyTest {

    private final String message;


    public EnhancerProxyTest(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

    private static final Objenesis OBJENESIS = new ObjenesisStd();

    public static void main(String... args) {

        EnhancerProxyTest originalObject = new EnhancerProxyTest("Test Message");
        System.out.println("Original:" + originalObject.get());

        EnhancerProxyTest proxyObject = buildProxy(originalObject);
        System.out.println("Proxy:" + proxyObject.get());

    }


    public static EnhancerProxyTest buildProxy(final EnhancerProxyTest originalObject) {

        InvocationHandler cglibHandler =
                (proxy, method, args) -> {
                    if (method.getName().startsWith("get")
                            && method.getReturnType() == String.class) {
                        return "<p>" + method.invoke(originalObject, args) + "</p>";
                    } else {
                        return method.invoke(originalObject, args);
                    }
                };

        Class<?> proxyClass = createProxyClass(originalObject.getClass());
        Factory proxy = (Factory) OBJENESIS.newInstance(proxyClass);
        proxy.setCallbacks(new Callback[]{cglibHandler, NoOp.INSTANCE});

        return (EnhancerProxyTest) proxy;

    }

    public static Class<?> createProxyClass(Class<?> baseClass) {

        Enhancer enhancer = new Enhancer();
        enhancer.setUseFactory(true);
        enhancer.setSuperclass(baseClass);
        enhancer.setCallbackType(net.sf.cglib.proxy.InvocationHandler.class);
        return enhancer.createClass();
    }


}
