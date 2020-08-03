package com.top.shop.user.query.action;

import com.top.shop.user.domain.Company;
import com.top.shop.user.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CompanyQueryAction {
    @Autowired
    CompanyRepository repository;

    public Company getCompanyById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Company save(Company company) {
        return repository.save(company);
    }

    public List<Company> findAll() {
        return repository.findAll();
    }
}
