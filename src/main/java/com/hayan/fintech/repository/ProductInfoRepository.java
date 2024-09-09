package com.hayan.fintech.repository;

import com.hayan.fintech.domain.ProductInfo;
import com.hayan.fintech.domain.constant.OrganizationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, Long> {
    List<ProductInfo> findAllByOrganizationCode(OrganizationCode organizationCode);
}
