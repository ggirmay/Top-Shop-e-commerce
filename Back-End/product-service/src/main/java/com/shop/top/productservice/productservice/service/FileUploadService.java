package com.shop.top.productservice.productservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String saveImage(MultipartFile file) throws  Exception;
}
