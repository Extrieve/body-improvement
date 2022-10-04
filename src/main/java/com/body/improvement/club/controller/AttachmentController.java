package com.body.improvement.club.controller;

import com.body.improvement.club.entity.Attachment;
import com.body.improvement.club.model.ResponseData;
import com.body.improvement.club.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Objects;

@RestController("/attachment")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;


    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURl = "";
        attachment = attachmentService.saveAttachment(file).getBody();
        downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(Objects.requireNonNull(attachment).getId())
                .toUriString();

        return new ResponseData(attachment.getFileName(),
                downloadURl,
                file.getContentType(),
                file.getSize());
    }


    @GetMapping("/download/{id}")
    public ResponseEntity<Attachment> downloadFile(@PathVariable String id){
        return attachmentService.getAttachmentById(id);
    }
}
