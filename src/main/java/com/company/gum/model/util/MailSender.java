package com.company.gum.model.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * The Class MailSender.
 *
 * @author Vladislav Kuzmich
 */
public class MailSender {

    /**
     * The Constant logger.
     */
    private static final Logger logger = LogManager.getLogger();

    /**
     * The Constant PROPERTY_PATH.
     */
    private static final String PROPERTY_PATH = "mail/mail.properties";

    /**
     * The Constant SUBJECT_MAIL.
     */
    private static final String SUBJECT_MAIL = "Verification";

    /**
     * The Constant properties.
     */
    private static final Properties properties = PropertyLoader.loadProperty(PROPERTY_PATH);

    /**
     * The message.
     */
    private MimeMessage message;

    /**
     * Send.
     *
     * @param userMail    the user mail
     * @param messageText the message text
     */
    public void send(String userMail, String messageText) {
        try {
            initMessage(userMail, messageText);
            Transport.send(message);
        } catch (AddressException e) {
            logger.warn("Invalid address: {} {}", userMail, e);
        } catch (MessagingException e) {
            logger.warn("Error generating or sending message: ", e);
        }
    }

    /**
     * Inits the message.
     *
     * @param userMail    the user mail
     * @param messageText the message text
     * @throws MessagingException the messaging exception
     */
    private void initMessage(String userMail, String messageText) throws MessagingException {
        Session mailSession = createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(SUBJECT_MAIL);
        message.setContent(messageText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
    }

    /**
     * Creates the session.
     *
     * @param configProperties the config properties
     * @return the session
     */
    public Session createSession(Properties configProperties) {
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
