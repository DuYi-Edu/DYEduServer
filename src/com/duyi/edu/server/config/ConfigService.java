package com.duyi.edu.server.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigService {

    private static Properties config = null;

    public static void init() throws IOException {
        config = new Properties();
        config.load(new FileInputStream("config.properties"));
    }

    public static String getConfig(String key) {
        return config.getProperty(key);
    }

    public static String getConfig(String key, String defValue) {
        String value = config.getProperty(key);
        return value == null ? defValue : value;
    }

}
