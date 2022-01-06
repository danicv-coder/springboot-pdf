package com.springboot.ejemplo.multipart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class SendMailServiceImpl implements ISendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private String sender = "daniel.calderon.e.v@gmail.com";


    public void sendMail(String from, String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);

        javaMailSender.send(mailMessage);
    }

    @Override
    public void sendAttachmentFileMail(String sendTo, String title, String content, File file) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMailMessage, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(sendTo);
            messageHelper.setSubject(title);
            messageHelper.setText(content);

            FileSystemResource fileSystemResource = new FileSystemResource(file);

            String fileName = file.getName();
            System.out.println(fileName);
            messageHelper.addAttachment(fileName, fileSystemResource);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mimeMailMessage);
    }

    public File multipartConvertAFile(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return file;
    }

}
