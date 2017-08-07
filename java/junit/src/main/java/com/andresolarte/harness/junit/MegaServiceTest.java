package com.andresolarte.harness.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MegaServiceTest {
    MegaService sut = spy(new MegaService());
    @Test
    public void testNormalizeName() {
        Person person = new Person("john", "smith");
        doReturn(person).when(sut).clonePersonEntity("john smith");
        sut.normalizeNames("john smith"); //Execute our logic
        verify(sut).persistEntity(person);
        assertEquals(person.getFirstName(), "JOHN");
        assertEquals(person.getLastName(), "SMITH");
    }
}
