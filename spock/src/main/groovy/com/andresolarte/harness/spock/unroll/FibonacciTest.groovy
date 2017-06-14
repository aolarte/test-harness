package com.andresolarte.harness.spock.unroll

import spock.lang.Specification
import spock.lang.Unroll

class FibonacciTest extends Specification {
    @Unroll
    def "fib(#input) = #result"(int input, int result) {
        expect:
        new Fibonacci().fib(input) == result
        where:
        input | result
        0     | 0
        1     | 1
        2     | 1
        3     | 2
        4     | 3
    }
}
