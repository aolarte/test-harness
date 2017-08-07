package com.andresolarte.harness.junit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class SimpleTest {

    List<String> list = new ArrayList<>();

    @Before
    public void setUp() {
        System.out.println("Before");
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Before Class");
    }

    @Test
    public void test1() {
        list.add("test1");
        System.out.println(list.size());
    }


    @Test
    public void test2() {
        list.add("test2");
        System.out.println(list.size());
    }
}