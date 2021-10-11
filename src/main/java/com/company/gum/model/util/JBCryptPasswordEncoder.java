package com.company.gum.model.util;

import org.mindrot.jbcrypt.BCrypt;
import org.pac4j.core.util.CommonHelper;

/**
 * The Class JBCryptPasswordEncoder.
 *
 * @author Vladislav Kuzmich
 */
public class JBCryptPasswordEncoder {

	/**
	 * The Constant SALT.
	 */
	private static final String SALT = "$2a$10$r/7vpCEEmIc0Kwek2SQu0.";

	/**
	 * Instantiates a new JB crypt password encoder.
	 */
	private JBCryptPasswordEncoder() {
	}

	/**
	 * Encode.
	 *
	 * @param password the password
	 * @return the string
	 */
	public static String encode(final String password) {
		CommonHelper.assertNotBlank("salt", SALT);
		return BCrypt.hashpw(password, SALT);
	}
}
