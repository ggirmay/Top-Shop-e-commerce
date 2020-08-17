package com.shop.top.productservice.productservice.controller;
import com.shop.top.productservice.productservice.model.Picture;
import com.shop.top.productservice.productservice.model.Product;
import com.shop.top.productservice.productservice.model.ProductList;
import com.shop.top.productservice.productservice.repository.ImageService;
import com.shop.top.productservice.productservice.repository.ProductRepository;
import com.shop.top.productservice.productservice.service.FileUploadService;
import com.shop.top.productservice.productservice.service.ProductService;
import com.shop.top.productservice.productservice.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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

    @Autowired
    private PromotionService promotionService;

    @Autowired
    ImageService imageService;
    @Autowired
    ProductService productService;

    @CrossOrigin
    @GetMapping("/getAll")
    public  Iterable<Product> getProductss() {
        return productService.getAllProducts();
    }
    @CrossOrigin
@PostMapping("/save")
    public Product addProduct(@RequestBody Product product){
        System.out.println("Hello");
    return productService.save(product);
}
    @CrossOrigin
    @GetMapping("/{id}")
    Product getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
    @CrossOrigin
    @PutMapping("/{id}")
   ResponseEntity<Product>  editProduct(@RequestBody Product newProduct, @PathVariable Long id) {

       Product p= productService.getProduct(id);
        p.setName(newProduct.getName());
        p.setCode(newProduct.getCode());
        p.setType(newProduct.getType());
        p.setPrice(newProduct.getPrice());
        p.setCategory(newProduct.getCategory());
        p.setProductDetail(newProduct.getProductDetail());
        p.setPicture_url(newProduct.getPicture_url());
        final Product updatedProduct = productService.save(p);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    //======================================================
    @CrossOrigin
    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uplaodImage(@RequestPart("image") MultipartFile file, @RequestPart(value = "product") Product product ) throws IOException {
        System.out.println(product);
        System.out.println("this is file "+file  + " " + file.getOriginalFilename()+"."+file.getContentType());
        System.out.println(product.toString());
        if(!file.isEmpty()){
            try {
                String imagePath=fileUploadService.saveImage(file);
                product.setPicture_url(imagePath);
                String imageNmae = file.getOriginalFilename();

                String url ="http://localhost:8083/product/getImage?image_id=";
                Picture p = new Picture();
                p.setBig(url+imageNmae);
                p.setSmall(url+imageNmae);

                ArrayList<Picture> pictures= new ArrayList<Picture>();

                pictures.add(p);
                product.setPictures(pictures);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        productRepository.save(product);
        return " Product created";
    }

    @CrossOrigin
    @GetMapping("/promoted-products")
    public List<Product> promotedProduct(){
        return this.promotionService.getAllPromotedProducts();
    }

    @CrossOrigin
    @PutMapping("/updateQuantity/{id}/{quantity}")
public Product updateQuantity(@PathVariable Long id, @PathVariable int quantity){
        Product product=productService.getProduct(id);
        product.setQuantity(product.getQuantity()-quantity);
       return productService.save(product);
}

    @CrossOrigin
    @GetMapping("/searchByPage")

    public Page<Product> findAll(@RequestParam  Optional<String> productName, @RequestParam Optional<Integer> page,
                                 @RequestParam Optional<String> sortby){
        System.out.println("I am in search");
      return  productRepository.findProdcutByName(productName.orElse(" "), PageRequest.of(page.orElse(0),
              5, Sort.by(Sort.Direction.ASC,sortby.orElse("id"))));
    }

    @CrossOrigin
    @RequestMapping(value = "/getImage", method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public  synchronized ResponseEntity<byte[]> getImage(@RequestParam String image_id) throws Exception {


        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageService.getImage(image_id));
    }

    //Janvier Reporting Module
    @CrossOrigin
    @GetMapping("/getAllforReporting")
    public ProductList getProducts() {
        ProductList productList=new ProductList();
        productList.setProductList(productService.getAllProducts());
        return productList ;
    }
    @CrossOrigin
    @GetMapping("/pending")
    public ResponseEntity<List<Product>> pending() {
        return ResponseEntity.ok().body(productService.getPendingProducts());
    }

    @CrossOrigin
    @GetMapping("/remove/{id}")
    public void removeProduct(Long id) {
         productService.removeProduct(id);
    }
    @CrossOrigin
    @GetMapping("/approve/{id}")
    public ResponseEntity pending(@PathVariable Long id) {
        productService.aproveProduct(id);

        return ResponseEntity.ok().body(new PlainText("Approved"));

    }

    @CrossOrigin
    @GetMapping("/reject/{id}")
    public ResponseEntity reject(@PathVariable Long id) {
        System.out.println("I am here");
        productService.deAproveProduct(id);
        return ResponseEntity.ok().body(new PlainText("Depproved"));
    }
    @CrossOrigin
    @GetMapping("/approved")
    public List<Product> approvedProducts() {

       return  productService.getApprovedProducts();

    }
    @CrossOrigin
    @GetMapping("/search")
    public ResponseEntity<Object> getAllProducts(@RequestParam String keyword) {
        List<Product> products = productService.search( "%"+ keyword + "%");
        return new ResponseEntity<Object>(products, HttpStatus.OK);
    }
}

class  PlainText{
    private String message;

    public PlainText(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
