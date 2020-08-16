package com.top.shop.user.command.service;

import com.top.shop.user.command.action.VendorCommandAction;
import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.Vendor;
import com.top.shop.user.exception.UserDoesntExit;
import com.top.shop.user.exception.UserExist;
import com.top.shop.user.query.action.VendorQueryAction;
import com.top.shop.user.query.service.EmployeeQueryService;
import com.top.shop.user.query.service.UserAccountQueryService;
import com.top.shop.user.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
public class VendorCommandService {
    @Autowired
    VendorCommandAction action;
    @Autowired
    VendorQueryAction vendorQueryAction;
    @Autowired
    EmployeeCommandService employeeCommandService;
    @Autowired
    UserAccountQueryService userAccountQueryService;
    @Autowired
    EmployeeQueryService employeeQueryService;
    @Value("${product.service.uri}")
    String productUri;
    @Value("${report.service.uri}")
    String reportUri;
    RestTemplate restTemplate= new RestTemplate();
    @Autowired
    EmployeeRepository employeeRepository;

    final Logger log = LoggerFactory.getLogger(VendorCommandService.class);


    public Vendor createVendor(Vendor vendor){
        vendor.getUserAccount().setRole("VENDOR");
        if(userAccountQueryService.validateAccountInformation(vendor.getUserAccount().getEmail(),vendor.getUserAccount().getUsername())) {
            Vendor c = action.save(vendor);
            log.info("Create Vendor status: {},"+c.toString());
            return c;
        }
      throw new UserExist("User with this email or username exist");
    }




    public boolean deleteById(Long id){

        Boolean productFlag;
//        productFlag =  restTemplate.getForObject(productUri+"product/delete/"+id,Boolean.class);
        Boolean reportFlag;
//        reportFlag = restTemplate.getForObject(reportUri+"rport/delete/"+id,Boolean.class);

      productFlag =true;
      reportFlag=true;
      boolean status = false;

      if(productFlag==true&&reportFlag==true)
          status = action.deleteById(id);

       log.info("Delete Vendor status: "+ status);

        return status;
    }


    public Employee addemployee(Long id, Employee employee) {
        employee.getUserAccount().setEnabled(true);
      Employee employee1 =  employeeRepository.save(employee);
       Vendor vendor = vendorQueryAction.getVendorByAccountId(id);
       if(vendor!=null&&employee1!=null) {
          vendor.addEmployee(employee1);
          action.save(vendor);
           return employee1;
       }
      throw new UserDoesntExit("Vendor doesnt Exist");
    }
    @Transactional
    public boolean removeEmployee(Long vendor_id, Long employee_id) {
        Employee e = employeeQueryService.getById(employee_id);
        Vendor vendor = vendorQueryAction.getVendorById(vendor_id);
        if(vendor!=null&&e!=null) {
            vendor.removeEmployee(e);
            action.save(vendor);
            return true;
        }
        return false;
    }


    public Employee getById(Long id) {
        return action.getById(id);
    }


}
