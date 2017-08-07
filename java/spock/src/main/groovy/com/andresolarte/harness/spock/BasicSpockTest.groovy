package com.andresolarte.harness.spock

import spock.lang.Specification

class BasicSpockTest extends Specification{
    def "basic mock"() {
        when:
        int x = 5
        then:
        x ==5
    }
}
