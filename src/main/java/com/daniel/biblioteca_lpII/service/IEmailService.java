package com.daniel.biblioteca_lpII.service;

import org.springframework.web.multipart.MultipartFile;


public interface IEmailService {

    void sendEmail(String toUser,String subject, String message); //destinatario - asunto - mensaje

}
