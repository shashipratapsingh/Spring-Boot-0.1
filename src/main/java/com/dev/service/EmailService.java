package com.dev.service;

import com.dev.model.Emails;
import com.dev.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;


    public Emails saveEmails(String to1, String subject1, String body1,Emails emails) throws Exception {
        Emails emails1=sendEmail(to1, subject1, body1,emails);
        return this.emailRepository.save(emails1);
 }
    public Emails sendEmail(String to1, String subject1, String body1, Emails emails) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to1);
        mailMessage.setSubject(subject1);
        mailMessage.setText(body1);
        javaMailSender.send(mailMessage);
        return emails;
    }
}