package com.top.shop.user.command.service;

import com.top.shop.user.command.action.CompanyCommandAction;
import com.top.shop.user.domain.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyCommandService {
    @Autowired
    CompanyCommandAction action;

    final Logger log = LoggerFactory.getLogger(CompanyCommandService.class);
    public Company createCompany(Company company){
      Company c = action.save(company);
      log.info("Create Company status: {},"+c.toString());

      return c;
    }


    public boolean deleteById(Long id){
       boolean status = action.deleteById(id);
       log.info("Delete Company status: "+ status);

        return status;
    }
}
