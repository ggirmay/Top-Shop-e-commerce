package com.top.shop.user.query.service;

import com.top.shop.user.domain.Company;
import com.top.shop.user.query.action.CompanyQueryAction;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyQueryService {

    @Autowired
    private CompanyQueryAction action;
    final Log log = LogFactory.getLog(CompanyQueryService.class);

    public Company updateCompany(Company company){
        Company c = action.save(company);
        log.info("Create Company status: {},"+c.toString());
        return c;
    }

    public List<Company> findAllCompany(){
        return action.findAll();
    }

    public Company getCompanyById(Long id){
        return action.getCompanyById(id);
    }
}
