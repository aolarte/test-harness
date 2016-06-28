package com.andresolarte.harness.guava;

import com.google.common.base.Optional;

import java.math.BigDecimal;

public class GuavaOptionals {

    public static void main(String... args) {
        BigDecimal bigDecimal=new BigDecimal("1000");
        Double result=Optional.of(bigDecimal).transform(BigDecimal::doubleValue).orNull();
        System.out.println("Result: "+ result);

        bigDecimal=null;
        result=Optional.fromNullable(bigDecimal).transform(BigDecimal::doubleValue).orNull();
        System.out.println("Result (from null): "+ result);
    }
}
