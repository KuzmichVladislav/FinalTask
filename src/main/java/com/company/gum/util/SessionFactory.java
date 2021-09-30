package com.company.gum.util;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

public class SessionFactory {

    private SessionFactory() {
    }

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
