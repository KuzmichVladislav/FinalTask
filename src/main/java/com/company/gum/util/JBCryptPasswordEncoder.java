package com.company.gum.util;

import org.mindrot.jbcrypt.BCrypt;
import org.pac4j.core.util.CommonHelper;

public class JBCryptPasswordEncoder {

    private static final String SALT = "$2a$10$r/7vpCEEmIc0Kwek2SQu0.";

    public static String encode(final String password) {
        CommonHelper.assertNotBlank("salt", SALT);
        return BCrypt.hashpw(password, SALT);
    }

    public boolean matches(final String plainPassword, final String encodedPassword) {
        CommonHelper.assertNotBlank("salt", SALT);

        return BCrypt.checkpw(plainPassword, encodedPassword);
    }
}
