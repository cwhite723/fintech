package com.hayan.fintech.util.converter;

import com.hayan.fintech.domain.constant.ProductCode;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ProductCodeConverter extends AbstractEnumAttributeConverter<ProductCode, String> {

    public ProductCodeConverter() {
        super(ProductCode.class);
    }
}
