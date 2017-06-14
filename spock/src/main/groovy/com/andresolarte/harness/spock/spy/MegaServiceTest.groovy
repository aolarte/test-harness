package com.andresolarte.harness.spock.spy

import com.andresolarte.harness.spock.service.TestService
import spock.lang.Specification

class MegaServiceTest extends Specification{
    def "test normalizeNames"() {
        given:
        Person person = new Person("john", "smith")
        MegaService sut = Spy(MegaService) {
            clonePersonEntity("john smith") >> person
        }
        when:
        sut.normalizeNames("john smith")
        then:
        1 * sut.persistEntity(person)
        person.getFirstName() == "JOHN"
        person.getLastName() == "SMITH"
    }
}
