package com.andresolarte.harness.lang.reflect;

import com.andresolarte.harness.lang.reflect.objs.TestStatic;
import com.andresolarte.harness.lang.reflect.objs.TestStaticFinal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class ReflectionTest {
    public static void main(String... args) {
        try {
            System.out.println("Change a private static field");
            setPrivateField();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Change a private static final field");
            setPrivateFinalField();
            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setPrivateField() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(TestStatic.getData());
        //// Let's change a private static field
        Field field = TestStatic.class.getDeclaredField("data");
        field.setAccessible(true);
        field.set(null, "v2");
        System.out.println(TestStatic.getData());
    }

    private static void setPrivateFinalField() throws NoSuchFieldException, IllegalAccessException {
        System.out.println(TestStaticFinal.getData());
        //// Let's change a private static field
        Field field = TestStaticFinal.class.getDeclaredField("data");

        //// Remove final modifier
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

        field.setAccessible(true);
        field.set(null, "f2");
        System.out.println(TestStaticFinal.getData());
    }
}
