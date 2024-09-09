package com.hayan.fintech.service.impl;

import com.hayan.fintech.domain.ProductInfo;
import com.hayan.fintech.domain.constant.OrganizationCode;
import com.hayan.fintech.domain.dto.ProductInfoDto;
import com.hayan.fintech.repository.ProductInfoRepository;
import com.hayan.fintech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductInfoRepository productInfoRepository;

    @Cacheable(value = "products", key = "#organizationCode")
    @Override
    public List<ProductInfoDto> loadProductsByOrganizationCode(String organizationCode) {
        var productList = productInfoRepository.findAllByOrganizationCode(OrganizationCode.ofName(organizationCode));

        return listToDto(productList);
    }

    @Transactional
    @Override
    public void save(ProductInfoDto productInfoDto) {
        var product = ProductInfo.toEntity(productInfoDto);
        productInfoRepository.save(product);
    }

    private List<ProductInfoDto> listToDto(List<ProductInfo> productList) {

        return productList.stream()
                .map(productInfo -> ProductInfoDto.builder()
                        .organizationCode(productInfo.getOrganizationCode().name())
                        .productCode(productInfo.getProductCode().name())
                        .productMaximumInterest(productInfo.getProductMaximumInterest())
                        .productMinimumInterest(productInfo.getProductMinimumInterest())
                        .productName(productInfo.getProductName())
                        .build())
                .toList();
    }
}
