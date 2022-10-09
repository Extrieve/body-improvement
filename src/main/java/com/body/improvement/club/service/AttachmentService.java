package com.body.improvement.club.service;

import com.body.improvement.club.entity.Attachment;
import com.body.improvement.club.repository.AttachmentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentService implements ServiceDelegator {

    @Autowired
    private AttachmentRepo attachmentRepo;

    private final Logger logger = LogManager.getLogger(AttachmentService.class);

    public ResponseEntity<Attachment> saveAttachment(MultipartFile attachment){
        logger.info("Saving attachment: " + attachment.getOriginalFilename());
        try {

            Attachment attachmentEntity = new Attachment(
                    attachment.getOriginalFilename(),
                    attachment.getContentType(),
                    attachment.getBytes()
            );

            return ResponseEntity.ok(attachmentRepo.save(attachmentEntity));

        } catch (IOException e) {

            throw new RuntimeException(e);

        }
    }

    public ResponseEntity<Attachment> getAttachmentById(String id){
        logger.info("Fetching attachment by id: " + id);
        return ResponseEntity.ok(attachmentRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Attachment not found")
        ));
    }
}
