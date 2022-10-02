package com.body.improvement.club.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@AllArgsConstructor@NoArgsConstructor
public class ResponseData {

    private String fileName;
    private String downloadUrl;
    private String fileType;
    private long fileSize;
}
