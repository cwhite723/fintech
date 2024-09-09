package com.hayan.fintech.service;

import com.hayan.fintech.domain.dto.ProductInfoDto;

import java.util.List;

public interface ProductService {
    List<ProductInfoDto> loadProductsByOrganizationCode(String organizationCode);
    void save(ProductInfoDto productInfoDto);
}
