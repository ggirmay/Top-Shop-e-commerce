package com.shop.top.productservice.productservice.repository;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;

@Component
public class ImageService {
  public synchronized byte[]  getImage(String image) throws Exception{
      ClassPathResource imgFile = new ClassPathResource("image"+"/"+image.toString());
      byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
      return bytes;

      /*File file = new File(new ClassPathResource("image"+"/"+image.toString()).getPath());
      System.out.println(file.getPath());
      byte[] bytes = IOUtils.toByteArray(new FileInputStream(file));

      if(bytes.length == 0) {
          System.out.println("image not found");
      }

      return bytes;*/
    }
}
