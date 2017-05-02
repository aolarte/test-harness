package com.andresolarte.harness.spring4.properties.source;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class ConstantPropertySource extends PropertySource<Object> {

   public ConstantPropertySource(ConfigurableEnvironment env) {
        super("constant");
        MutablePropertySources sources = env.getPropertySources();
        sources.addFirst(this);
    }

    @Override
    public Object getProperty(String s) {
        if ("key1".equalsIgnoreCase(s)) {
            return "value1";
        }
        return null;
    }


}
