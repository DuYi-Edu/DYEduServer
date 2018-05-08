package com.duyi.edu.server.common.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ConfigService {

    private static Properties config = null;

    public static void init() throws IOException, InstantiationException, IllegalAccessException, ConfigInitException {
        config = new Properties();
        config.load(new FileInputStream("./conf/config.properties"));
        checkConfig();
    }

    public static String getConfig(String key) {
        return config.getProperty(key);
    }

    public static String getConfig(String key, String defValue) {
        String value = config.getProperty(key);
        return value == null ? defValue : value;
    }

    private static void checkConfig() throws IllegalAccessException, InstantiationException, ConfigInitException {
        Field[] fields = ConfigName.class.getDeclaredFields();
        Set<String> notExistConfig = new HashSet<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Must must = field.getAnnotation(Must.class);
            if (must == null) {
                continue;
            }
            if ("".equals(must.preConfig()) || getConfig(must.preConfig()) != null && getConfig(must.preConfig()).equals(must.preValue())) {
                String key = (String) field.get(ConfigName.class.newInstance());
                String temp = getConfig(key);
                if (temp == null) {
                    notExistConfig.add(key);
                }
            }
        }
        if (notExistConfig.size() > 0) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("can not find config: ");
            for (String temp : notExistConfig) {
                stringBuffer.append(temp);
                stringBuffer.append(" ");
            }
            throw new ConfigInitException(stringBuffer.toString());
        }
    }

}
