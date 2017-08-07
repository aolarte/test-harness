package com.andresolarte.harness.spock.spy

import com.andresolarte.harness.spock.service.TestService
import spock.lang.Specification

class SpySpockTest extends Specification{

    def "Basic Spy"() {
        given:
        TestService testService = Spy(TestService)
        when:
        int result = testService.sum(1, 2)
        then:
        result == 3
    }

    def "Spy with stubbing"() {
        given:
        TestService testService = Spy(TestService)
        testService.sum(1, 2) >> 4
        when:
        int result = testService.sum(1, 2)
        then:
        result == 4
    }

    def "Spy with partial stubbing"() {
        given:
        TestService testService = Spy(TestService)
        testService.sum(1, 2) >> 4
        when:
        int result = testService.sumAndSubtract(1, 2, 1)
        then:
        result == 2
    }
}
