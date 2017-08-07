package com.andresolarte.harness.lang.java8.topic9_1;

import com.andresolarte.harness.lang.java8.domain.Person;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;


public class JavaFromJSTest implements Runnable {
    public void run() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");

        try {
            engine.eval(new InputStreamReader(getClass().getResourceAsStream("/topic9_1.js")));
            Invocable invocable = (Invocable) engine;
            Person result = (Person) invocable.invokeFunction("testWithReturn");
            System.out.println("Result: " + result.formatName());

            result = (Person) invocable.invokeFunction("testWithBeanReturn");
            System.out.println("Bean and result and methods: " + result.formatName());

            List<Person> personList = (List<Person>) invocable.invokeFunction("testWithListReturn");
            personList.forEach(p -> System.out.println("Person from list: " + p.formatName()));

            Person[] personArray = (Person[]) invocable.invokeFunction("testWithTypedArrayReturn");
            Arrays.asList(personArray).forEach(p -> System.out.println("Person from array: " + p.formatName()));

            ScriptObjectMirror scriptObjectMirror = (ScriptObjectMirror) invocable.invokeFunction("testWithJSArrayReturn");
            Person person = (Person) scriptObjectMirror.get("0");
            System.out.println("Person from JS array: " + person.formatName());

        } catch (ScriptException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
