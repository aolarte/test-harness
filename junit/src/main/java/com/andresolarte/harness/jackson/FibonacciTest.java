package com.andresolarte.harness.jackson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FibonacciTest {

    public class Fibonacci{
        int fib(int input) {
            return 0;
        }
    }

    @Parameterized.Parameters(name = "{index}: fib({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { 0, 0 }, { 1, 1 }, { 2, 1 }, { 3, 2 }, { 4, 3 }
        });
    }

    @Parameterized.Parameter
    public int input;

    @Parameterized.Parameter(1)
    public  int result;

    @Test
    public void test() {
        assertEquals(result, new Fibonacci().fib(input));
    }
}