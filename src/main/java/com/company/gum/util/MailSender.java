package com.company.gum.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {

    private static final Logger logger = LogManager.getLogger();

    private static final String PROPERTY_PATH = "mail/mail.properties";
    private static final String SUBJECT_MAIL = "Verification";
    private static final Properties properties = PropertyLoader.loadProperty(PROPERTY_PATH);

    private MimeMessage message;

    public void send(int userId, String userMail, String messageText) {
        try {
            initMessage(userId, userMail, messageText);
            Transport.send(message);
        } catch (AddressException e) {
            logger.warn("Invalid address: {} {}", userMail, e);
        } catch (MessagingException e) {
            logger.warn("Error generating or sending message: ", e);
        }
    }

    private void initMessage(int userId, String userMail, String messageText) throws MessagingException { // TODO: 9/24/2021 Добавить разлличные сообщения в зависимости от того кто регистрируется
        Session mailSession = SessionFactory.createSession(properties);
        mailSession.setDebug(true);
        message = new MimeMessage(mailSession);
        message.setSubject(SUBJECT_MAIL);
        message.setContent(messageText, "text/html");
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
    }
}
