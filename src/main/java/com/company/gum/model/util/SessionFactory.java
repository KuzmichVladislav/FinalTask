package com.company.gum.model.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Session objects.
 */
public class SessionFactory {

    /**
     * Instantiates a new session factory.
     */
    private SessionFactory() {
    }

    /**
     * Creates a new Session object.
     *
     * @param configProperties the config properties
     * @return the session
     */
    public static Session createSession(Properties configProperties) {
        String userName = configProperties.getProperty("mail.user.name");
        String userPassword = configProperties.getProperty("mail.user.password");
        return Session.getDefaultInstance(configProperties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, userPassword);
            }
        });
    }
}
