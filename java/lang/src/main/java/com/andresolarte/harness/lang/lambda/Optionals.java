package com.andresolarte.harness.lang.lambda;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

public class Optionals {

    public static void main(String... args) {

        BigDecimal bigDecimal=new BigDecimal("1000");
        Double result=Optional.of(bigDecimal).map(BigDecimal::doubleValue).orElse(null);
        System.out.println("Result: "+ result);

        bigDecimal=null;
        result=Optional.ofNullable(bigDecimal).map(BigDecimal::doubleValue).orElse(null);
        System.out.println("Result (from null): "+ result);


        //Optional from an array
        String[] array1=new String[]{"Test1"};
        Optional<String> optional1= Arrays.asList(array1).stream().findFirst();
        System.out.println("Optional 1 present: " + optional1.isPresent());

        Optional<String> optional2= Arrays.asList(new String[0]).stream().findFirst();
        System.out.println("Optional 2 present: " + optional2.isPresent());
    }
}
