package com.company.gum.util;

import com.company.gum.pool.ConnectionPool;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyLoader {

	private static final Logger logger = LogManager.getLogger();

	private PropertyLoader() {
	}

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
