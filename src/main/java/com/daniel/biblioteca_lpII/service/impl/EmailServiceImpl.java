package com.daniel.biblioteca_lpII.service.impl;

import com.daniel.biblioteca_lpII.service.IEmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.from}")
    private String emailUser;

    @Override
    public void sendEmail(String toUser, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage(); //el objeto que ayuda a construir el mensaje
        mailMessage.setFrom(emailUser); //quien envia el correo
        mailMessage.setTo(toUser); //a quien se va a hacer el envio
        mailMessage.setSubject(subject); //asunto
        mailMessage.setText(message); //cuerpo
        mailSender.send(mailMessage);
    }

    /*
    public void sendEmailWithFile(String[] toUsers, String subject, String message, MultipartFile file) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name()); //ponemos como true si vamos a mandar un archivo
            mimeMessageHelper.setFrom(emailUser); //quien envia el correo
            mimeMessageHelper.setTo(toUsers); //a quien se va a hacer el envio
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file.getName(), file);
            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     */
}
