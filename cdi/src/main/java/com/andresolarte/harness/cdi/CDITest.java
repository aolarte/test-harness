package com.andresolarte.harness.cdi;



import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;



public class CDITest {

    public static void main(String... args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        App app = container.instance().select(App.class).get();
        app.run();

    }


}
