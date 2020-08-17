package com.shop.top.productservice.productservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class FileUploadServiceImpl implements FileUploadService {
///src/main/resources
    private static  String uploaddirectory =System.getProperty("user.dir")+"/Back-End/product-service/src/main/resources/image/";
    @Override
    public String saveImage(MultipartFile file) throws Exception {
       File directory=new File(uploaddirectory);

       if(!directory.exists()){
           directory.mkdir();
       }
       byte[]bytes= file.getBytes();
       Path path= Paths.get(uploaddirectory+file.getOriginalFilename());
       Files.write(path,bytes);



       return file.getOriginalFilename();
    }
}
