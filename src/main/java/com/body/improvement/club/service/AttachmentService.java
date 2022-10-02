package com.body.improvement.club.service;

import com.body.improvement.club.entity.Attachment;
import com.body.improvement.club.repository.AttachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentService implements ServiceDelegator {

    @Autowired
    private AttachmentRepo attachmentRepo;

    public void saveAttachment(MultipartFile attachment){
        String fileName = attachment.getOriginalFilename();
        String contentType = attachment.getContentType();
        byte[] content = new byte[0];
        try {
            content = attachment.getBytes();
            Attachment attachmentEntity = new Attachment(fileName, contentType, content);
            attachmentRepo.save(attachmentEntity);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
