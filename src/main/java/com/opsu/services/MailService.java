package com.opsu.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;


@Service
public class MailService {
    private  static Logger LOG = Logger.getLogger(MailService.class.getName());
    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text) throws MailException {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("serviceskyclean@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException e) {
            LOG.error("MailService method sendMessage: " + e.getMessage(), e);
            throw e;
        }
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String filename, ByteArrayInputStream stream)
            throws MessagingException, IOException{
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);

            //construct the text body part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(text);

            //construct the pdf body part
            DataSource dataSource = new ByteArrayDataSource(stream, "application/pdf");
            MimeBodyPart attachment = new MimeBodyPart();
            attachment.setDataHandler(new DataHandler(dataSource));
            attachment.setFileName(filename);

            //construct the mime multi part
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textPart);
            mimeMultipart.addBodyPart(attachment);

            message.setContent(mimeMultipart);
            emailSender.send(message);
        } catch (MessagingException | IOException e) {
            LOG.error("MailService method sendMessageWithAttachment: " + e.getMessage(), e);
            throw e;
        }
    }
}
