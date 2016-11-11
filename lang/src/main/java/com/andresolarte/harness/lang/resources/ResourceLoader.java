package com.andresolarte.harness.lang.resources;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class ResourceLoader {
    public static void main(String... args) throws URISyntaxException {
        URL url = String.class.getResource("/test.res");
        File file = new File(url.toURI());
        file = file.getParentFile().getParentFile().getParentFile();
        String path = file.getAbsolutePath();
       // path = path.substring(path.indexOf(":") + 1);

        System.out.println("Load resource: " + url.toString());
        System.out.println("Path: " + path);
    }

    //Load resource: file:/Users/aolarte/per/test-harness/lang/target/classes/test.res
    //Path: /Users/aolarte/per/test-harness/lang/target/classes/test.res
}
