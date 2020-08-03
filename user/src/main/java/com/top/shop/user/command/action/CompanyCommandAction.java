package com.top.shop.user.command.action;

import com.top.shop.user.domain.Company;
import com.top.shop.user.query.service.CompanyQueryService;
import com.top.shop.user.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompanyCommandAction {
    @Autowired
    CompanyRepository repository;
    @Autowired
    CompanyQueryService companyQueryService;



    public boolean deleteById(Long id) {
        repository.deleteById(id);

        return companyQueryService.getCompanyById(id)==null?true:false;
    }

    public Company save(Company company) {
        return repository.save(company);
    }
}
