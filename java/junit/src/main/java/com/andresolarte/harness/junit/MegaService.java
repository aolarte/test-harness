package com.andresolarte.harness.junit;

public class MegaService {
    Person clonePersonEntity(String name) {
        return null;
    }

    boolean persistEntity(Person person) {
        return true;
    }


    public void normalizeNames(String name) {
           Person person = clonePersonEntity(name);
           person.setFirstName(person.getFirstName().toUpperCase());
           person.setLastName(person.getLastName().toUpperCase());
           persistEntity(person);
    }
}
