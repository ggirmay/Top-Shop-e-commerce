package com.shop.top.productservice.productservice.controller;

import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.repository.ProductRepository;
import com.shop.top.productservice.productservice.service.FileUploadService;
import com.shop.top.productservice.productservice.service.ProductService;
import com.shop.top.productservice.productservice.service.ProductServiceImpl;
import com.shop.top.productservice.productservice.service.PromotionService;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    private  ProductRepository productRepository;
    private final ProductService productService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    public ProductController( ProductService productService){
this.productService=productService;
    }
    @GetMapping("/getAll")
    public @NotNull Iterable<Product> getProducts() {
        return productService.getAllProducts();
    }
@PostMapping("/save")
    public Product addProduct(@RequestBody Product product){
        System.out.println("Hello");
    return productService.save(product);
}
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
    @PutMapping("/{id}")
   ResponseEntity<Product>  editProduct(@RequestBody Product newProduct, @PathVariable Long id) {

       Product p= productService.getProduct(id);
        p.setName(newProduct.getName());
        p.setCode(newProduct.getCode());
        p.setType(newProduct.getType());
        p.setPrice(newProduct.getPrice());
        p.setCategory(newProduct.getCategory());
        p.setProductDetailList(newProduct.getProductDetailList());
        p.setPicture_url(newProduct.getPicture_url());
        final Product updatedProduct = productService.save(p);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    //======================================================
    @PostMapping("/upload")
    public ResponseEntity.BodyBuilder uplaodImage(@RequestPart("image") MultipartFile file , @RequestBody Product product) throws IOException {
System.out.println("hellooooo");
        System.out.println("this is file "+file);
        if(!file.isEmpty()){
            try {
                System.out.println(" another hellooooo");
                String imagePath=fileUploadService.saveImage(file);
               // product.setPicture_url(imagePath);
            }
            catch (Exception e){
                System.out.println("hellooooo inside catch");
                ((NullPointerException) e).printStackTrace();
            }
        }


       // productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK);
    }

//    @GetMapping(path = { "/get/{imageName}" })
//    public Product getImage(@PathVariable("imageName") String imageName) throws IOException {
//         Product retrievedImage = productRepository.findByName(imageName);
//
//        Product img = new Product(retrievedImage.getName(), retrievedImage.getType(),
//
//                decompressBytes(retrievedImage.getPicByte()));
//        return img;
//
//    }

    @GetMapping("/promoted-products")
    public List<Product> promotedProduct(){
        return this.promotionService.getAllPromotedProducts();
    }

    @PutMapping("/updateQuantity/{id}/{quantity}")
public Product updateQuantity(@PathVariable Long id, @PathVariable int quantity){
        Product product=productService.getProduct(id);
        product.setQuantity(product.getQuantity()-quantity);
       return productService.save(product);
}
    public static byte[] compressBytes(byte[] data) {

        Deflater deflater = new Deflater();

        deflater.setInput(data);

        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {

            int count = deflater.deflate(buffer);

            outputStream.write(buffer, 0, count);

        }

        try {

            outputStream.close();

        } catch (IOException e) {

        }

        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();

    }

        // uncompress the image bytes before returning it to the angular application

    public static byte[] decompressBytes(byte[] data) {

        Inflater inflater = new Inflater();

        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);

        byte[] buffer = new byte[1024];

        try {

            while (!inflater.finished()) {

                int count = inflater.inflate(buffer);

                outputStream.write(buffer, 0, count);

            }

            outputStream.close();

        } catch (IOException ioe) {

        } catch (DataFormatException e) {

        }

        return outputStream.toByteArray();

    }
}
