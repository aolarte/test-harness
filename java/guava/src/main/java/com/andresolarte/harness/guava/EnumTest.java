package com.andresolarte.harness.guava;

import com.google.common.base.Enums;

public class EnumTest {
    enum Sort {
        ASCENDING, DESCENDING
    }


    public static void main(String... args) {
        Sort sort = Enums.getIfPresent(Sort.class, "ASCENDING").get();
        System.out.println("1 => " + sort);

        sort = Enums.getIfPresent(Sort.class, "").or(Sort.DESCENDING);
        System.out.println("2 => " + sort);

        sort = Enums.getIfPresent(Sort.class, "").or(() -> {throw new IllegalArgumentException("Bad argument");});
        System.out.println("3 => " + sort); //Won't reach this part!!
    }
}
