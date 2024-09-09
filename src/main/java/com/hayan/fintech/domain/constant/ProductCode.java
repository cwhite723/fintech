package com.hayan.fintech.domain.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductCode implements EnumCode{
    PRODUCT_A("001"),
    PRODUCT_B("002"),
    PRODUCT_C("003");

    private final String code;

    public static ProductCode ofName(String name) {
        return EnumCode.ofName(ProductCode.class, name);
    }

    public static ProductCode ofCode(String code) {
        return EnumCode.ofCode(ProductCode.class, code);
    }
}
