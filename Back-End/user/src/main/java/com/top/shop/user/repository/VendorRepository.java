package com.top.shop.user.repository;

import com.top.shop.user.domain.Employee;
import com.top.shop.user.domain.PaymentInformation;
import com.top.shop.user.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
//@RepositoryRestResource(collectionResourceRel = "company", path = "company")
public interface VendorRepository extends JpaRepository<Vendor,Long> {
    @Query(value = "SELECT v.paymentInformation FROM Vendor v WHERE v.id = :id")
    List<PaymentInformation> findAllPaymentInformationById(@Param("id") Long id);

    @Query( "from Vendor v inner join fetch v.userAccount u where u.enabled = false")
    List<Vendor> findPendingAccount();

    @Query( "from Vendor v inner join fetch v.userAccount u where u.id = :id")
    Vendor findVendorByAccountId(@Param("id") Long id);
}