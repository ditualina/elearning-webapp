package com.alinadev.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUpload {

    private List<MultipartFile> multipartFiles;
 
    public List<MultipartFile> getFiles() {
        return multipartFiles;
    }
 
    public void setFiles(List<MultipartFile> files) {
        this.multipartFiles = files;
    }
}
