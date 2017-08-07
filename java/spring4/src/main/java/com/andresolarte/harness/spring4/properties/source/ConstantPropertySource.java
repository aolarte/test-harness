package com.andresolarte.harness.spring4.properties.source;

import org.springframework.core.env.PropertySource;


public class ConstantPropertySource extends PropertySource<Object> {

    public ConstantPropertySource() {
        super("constant");
    }

    @Override
    public Object getProperty(String s) {
        if ("key1".equalsIgnoreCase(s)) {
            return "value1";
        }
        return null;
    }


}
