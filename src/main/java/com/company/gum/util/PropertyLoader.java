package com.company.gum.util;

import com.company.gum.pool.ConnectionPool;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    private PropertyLoader() {
    }

    public static Properties loadProperty(String path) {
        Properties properties = new Properties();

        try {
            properties.load(ConnectionPool.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
