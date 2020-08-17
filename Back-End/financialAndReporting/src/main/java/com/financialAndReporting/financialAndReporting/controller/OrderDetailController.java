package com.financialAndReporting.financialAndReporting.controller;

import com.financialAndReporting.financialAndReporting.dto.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/orderDetail")
@CrossOrigin
public class OrderDetailController {
    @Autowired
    RestTemplate restTemplate;

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/testShoppingCart")
    public ResponseEntity<OrderDetailList> getAllOrderDetails(){
        OrderDetailList orderDetailList = restTemplate.getForObject("http://localhost:8087/orderdetail/getOrderDetail", OrderDetailList.class);
        return ResponseEntity.ok().body(orderDetailList);
    }
    private  OrderDetailList getFromOrderDetailsList(){
        OrderDetailList orderDetailList = restTemplate.getForObject("http://localhost:8087/orderdetail/getOrderDetail", OrderDetailList.class);
        return orderDetailList;
    }

    @GetMapping(value = "/all")
    public void getOrderDetailReport(HttpServletResponse response ){
        log.info("Preparing the pdf report via jasper.");
        List<OrderDetail> orderDetailList = getFromOrderDetailsList().getOrderDetails();
        Map parameters = new HashMap();
        parameters.put("createdBy", "Top Shop");
        String pathname = "S:\\MUM Classes Code & Projects\\PM\\front end\\Top-Shop-e-commerce\\Back-End\\financialAndReporting\\src\\main\\resources\\Sales.jrxml";
        try {
            response = createPdfReport(response,orderDetailList,parameters,pathname);
            log.info("File successfully Created");
        } catch (final Exception e) {
            log.info("Some error occurs while preparing to create the product Report");
            e.printStackTrace();
        }
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","Inline;filename=jasper.pdf;");

    }

    public HttpServletResponse createPdfReport(HttpServletResponse response, List<OrderDetail> orderDetailList, Map parameters, String pathname) throws IOException, JRException {

        final InputStream stream = new FileInputStream(new File(pathname));
        JasperDesign jasperDesign = JRXmlLoader.load(stream);
        final JasperReport report = JasperCompileManager.compileReport(jasperDesign);
        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(orderDetailList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,beanCollectionDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return response;
    }


}
