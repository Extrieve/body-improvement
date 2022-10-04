package com.body.improvement.club.service;

import com.body.improvement.club.entity.Attachment;
import com.body.improvement.club.model.ResponseData;
import com.body.improvement.club.repository.AttachmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class AttachmentService implements ServiceDelegator {

    @Autowired
    private AttachmentRepo attachmentRepo;

    public ResponseEntity<Attachment> saveAttachment(MultipartFile attachment){
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
        return ResponseEntity.ok(attachmentRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Attachment not found")
        ));
    }
}
