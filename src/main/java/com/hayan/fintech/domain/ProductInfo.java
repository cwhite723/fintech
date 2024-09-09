package com.hayan.fintech.domain;

import com.hayan.fintech.domain.base.BaseEntity;
import com.hayan.fintech.domain.constant.OrganizationCode;
import com.hayan.fintech.domain.constant.ProductCode;
import com.hayan.fintech.domain.dto.ProductInfoDto;
import com.hayan.fintech.util.converter.OrganizationCodeConverter;
import com.hayan.fintech.util.converter.ProductCodeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Entity
@Builder
@Table(name = "product_info")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductInfo extends BaseEntity {

    @Convert(converter = OrganizationCodeConverter.class)
    @Column(name = "org_cd")
    private OrganizationCode organizationCode;

    @Convert(converter = ProductCodeConverter.class)
    @Column(name = "prod_cd", unique = true)
    private ProductCode productCode;

    @Column(name = "prod_nm")
    private String productName;

    @Column(name = "prod_min_intr")
    private Double productMinimumInterest;

    @Column(name = "prod_max_intr")
    private Double productMaximumInterest;

    public static ProductInfo toEntity(ProductInfoDto productInfoDto) {
        return ProductInfo.builder()
                .organizationCode(OrganizationCode.ofCode(productInfoDto.organizationCode()))
                .productCode(ProductCode.ofCode(productInfoDto.productCode()))
                .productMaximumInterest(productInfoDto.productMaximumInterest())
                .productMinimumInterest(productInfoDto.productMinimumInterest())
                .productName(productInfoDto.productName())
                .build();
    }
}
