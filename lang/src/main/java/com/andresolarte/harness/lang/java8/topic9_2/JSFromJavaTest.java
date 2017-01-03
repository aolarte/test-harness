package com.andresolarte.harness.lang.java8.topic9_2;

import com.andresolarte.harness.lang.java8.domain.Gender;
import com.andresolarte.harness.lang.java8.domain.Person;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStreamReader;

/**
 * Created by andres on 4/26/15.
 */
public class JSFromJavaTest implements Runnable {
    public void run() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");

        try {
            engine.eval(new InputStreamReader(getClass().getResourceAsStream("/topic9_2.js")));
            Invocable invocable = (Invocable) engine;
            ScriptObjectMirror personBuilder = (ScriptObjectMirror) invocable.invokeFunction("personBuilder");
            Person person = (Person) personBuilder.callMember("build");
            System.out.println("Return value from JS: " + person.formatName());

            Person templatePerson = new Person.Builder()
                    .givenName("Brenda")
                    .surName("Harrell")
                    .age(25)
                    .gender(Gender.FEMALE)
                    .email("BrendaCHarrell@example.com")
                    .phoneNumber("123-123-4678")
                    .address("1922 Rubaiyat Road, Smallville, KS 12333")
                    .build();
            person = (Person) personBuilder.callMember("buildWithParameters", templatePerson);
            System.out.println("Return value from JS using a parameter: " + person.formatName());

            ScriptObjectMirror jsPerson = (ScriptObjectMirror) invocable.invokeFunction("createJavaScriptPersonObject");
            System.out.println("JS Person name: " + jsPerson.get("firstName") + " " + jsPerson.get("lastName"));
            ScriptObjectMirror jsAddress = (ScriptObjectMirror) jsPerson.get("address");
            System.out.println("JS Person address: " + jsAddress.get("street1") + " " + jsAddress.get("street2") + " " +
                    jsAddress.get("city") + ", " + jsAddress.get("state") + " " + jsAddress.get("zip"));
        } catch (ScriptException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
