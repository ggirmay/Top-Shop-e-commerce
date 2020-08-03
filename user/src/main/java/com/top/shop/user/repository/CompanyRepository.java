package com.top.shop.user.repository;

import com.top.shop.user.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
//@RepositoryRestResource(collectionResourceRel = "company", path = "company")
public interface CompanyRepository extends JpaRepository<Company,Long> {
}
