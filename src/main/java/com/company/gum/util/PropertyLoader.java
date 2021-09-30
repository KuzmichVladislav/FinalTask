package com.company.gum.util;

import com.company.gum.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class PropertyLoader.
 */
public class PropertyLoader {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * Instantiates a new property loader.
     */
    private PropertyLoader() {
    }

    /**
     * Load property.
     *
     * @param path the path
     * @return the properties
     */
    public static Properties loadProperty(String path) {
        Properties properties = new Properties();

        try {
            properties.load(ConnectionPool.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException e) {
            logger.error("Unable to load resources", e);
        }
        return properties;
    }
}
