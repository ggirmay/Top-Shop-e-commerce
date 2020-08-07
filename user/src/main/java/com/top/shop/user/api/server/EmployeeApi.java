package com.top.shop.user.api.server;

import com.top.shop.user.command.service.EmployeeCommandService;
import com.top.shop.user.domain.Employee;
import com.top.shop.user.query.service.EmployeeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeApi {
    @Autowired
    EmployeeQueryService employeeQueryService;
    @Autowired
    EmployeeCommandService employeeCommandService;

    @PostMapping
    @Operation(summary = "Create Employee ",description = "Return Employee Detail")
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeCommandService.registerUser(employee));
    }

    @GetMapping
    @Operation(summary = "Get all Employees  ",description = "Return List of Employees")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok().body(employeeQueryService.fndAll());
    }


    @GetMapping("/{id}")
    @Operation(summary = "Get all Employees Employee ",description = "Return List of Employees")
    public ResponseEntity<Employee> getAll(@PathVariable Long id){
        return ResponseEntity.ok().body(employeeQueryService.getById(id));
    }


    @PutMapping("/{id}")
    @Operation(summary = "Get all Employees Employee ",description = "Return List of Employees")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "valid Request")})
    public ResponseEntity<Employee> update(@RequestBody Employee employee){
        return ResponseEntity.ok().body(employeeCommandService.update(employee));
    }

}
