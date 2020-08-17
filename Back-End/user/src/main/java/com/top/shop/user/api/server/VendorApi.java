package com.top.shop.user.api.server;

import com.top.shop.user.command.service.EmployeeCommandService;
import com.top.shop.user.command.service.UserAccountCommandService;
import com.top.shop.user.command.service.VendorCommandService;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.Vendor;
import com.top.shop.user.query.service.EmployeeQueryService;
import com.top.shop.user.query.service.VendorQueryService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/api/vendor")
public class VendorApi {
    @Autowired
    VendorQueryService vendorQueryService;

    @Autowired
    VendorCommandService vendorCommandService;
    @Autowired
    EmployeeQueryService employeeQueryService;
    @Autowired
    EmployeeCommandService employeeCommandService;
    @Autowired
    UserAccountCommandService userAccountCommandService;

    final Logger log = LoggerFactory.getLogger(VendorApi.class);
    @GetMapping
    @Operation(summary = "Get All Vendor", description = "<p> This Api Return List of Vendor"
            + "or if {} if the database is empty.</p>")
    public ResponseEntity<List<Vendor>> getAll() {
        return ResponseEntity.ok().body(vendorQueryService.findAllVendor());
    }
    @GetMapping("/pending")
    @Operation(summary = "Get  Vendor pending", description = "<p> This Api Return List of Vendor"
            + "or if {} if the database is empty.</p>")
    public ResponseEntity<List<Vendor>> getpeningVendor() {
        return ResponseEntity.ok().body(vendorQueryService.getpending());
    }


    @PostMapping
    @Operation(summary = "CreateVendor", description = "<p> This Api Create a Vendor")
    public ResponseEntity<PlainText> create(@RequestBody Vendor vendor) {
        System.out.println("this is vendor controller: " + vendor.getName());
        System.out.println(vendor.getAddress().getAddressLineOne());

        vendor.getUserAccount().setEnabled(false);
        vendorCommandService.createVendor(vendor);
        return ResponseEntity.ok().body(new PlainText("created"));
    }



    @PutMapping(value = "/{id}")
    @Operation(summary = "Update Vendor", description = "<p> This Api Update a Vendor")
    public ResponseEntity<Vendor> update(@RequestBody Vendor newVendor, @PathVariable Long id) {
        Vendor vendor = vendorQueryService.updateVendor(newVendor);
        log.debug(vendor.toString());

        return ResponseEntity.ok(vendor);
    }

    @GetMapping("getEmployees/{id}")
    @Operation(summary = "Get all employees", description = "return all employess belongs to  a vendor")
    public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable Long id) {
        return ResponseEntity.ok().body(employeeQueryService.findEmployyes(id));
    }

    @PostMapping("addEmployee/{id}")
    @Operation(summary = "Add employee Employee", description = "Add  Employees")
    public ResponseEntity<Employee> getAllEmployees(@PathVariable Long id,@RequestBody Employee employee) {
        System.out.println("this is vendor controller" + employee.getRole());
        return ResponseEntity.ok().body(vendorCommandService.addemployee(id,employee));
    }

    @DeleteMapping("/{vendor_id}")
    @Operation(summary = "Delete Vendor", description = "return  true if deleted or false ")
    public ResponseEntity<Boolean> removeFromVendor(@PathVariable Long vendor_id){
        return ResponseEntity.ok().body(vendorCommandService.deleteById(vendor_id));
    }

    @DeleteMapping("/{vendor_id}/{employee_id}")
    @Operation(summary = "Delete Employee", description = "return  true if deleted or false ")
    public ResponseEntity<Boolean> removeEmployeeFromVendor(@PathVariable Long vendor_id, @PathVariable Long employee_id){
        return ResponseEntity.ok().body(vendorCommandService.removeEmployee(vendor_id,employee_id));
    }



    static class PlainText{
        String message;

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
}

