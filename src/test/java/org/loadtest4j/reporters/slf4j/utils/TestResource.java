package org.loadtest4j.reporters.slf4j.utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class TestResource {
    private static String streamToString(InputStream is) {
        // From https://stackoverflow.com/a/5445161
        final Scanner s = new Scanner(is, StandardCharsets.UTF_8.name()).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String read(String name) {
        return streamToString(TestResource.class.getClassLoader().getResourceAsStream(name));
    }
}
