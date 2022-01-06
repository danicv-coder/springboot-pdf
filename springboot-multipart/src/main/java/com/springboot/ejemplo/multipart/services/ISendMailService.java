package com.springboot.ejemplo.multipart.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface ISendMailService {
    void sendMail(String from, String to, String subject, String body);

    void sendAttachmentFileMail(String sendTo, String title, String content, File file);

    File multipartConvertAFile(MultipartFile multipartFile) throws IOException;

}
