package com.top.shop.user.api.server;

import com.netflix.discovery.converters.Auto;
import com.top.shop.user.command.service.AdminCommandService;
import com.top.shop.user.domain.Admin;
import com.top.shop.user.domain.UserAccount;
import com.top.shop.user.query.action.AdminQueryAction;
import com.top.shop.user.query.service.AdminQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.flogger.Flogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@Controller
@RequestMapping("/api/admin")
public class AdminApi {
    @Autowired
    AdminQueryService adminQueryService;
    @Autowired
    AdminCommandService adminCommandService;

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Create Admin ",description = "Return Admin Detail")
    public ResponseEntity<Admin> create(@RequestBody Admin admin){
        return ResponseEntity.ok().body(adminCommandService.registerUser(admin));
    }
    @CrossOrigin
    @GetMapping
    @Operation(summary = "Get all admins",description = "Return List of Admins")
    public ResponseEntity<List<Admin>> getAll(){
        System.out.println("I AM HERE");
        return ResponseEntity.ok().body(adminQueryService.fndAll());
    }

    @CrossOrigin
    @GetMapping("findById/{id}")
    @Operation(summary = "Get admin by id",description = "Return Admin by id")
    public ResponseEntity<Admin> getAll(@PathVariable Long id){
        return ResponseEntity.ok().body(adminQueryService.getById(id));
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(summary = "Update  admin Account",description = "return updated value")
    public ResponseEntity<Admin> update(@RequestBody Admin admin){
        return ResponseEntity.ok().body(adminCommandService.update(admin));
    }



}
