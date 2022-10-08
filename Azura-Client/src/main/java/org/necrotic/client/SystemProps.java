package org.necrotic.client;

import java.io.File;

public class SystemProps {

    public static final String separator = "/";

    public static String combinePath(String... parts) {
        return String.join(separator, parts);
    }
}