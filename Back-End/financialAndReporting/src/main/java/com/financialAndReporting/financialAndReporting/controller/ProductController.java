package com.financialAndReporting.financialAndReporting.controller;


import com.financialAndReporting.financialAndReporting.dto.Product;
import com.financialAndReporting.financialAndReporting.dto.ProductList;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    @Autowired
    RestTemplate restTemplate;
    final Logger log = LoggerFactory.getLogger(this.getClass());
    final ModelAndView model = new ModelAndView();


    // Method to display the index page of the application.
    @GetMapping(value= "/welcome")
    public ModelAndView index() {
        log.info("Showing the welcome page.");
        model.setViewName("welcome");
        return model;
    }

    @GetMapping(value = "/report")
    public void getProductReport(HttpServletResponse response ){
        log.info("Preparing the pdf report via jasper.");
        List<Product> productLists = getFromProductList().getProductList();
        // Adding the additional parameters to the pdf.
        Map parameters = new HashMap();
   parameters.put("createdBy", "Top Shop");
//        parameters.put("ProductName",productName);
        String pathname = "E:\\MUM\\PM\\Top-Shop-e-commerce\\Back-End\\financialAndReporting\\src\\main\\resources\\product_report.jrxml";
        try {
            response = createPdfReport(response,productLists,parameters,pathname);
            log.info("File successfully Created");
        } catch (final Exception e) {
            log.info("Some error occurs while preparing to create the product Report");
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","Inline;filename=jasper.pdf;");

    }




    // Method to create the pdf file using the product list datasource.
    public HttpServletResponse createPdfReport(HttpServletResponse response, List<Product> productList, Map parameters, String pathname) throws IOException, JRException {

final InputStream stream = new FileInputStream(new File(pathname));
        // Compile the Jasper report from .jrxml to .japser
//        ???//?final JasperReport report = JasperCompileManager.compileReport(stream);
        JasperDesign jasperDesign = JRXmlLoader.load(stream);
        // Fetching the products from the data source.
//        compare the JesperReport from Jrxml to .jasper
        final JasperReport report = JasperCompileManager.compileReport(jasperDesign);
//        Create datasource from bean list
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(productList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,beanCollectionDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return response;
    }

    @GetMapping(value = "/testProduct")
    public ResponseEntity<ProductList> test(){
        ProductList productList = restTemplate.getForObject("http://localhost:8083/product/getAll", ProductList.class);
        return ResponseEntity.ok().body(productList);
    }

    private  ProductList getFromProductList(){
        ProductList productList = restTemplate.getForObject("http://localhost:8083/product/getAll", ProductList.class);
        return productList;
    }
}
