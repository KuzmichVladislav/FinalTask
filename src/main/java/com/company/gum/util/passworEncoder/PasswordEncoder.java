package com.company.gum.util.passworEncoder;

public interface PasswordEncoder {

    /**
     * Encode a password.
     *
     * @param password the not encoded password
     * @return the encoded password
     */
    static String encode(String password);

    /**
     * Validate if a plainPassword matches
     *
     * @param plainPassword   the not encoded password to check
     * @param encodedPassword the encoded password
     * @return <code>true</code> if they match
     */
    boolean matches(String plainPassword, String encodedPassword);
}
