package com.company.gum.util;

import org.mindrot.jbcrypt.BCrypt;
import org.pac4j.core.util.CommonHelper;

public class JBCryptPasswordEncoder {

    private static String salt = "$2a$10$r/7vpCEEmIc0Kwek2SQu0.";

    public JBCryptPasswordEncoder(final String salt) {
        this.salt = salt;
    }

    public static String encode(final String password) {
        CommonHelper.assertNotBlank("salt", salt);
        return BCrypt.hashpw(password, salt);
    }

    public boolean matches(final String plainPassword, final String encodedPassword) {
        CommonHelper.assertNotBlank("salt", salt);

        return BCrypt.checkpw(plainPassword, encodedPassword);
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}