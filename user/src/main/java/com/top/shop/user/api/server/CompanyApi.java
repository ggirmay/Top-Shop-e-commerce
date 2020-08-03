package com.top.shop.user.api.server;

import com.top.shop.user.command.service.CompanyCommandService;
import com.top.shop.user.domain.Company;
import com.top.shop.user.query.service.CompanyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyApi {
    @Autowired
    CompanyQueryService companyQueryService;
    @Autowired
    CompanyCommandService companyCommandService;

    final Logger log = LoggerFactory.getLogger(CompanyApi.class);
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get All Company", description = "<p> This Api Return List of Company"
            + "or if {} if the database is empty.</p>")
    public ResponseEntity<List<Company>> getAll(){
        return ResponseEntity.ok().body(companyQueryService.findAllCompany());
    }
    @PostMapping
    @Operation(summary = "CreateCompany", description = "<p> This Api Create a Company")
    public String create(@RequestBody Company company){
        log.info("Create "+ company.toString());
        companyCommandService.createCompany(company);
       return "created";
    }
    @DeleteMapping(value = "/{id}")
    @Operation(summary = "CreateCompany", description = "<p> This Api Create a Company")
    public ResponseEntity<String> delete(@PathVariable Long id){
    boolean result = companyCommandService.deleteById(id);
        return result?ResponseEntity.ok().body("Deleted")
                :ResponseEntity.ok().body("Unable To delete");
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update Company", description = "<p> This Api Update a Company")
    public ResponseEntity<Company> update(@RequestBody Company newCompany, @PathVariable Long id){
      Company company  = companyQueryService.updateCompany(newCompany);
      log.debug(company.toString());

      return ResponseEntity.ok(company);
    }
}
