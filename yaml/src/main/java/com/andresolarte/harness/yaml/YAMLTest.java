package com.andresolarte.harness.yaml;


import com.andresolarte.harness.yaml.objects.Simple;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YAMLTest {
    public static void main(String... args) {
        new YAMLTest().loadSimple();
    }

    public void loadSimple() {
        Yaml yaml = new Yaml(new Constructor(Simple.class));
        Simple o=yaml.loadAs(this.getClass().getClassLoader().getResourceAsStream("simple.yml"),Simple.class);

        System.out.println(o);
    }

}
