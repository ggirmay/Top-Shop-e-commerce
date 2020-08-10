package com.shop.top.productservice.productservice.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

@Component
public class ImageService {
  public synchronized byte[]  getImage(String image) throws Exception{
      ClassPathResource imgFile = new ClassPathResource("image"+"/"+image.toString());
      byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
      return bytes;
    }
}
